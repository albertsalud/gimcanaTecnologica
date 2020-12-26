package tk.daudecinc.gimcana.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.services.LocationServices;

@Controller
@RequestMapping("/admin/locations")
public class LocationController {
	
	@Autowired
	private LocationServices locationServices;
	
	@GetMapping("/new")
	public String locationForm(Model model) {
		return goToLocationForm(model, new Location());
	}
	
	private String goToLocationForm(Model model, Location location) {
		model.addAttribute("location", location);
		return "locationForm";
	}
	
	@PostMapping("/save")
	public String saveLocation(
			Model model,
			@Valid @ModelAttribute Location location,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			return goToLocationForm(model, location);
		}
		
		locationServices.saveLocation(location);
		
		return locationsList(model);
	}
	
	
	@GetMapping(value = {"", "/"})
	public String locationsList(Model model) {
		model.addAttribute("locations", locationServices.listLocations());
		
		return "locationList";
	}
	
	
	@GetMapping("/{locationId}")
	public String getLocation(@PathVariable Long locationId, Model model) {
		Location location = locationServices.getLocation(locationId);
		
		if(location == null) {
			model.addAttribute("message", "Requested location not exists!");
			location = new Location();
		}
		
		return goToLocationForm(model, location);
	}

}
