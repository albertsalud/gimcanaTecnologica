package tk.daudecinc.gimcana.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.gimcana.model.entities.Event;

public interface EventDAO extends JpaRepository<Event, Long> {

}
