package tk.daudecinc.gimcana.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Player;

public interface PlayerDAO extends JpaRepository<Player, Long>{
	
	Player findByIdAndPassword(Long id, String password);

	List<Player> findAllByEventOrderByName(Event event);

}
