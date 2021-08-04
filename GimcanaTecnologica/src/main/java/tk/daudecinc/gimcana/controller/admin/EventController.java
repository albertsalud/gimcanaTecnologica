package tk.daudecinc.gimcana.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.gimcana.controller.dto.EventFormDTO;
import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.EventServices;
import tk.daudecinc.gimcana.model.services.LocationServices;
import tk.daudecinc.gimcana.model.services.PlayerServices;

@Controller
@RequestMapping("/admin/events")
public class EventController {
	
	@Autowired
	private LocationServices locationServices;
	
	@Autowired
	private EventServices eventServices;
	
	@Autowired
	private PlayerServices playerServices;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/new")
	public String goToEventForm(Model model) {
		return goToEventForm(model, new EventFormDTO());
	}
	
	private String goToEventForm(Model model, EventFormDTO event) {
		event.setAllLocations(manageAvailableLocations(event, locationServices.listLocations()));
		model.addAttribute("eventFormDTO", event);
		
		return "eventForm";
	}
	
	private List<Location> manageAvailableLocations(EventFormDTO event, List<Location> allLocations) {
		List<Location> eventLocations = event.getEventLocations();
		
		if(eventLocations != null && !eventLocations.isEmpty()) {
			List<Location> newAllLocationsList = new ArrayList<>(allLocations);
			eventLocations.forEach(l -> {
				if(allLocations.contains(l)) newAllLocationsList.remove(l);
			});
			
			return newAllLocationsList;
			
		} else {
			event.setEventLocations(new ArrayList<>(allLocations));
			return new ArrayList<>();
		}
		
		
	}

	@PostMapping("/save")
	public String saveEvent(
			@Valid @ModelAttribute EventFormDTO eventFormDTO,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return this.goToEventForm(model, eventFormDTO);
		}
		
		Event eventToSave = manageEventFormDTO(eventFormDTO);
		eventServices.saveEvent(eventToSave);
		
		return this.listEvents(model);
	}
	
	private Event manageEventFormDTO(@Valid EventFormDTO eventFormDTO) {
		if(eventFormDTO.getId() == null) {
			return modelMapper.map(eventFormDTO, Event.class);
		
		} else {
			return setEvent(eventServices.getEvent(eventFormDTO.getId()), eventFormDTO);
		}
		
	}

	private Event setEvent(Event event, @Valid EventFormDTO eventFormDTO) {
		event.setAllowPlayersRegistration(eventFormDTO.isAllowPlayersRegistration());
		event.setEventStarted(eventFormDTO.isEventStarted());
		event.setInitDate(eventFormDTO.getInitDate());
		event.setName(eventFormDTO.getName());
		event.setEventLocations(eventFormDTO.getEventLocations());
		return event;
	}

	@GetMapping("/{eventId}")
	public String getEvent(
			@PathVariable(required = true) Long eventId,
			Model model
			) {
		
		Event eventSearched = eventServices.getEvent(eventId);
		
		EventFormDTO dto; 
		if(eventSearched == null) {
			model.addAttribute("message", "Requested event not exists!");
			dto = new EventFormDTO();
		}
		
		dto = modelMapper.map(eventSearched, EventFormDTO.class);
		return goToEventForm(model, dto);
	}
	
	@GetMapping("/{eventId}/players")
	public String getEventPlayersList(
			@PathVariable(required = true) Long eventId,
			@RequestParam(name="player", required = false) Long playerId,
			@RequestParam(required = false) Boolean present,
			Model model
			) {
		
		Event eventSearched = eventServices.getEvent(eventId);
		
		if(eventSearched == null) {
			model.addAttribute("message", "Requested event not exists!");
			return this.listEvents(model);
		}
		
		eventServices.setPlayerPresent(playerId, present);
		
		model.addAttribute("players", eventServices.getEventPlayers(eventSearched));
		return "eventPlayersList";
	}
	
	@GetMapping("/{eventId}/players/{playerId}")
	public String getEventPlayersProgress(
			@PathVariable(required = true) Long eventId,
			@PathVariable(required = true) Long playerId,
			Model model
			) {
		
		Player player = playerServices.findById(playerId);
				
		if(player == null) {
			model.addAttribute("message", "Requested player not exists!");
			return this.getEventPlayersList(eventId, null, null, model);
		}
			
		model.addAttribute("player", player);
		return "eventPlayerStatus";
	}
	
	@GetMapping(value = {"", "/ "})
	public String listEvents(Model model) {
		
		List<Event> events = eventServices.listEvents();
		model.addAttribute("listEvents", events);
		
		return "eventList";
	}
	
}
