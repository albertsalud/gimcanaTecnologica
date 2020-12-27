package tk.daudecinc.gimcana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.gimcana.model.services.EventServices;

@Controller
public class MainController {
	
	@Autowired
	private EventServices eventServices;
	
	@RequestMapping("/")
	public String init(Model model) {
		model.addAttribute("events", eventServices.listEventsAllowedForRegistration());
		return "index";
	}

}
