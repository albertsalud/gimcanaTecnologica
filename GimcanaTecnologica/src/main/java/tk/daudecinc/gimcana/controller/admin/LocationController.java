package tk.daudecinc.gimcana.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.gimcana.model.entities.Location;

@Controller
@RequestMapping("/admin/localizaciones")
public class LocationController {
	
	@GetMapping("/nueva")
	public String locationForm(Model model) {
		model.addAttribute("location", new Location());
		return "locationForm";
	}

}
