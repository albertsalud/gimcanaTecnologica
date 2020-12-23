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
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.gimcana.controller.dto.CheckPointFormDTO;
import tk.daudecinc.gimcana.controller.exceptions.EventNotStartedException;
import tk.daudecinc.gimcana.controller.exceptions.LocationNotFoundException;
import tk.daudecinc.gimcana.controller.exceptions.PlayerNotFoundException;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.LocationServices;
import tk.daudecinc.gimcana.model.services.PlayerServices;

@Controller
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private PlayerServices playerServices;
	
	@Autowired
	private LocationServices locationServices;
	
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
		model.addAttribute("checkPointFormDTO", dto);
		return "checkPointForm";
	}
	
	@PostMapping("/checkPlayerStatus")
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
		if(player.getCheckPoints().isEmpty()) {
			playerServices.addCheckPoint(player);
			model.addAttribute("player", player);
		
		} else if (locationCode != null) {
			Location requestedLocation = locationServices.findByCode(locationCode);
			
			if(requestedLocation == null) throw new LocationNotFoundException();
			
			if(requestedLocation.equals(player.getCheckPoints().get(0))) {

			}
		
		}
		
		return "playerStatus";
		
	}

	private Player lookForPlayer(@Valid CheckPointFormDTO checkPointFormDTO) throws PlayerNotFoundException {
		Player player = playerServices.findByUserAndPassword(checkPointFormDTO.getPlayerName(),
				checkPointFormDTO.getPassword());
		
		if(player == null) throw new PlayerNotFoundException();
		return player;
	}

}
