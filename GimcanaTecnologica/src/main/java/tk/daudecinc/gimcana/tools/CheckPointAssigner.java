package tk.daudecinc.gimcana.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import tk.daudecinc.gimcana.model.entities.CheckPoint;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.entities.Player;

@Component
public class CheckPointAssigner {
	
	private Random random = new Random();
	
	public CheckPoint assignCheckPoint(Player player) {
		CheckPoint checkPoint = new CheckPoint();
		
		checkPoint.setLocation(selectLocation(player, false, false));
		
		return checkPoint;
		
	}

	private Location selectLocation(Player player,
			boolean sameZoneAllowed,
			boolean repeatLocationAllowed) {
		CheckPoint currentCheckPoint = player.getCheckPoints().isEmpty() ? null : player.getCheckPoints().get(0);
		int zone = currentCheckPoint == null ? -1 : currentCheckPoint.getLocation().getZone();
		
		List<Location> availableLocations = player.getEvent().getEventLocations();
		if(!sameZoneAllowed) availableLocations = removeSameZoneLocations(availableLocations, zone);
		if(!repeatLocationAllowed) availableLocations = removeVisitedLocations(availableLocations, player.getCheckPoints());
		
		if(availableLocations.isEmpty()) {
			return selectLocation(player,  true, sameZoneAllowed ? true : repeatLocationAllowed);
		}
		
		return availableLocations.get(random.nextInt(availableLocations.size()));
		
	}

	private List<Location> removeVisitedLocations(List<Location> availableLocations, List<CheckPoint> checkPoints) {
		List<Location> visitedLocations = new ArrayList<>();
		for(CheckPoint currentCheckPoint : checkPoints) {
			visitedLocations.add(currentCheckPoint.getLocation());
		}
		
		return availableLocations.stream()
				 .filter(l -> {
					 return !visitedLocations.contains(l);
				 	})
				 .collect(Collectors.toList());
	}

	private List<Location> removeSameZoneLocations(List<Location> availableLocations, int zone) {
		return availableLocations.stream()
				 .filter(l -> {
					 return l.getZone() !=  zone;
				 	})
				 .collect(Collectors.toList());
	}

}
