package tk.daudecinc.gimcana.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.gimcana.model.entities.Location;

public interface LocationDAO extends JpaRepository<Location, Long> {
	
	Location findByCode(String code);

}
