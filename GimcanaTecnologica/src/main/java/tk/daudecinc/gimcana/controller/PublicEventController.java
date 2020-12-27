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

import tk.daudecinc.gimcana.controller.dto.PlayerRegistrationDTO;
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
		return goToPlayerRegistrationForm(model, new PlayerRegistrationDTO());
		
	}
	
	private String goToPlayerRegistrationForm(Model model, PlayerRegistrationDTO dto) {
		model.addAttribute("events", eventServices.listEventsAllowedForRegistration());
		model.addAttribute("playerRegistrationDTO", dto);
		
		return "playerForm";
	}
	
	@PostMapping("/registry")
	public String registryPlayer(
			@Valid @ModelAttribute PlayerRegistrationDTO dto,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return goToPlayerRegistrationForm(model, dto);
		}
		
		try {
			Player player = buildPlayer(dto);
			playerServices.savePlayer(player);
		
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return goToPlayerRegistrationForm(model, dto);
		}
		
		return "registrationConfirmation";
	}

	private Player buildPlayer(@Valid PlayerRegistrationDTO dto) {
		return Player.builder()
				.event(dto.getEvent())
				.name(dto.getName())
				.password(dto.getPassword())
				.build();
	}

}
