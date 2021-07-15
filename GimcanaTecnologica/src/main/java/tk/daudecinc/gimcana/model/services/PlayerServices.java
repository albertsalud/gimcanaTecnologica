package tk.daudecinc.gimcana.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.PlayerDAO;
import tk.daudecinc.gimcana.model.entities.CheckPoint;
import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.tools.CheckPointAssigner;
import tk.daudecinc.gimcana.tools.SecretWordGenerator;

@Service
public class PlayerServices {
	
	@Autowired
	private SecretWordGenerator secretWordGenerator;
	
	@Autowired
	private PlayerDAO playerDao;
	
	@Autowired
	private CheckPointAssigner checkPointAssigner;
	
	public void savePlayer(Player player) {
		
		if(player.getId() == null) {
			player.setSecretWord(secretWordGenerator.getSecretWord());
		}
		
		playerDao.save(player);
	}

	public Player findByIdAndPassword(Long playerId, String password) {
		return playerDao.findByIdAndPassword(playerId, password);
	}

	public void addCheckPoint(Player player) {
		CheckPoint newCheckPoint = checkPointAssigner.assignCheckPoint(player);
		player.getCheckPoints().add(0, newCheckPoint);	// new checkpoint always added at position 0.
		
		this.savePlayer(player);
	}

	public List<Player> findByEvent(Event event) {
		return playerDao.findAllByEventOrderByName(event);
	}

	public Player findById(Long playerId) {
		return playerDao.findById(playerId).orElse(null);
	}

}
