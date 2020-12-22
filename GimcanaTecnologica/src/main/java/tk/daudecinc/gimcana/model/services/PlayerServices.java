package tk.daudecinc.gimcana.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.PlayerDAO;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.tools.SecretWordGenerator;

@Service
public class PlayerServices {
	
	@Autowired
	private SecretWordGenerator secretWordGenerator;
	
	@Autowired
	private PlayerDAO playerDao;
	
	public void savePlayer(Player player) {
		
		if(player.getId() == null) {
			player.setSecretWord(secretWordGenerator.getSecretWord());
		}
		
		playerDao.save(player);
	}

}
