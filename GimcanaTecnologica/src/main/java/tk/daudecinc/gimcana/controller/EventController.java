package tk.daudecinc.gimcana.controller;

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

import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.services.EventServices;

@Controller
public class EventController {
	
	@Autowired
	private EventServices eventServices;
	
	@GetMapping("/eventos/nuevo")
	public String goToEventForm(Model model) {
		model.addAttribute("event", new Event());
		return "eventForm";
	}
	
	@PostMapping("/eventos/nuevo")
	public String saveEvent(
			@Valid @ModelAttribute Event eventToSave,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("The received event has errors!");
			model.addAttribute("event", eventToSave);
			return "eventForm";
		}
		
		System.out.println("Received event: " + eventToSave);
		eventServices.saveEvent(eventToSave);
		
		return "index";
	}
	
	@GetMapping("/eventos/{eventId}")
	public String getEvent(
			@PathVariable(required = true) Long eventId,
			Model model
			) {
		
		Event eventSearched = eventServices.getEvent(eventId);
		
		if(eventSearched == null) {
			model.addAttribute("message", "Requested event not exists!");
			eventSearched = new Event();
		}
		
		model.addAttribute("event", eventSearched);
		
		return "eventForm";
	}
	
	@GetMapping("/eventos")
	public String listEvents(Model model) {
		
		List<Event> events = eventServices.listEvents();
		model.addAttribute("listEvents", events);
		
		return "eventList";
	}
	

}
