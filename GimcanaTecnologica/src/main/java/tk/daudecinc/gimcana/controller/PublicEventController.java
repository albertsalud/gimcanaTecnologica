package tk.daudecinc.gimcana.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.EventServices;
import tk.daudecinc.gimcana.model.services.PlayerServices;

@Controller
@RequestMapping("/events")
public class PublicEventController {
	
	@Autowired
	private EventServices eventServices;
	
	@Autowired
	private PlayerServices playerServices;
	
	@GetMapping("/registry")
	public String listEvents(Model model) {
		return goToPlayerRegistrationForm(model, new Player());
		
	}
	
	private String goToPlayerRegistrationForm(Model model, Player player) {
		model.addAttribute("events", eventServices.listEventsAllowedForRegistration());
		model.addAttribute("player", player);
		
		return "playerForm";
	}
	
	@PostMapping("/registry")
	public String registryPlayer(
			@Valid @ModelAttribute Player player,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return goToPlayerRegistrationForm(model, player);
		}
		
		try {
			playerServices.savePlayer(player);
		
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return goToPlayerRegistrationForm(model, player);
		}
		
		return "registrationConfirmation";
	}

}
