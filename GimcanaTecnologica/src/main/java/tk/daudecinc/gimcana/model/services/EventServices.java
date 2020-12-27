package tk.daudecinc.gimcana.model.services;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.EventDAO;
import tk.daudecinc.gimcana.model.entities.Event;
import tk.daudecinc.gimcana.model.entities.Player;

@Service
public class EventServices {
	
	@Autowired
	private EventDAO eventDao;
	
	@Autowired
	private PlayerServices playerServices;

	public void saveEvent(Event event) {
		eventDao.save(event);
	}

	public Event getEvent(Long eventId) {
		return eventDao.findById(eventId).orElse(null);
		
	}

	public List<Event> listEvents() {
		return eventDao.findAll().stream()
				.sorted(Comparator.comparing(Event::getInitDate).reversed())
				.collect(Collectors.toList());
	}
	
	public List<Event> listEventsAllowedForRegistration(){
		return getAllowedForRegistration(
						getFutureEvents(
								listEvents()));
	}
	
	
	private List<Event> getFutureEvents(List<Event> events){
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		
		return events.stream()
				.filter(e -> {
					return e.getInitDate().after(yesterday.getTime());
					})
				.sorted(Comparator.comparing(Event::getInitDate))
				.collect(Collectors.toList());
	}
	
	private List<Event> getAllowedForRegistration(List<Event> events){
		return events.stream()
				.filter(e -> {
					return e.isAllowPlayersRegistration();
					})
				.collect(Collectors.toList());
	}

	public List<Player> getEventPlayers(Event eventSearched) {
		return playerServices.findByEvent(eventSearched);
	}

	public void setPlayerPresent(Long playerId, Boolean present) {
		if(playerId == null || present == null) return;
		
		Player player = playerServices.findById(playerId);
		
		if(player != null) {
			player.setPresent(present);
			playerServices.savePlayer(player);
		}
	}

	public List<Event> getStartedEvents() {
		return this.getStartedEvents(eventDao.findAll());
	}

	private List<Event> getStartedEvents(List<Event> events) {
		return getFutureEvents(events).stream()
				.filter(e -> {
					return e.isEventStarted();
				})
				.collect(Collectors.toList());
	}

	public List<Player> getEventPresentPlayers(Event selectedEvent) {
		return this.getEventPlayers(selectedEvent).stream()
				.filter(p -> {
					return p.isPresent();
				})
				.collect(Collectors.toList());
	}
	
}
