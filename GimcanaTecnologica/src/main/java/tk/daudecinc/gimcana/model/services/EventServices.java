package tk.daudecinc.gimcana.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.EventDAO;
import tk.daudecinc.gimcana.model.entities.Event;

@Service
public class EventServices {
	
	@Autowired
	private EventDAO eventDao;

	public void saveEvent(Event event) {
		eventDao.save(event);
	}

	public Event getEvent(Long eventId) {
		return eventDao.findById(eventId).orElse(null);
		
	}

	public List<Event> listEvents() {
		return eventDao.findAll();
	}
	
}
