package tk.daudecinc.gimcana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.EventServices;

@RestController
@RequestMapping("/players-rest")
public class PlayerRestController {
	
	@Autowired
	private EventServices eventServices;
	
	@GetMapping("/eventPlayers")
	public List<Player> getEventPlayers(
			@RequestParam Long eventId
			){
		Event searchedEvent = eventServices.getEvent(eventId);
		
		return eventServices.getEventPlayers(searchedEvent);

	}

}
