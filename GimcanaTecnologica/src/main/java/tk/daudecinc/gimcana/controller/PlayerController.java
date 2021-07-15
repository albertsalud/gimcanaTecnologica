package tk.daudecinc.gimcana.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.gimcana.controller.dto.CheckPointFormDTO;
import tk.daudecinc.gimcana.controller.exceptions.EventNotStartedException;
import tk.daudecinc.gimcana.controller.exceptions.LocationNotFoundException;
import tk.daudecinc.gimcana.controller.exceptions.PlayerNotFoundException;
import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.EventServices;
import tk.daudecinc.gimcana.model.services.LocationServices;
import tk.daudecinc.gimcana.model.services.PlayerServices;

@Controller
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private PlayerServices playerServices;
	
	@Autowired
	private LocationServices locationServices;
	
	@Autowired
	private EventServices eventServices;
	
	@GetMapping("/checkPoint")
	public String goToCheckPointForm(
			@RequestParam(name = "locationCode", required = false) String locationCode,
			Model model
			) {
		CheckPointFormDTO dto = new CheckPointFormDTO();
		dto.setLocationCode(locationCode);
		
		return this.goToCheckPointForm(model, dto);
	}
	
	private String goToCheckPointForm(Model model, CheckPointFormDTO dto) {
		List<Event> startedEvents = eventServices.getStartedEvents();
		model.addAttribute("events", startedEvents);
		
		if(startedEvents.isEmpty()) return "noEventsStarted";

		if(dto.getEventId() != null || startedEvents.size() == 1) {
			dto.setEventId(dto.getEventId() != null ? dto.getEventId() : startedEvents.get(0).getId());
			
			Event selectedEvent = eventServices.getEvent(dto.getEventId());
			model.addAttribute("playersList", eventServices.getEventPresentPlayers(selectedEvent));
		}

		model.addAttribute("checkPointFormDTO", dto);
		return "checkPointForm";
	}
	
	@PostMapping("/checkPoint")
	public String checkPlayerStatus(
			@Valid @ModelAttribute CheckPointFormDTO checkPointFormDTO,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return this.goToCheckPointForm(model, checkPointFormDTO);
		}
		
		return managePlayerStatus(model, checkPointFormDTO);
		
	}

	private String managePlayerStatus(Model model, @Valid CheckPointFormDTO checkPointFormDTO) {
		
		try {
			Player player = lookForPlayer(checkPointFormDTO);
			
			if(!player.getEvent().isEventStarted()) throw new EventNotStartedException();
			
			return manageLocationCode(model, player, checkPointFormDTO.getLocationCode());
		
		} catch (PlayerNotFoundException | EventNotStartedException | LocationNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			return goToCheckPointForm(model, checkPointFormDTO);
		}
			

	}

	private String manageLocationCode(Model model, Player player, String locationCode) throws LocationNotFoundException {
		if(player.getCheckPoints().isEmpty()) {	// When event starts
			playerServices.addCheckPoint(player);
		
		} else if (locationCode != null && !"".equals(locationCode)) {	// When player checks a place
			checkValidLocation(model, player, locationCode);
		}
		
		// else: player is checking its own status
		model.addAttribute("player", player);
		manageCharactersFound(model, player);
		manageDescriptionMessage(model, player);
		
		return "playerStatus";
		
	}

	private void manageDescriptionMessage(Model model, Player player) {
		String descriptionMessage;
		if(player.getCheckPoints().get(0).getCheckedDate() == null) {	// Show riddle description
			descriptionMessage = "La teva pista es: <p><b>" + player.getCheckPoints().get(0).getLocation().getDescription().replaceAll("\n", "<br/>") + "</b></p>";
		} else {	// End game message
			descriptionMessage = "Enhorabona! Has acabat la gimcana! Torna al punt de control per comprobar si has guanyat.";
		}
		
		model.addAttribute("descriptionMessage", descriptionMessage);
		
	}

	private void manageCharactersFound(Model model, Player player) {
		int charactersFound = player.getCheckPoints().get(0).getCheckedDate() == null ? player.getCheckPoints().size() - 1 : player.getCheckPoints().size();
		model.addAttribute("charactersFound", player.getSecretWord().substring(0, charactersFound));
	}

	private void checkValidLocation(Model model, Player player, String locationCode) throws LocationNotFoundException {
		Location requestedLocation = locationServices.findByCode(locationCode);
		
		if(requestedLocation == null) throw new LocationNotFoundException();
		
		if(requestedLocation.equals(player.getCheckPoints().get(0).getLocation())) {
			model.addAttribute("resultMessage", "Molt bé! Has trobat una nova lletra!");
			player.getCheckPoints().get(0).setCheckedDate(new Date());
			
			if(player.getCheckPoints().size() == 5) {	// End game
				playerServices.savePlayer(player);
			} else {	// Assign new check point
				playerServices.addCheckPoint(player);
			}
		
		} else {
			model.addAttribute("resultMessage", "Estàs al lloc equivocat! Torna a llegir la pista amb tranquilitat ...");
		}
		
	}

	private Player lookForPlayer(@Valid CheckPointFormDTO checkPointFormDTO) throws PlayerNotFoundException {
		Player player = playerServices.findByIdAndPassword(checkPointFormDTO.getPlayerId(),
				checkPointFormDTO.getPassword());
		
		if(player == null) throw new PlayerNotFoundException();
		return player;
	}

}
