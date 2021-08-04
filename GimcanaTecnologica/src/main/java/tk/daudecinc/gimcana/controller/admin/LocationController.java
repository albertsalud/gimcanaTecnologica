package tk.daudecinc.gimcana.controller.admin;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.gimcana.controller.dto.LocationFormDTO;
import tk.daudecinc.gimcana.controller.exceptions.LocationNotFoundException;
import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.services.EventServices;
import tk.daudecinc.gimcana.model.services.LocationServices;
import tk.daudecinc.gimcana.tools.PDFLocationsGenerator;

@Controller
@RequestMapping("/admin/locations")
public class LocationController {
	
	Log log = LogFactory.getLog(LocationController.class);
	
	@Autowired
	private LocationServices locationServices;
	
	@Autowired
	private EventServices eventServices;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PDFLocationsGenerator pdfGenerator;
	
	@GetMapping("/new")
	public String locationForm(Model model) {
		return goToLocationForm(model, new LocationFormDTO());
	}
	
	private String goToLocationForm(Model model, LocationFormDTO location) {
		model.addAttribute("locationFormDTO", location);
		return "locationForm";
	}
	
	@PostMapping("/save")
	public String saveLocation(
			Model model,
			@Valid @ModelAttribute LocationFormDTO location,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			return goToLocationForm(model, location);
		}
		
		try {
			Location locationToSave = manageLocationFormDTO(location);
			locationServices.saveLocation(locationToSave);
		
		} catch (LocationNotFoundException e) {
			model.addAttribute("message", e.getMessage());
		}
		
		return locationsList(model);
	}
	
	
	private Location manageLocationFormDTO(@Valid LocationFormDTO location) throws LocationNotFoundException {
		if(location.getId() == null) return modelMapper.map(location, Location.class);
		else {
			Location persistedLocation = locationServices.getLocation(location.getId());
			if(persistedLocation == null) throw new LocationNotFoundException();
			
			persistedLocation.setAvailable(location.isAvailable());
			persistedLocation.setCode(location.getCode());
			persistedLocation.setDescription(location.getDescription());
			persistedLocation.setName(location.getName());
			persistedLocation.setZone(location.getZone());
			
			return persistedLocation;
		}
	}

	@GetMapping(value = {"", "/"})
	public String locationsList(Model model) {
		model.addAttribute("locations", locationServices.listLocations());
		
		return "locationList";
	}
	
	
	@GetMapping("/{locationId}")
	public String getLocation(@PathVariable Long locationId, Model model) {
		Location location = locationServices.getLocation(locationId);
		
		LocationFormDTO dto;
		if(location == null) {
			model.addAttribute("message", "Requested location not exists!");
			dto = new LocationFormDTO();
		}
		
		dto = modelMapper.map(location, LocationFormDTO.class);
		return goToLocationForm(model, dto);
	}
	
	@GetMapping("/print/{eventId}")
	public void generateLocationsPDFDocument(@PathVariable Long eventId,
			HttpServletResponse response) {
		
		log.debug("Initializating locations document print process...");
		
		Event selectedEvent = eventServices.getEvent(eventId);
		
		log.debug("Generating pdf document...");
		InputStream pdf = pdfGenerator.generateLocationsPDFDocument(manageEventLocations(selectedEvent));

		sendDocumentToClient(selectedEvent, response, pdf);
		
		log.debug("locations document print process finished!");
	}

	private void sendDocumentToClient(Event selectedEvent, HttpServletResponse response, InputStream pdf) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + manageLocationsDocumentFileName(selectedEvent) + ".pdf\"");
		
		log.debug("Sending generated documento to client ...");
		try {
			byte[] buffer = new byte[10240];
			for (int length = 0; (length = pdf.read(buffer)) > 0;) {
				response.getOutputStream().write(buffer, 0, length);
		    }
			response.flushBuffer();
		
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}

	private String manageLocationsDocumentFileName(Event selectedEvent) {
		return selectedEvent.getName().replace(" ", "_");
	}

	private List<Location> manageEventLocations(Event selectedEvent) {
		List<Location> locations = selectedEvent.getEventLocations();
		locations.add(generateStartLocation());
		
		return locations;
	}

	private Location generateStartLocation() {
		Location startLocation = new Location();
		startLocation.setName("Inici");
		
		return startLocation;
	}

}
