package tk.daudecinc.gimcana.controller.admin;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.EventServices;
import tk.daudecinc.gimcana.model.services.PlayerServices;

@Controller
@RequestMapping("/admin/events")
public class EventController {
	
	@Autowired
	private EventServices eventServices;
	
	@Autowired
	private PlayerServices playerServices;
	
	@GetMapping("/new")
	public String goToEventForm(Model model) {
		return goToEventForm(model, new Event());
	}
	
	private String goToEventForm(Model model, Event event) {
		model.addAttribute("event", event);
		return "eventForm";
	}
	
	@PostMapping("/save")
	public String saveEvent(
			@Valid @ModelAttribute Event eventToSave,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return this.goToEventForm(model, eventToSave);
		}
		
		eventServices.saveEvent(eventToSave);
		
		return this.listEvents(model);
	}
	
	@GetMapping("/{eventId}")
	public String getEvent(
			@PathVariable(required = true) Long eventId,
			Model model
			) {
		
		Event eventSearched = eventServices.getEvent(eventId);
		
		if(eventSearched == null) {
			model.addAttribute("message", "Requested event not exists!");
			eventSearched = new Event();
		}
		
		return goToEventForm(model, eventSearched);
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
