package tk.daudecinc.gimcana.model.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<Event> listEventsAllowedForRegistration(){
		return getAllowedForRegistration(
						getFutureEvents(
								listEvents()));
	}
	
	
	private List<Event> getFutureEvents(List<Event> events){
		return events.stream()
				.filter(e -> {
					return e.getInitDate().after(new Date());
					})
				.collect(Collectors.toList());
	}
	
	private List<Event> getAllowedForRegistration(List<Event> events){
		return events.stream()
				.filter(e -> {
					return e.isAllowPlayersRegistration();
					})
				.collect(Collectors.toList());
	}
	
}
