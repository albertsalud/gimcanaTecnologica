package tk.daudecinc.gimcana.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tk.daudecinc.gimcana.model.entities.CheckPoint;
import tk.daudecinc.gimcana.model.entities.Location;
import tk.daudecinc.gimcana.model.entities.Player;
import tk.daudecinc.gimcana.model.services.LocationServices;

@Component
public class CheckPointAssigner {
	
	@Autowired
	private LocationServices locationServices;
	
	private Random random = new Random();
	
	public CheckPoint assignCheckPoint(Player player) {
		CheckPoint checkPoint = new CheckPoint();
		
		checkPoint.setLocation(selectLocation(player.getCheckPoints(), false, false));
		
		return checkPoint;
		
	}

	private Location selectLocation(List<CheckPoint> checkPoints,
			boolean sameZoneAllowed,
			boolean repeatLocationAllowed) {
		CheckPoint currentCheckPoint = checkPoints.isEmpty() ? null : checkPoints.get(0);
		int zone = currentCheckPoint == null ? -1 : currentCheckPoint.getLocation().getZone();
		
		List<Location> availableLocations = locationServices.listLocations();
		if(!sameZoneAllowed) availableLocations = removeSameZoneLocations(availableLocations, zone);
		if(!repeatLocationAllowed) availableLocations = removeVisitedLocations(availableLocations, checkPoints);
		
		if(availableLocations.size() == 0) {
			return selectLocation(checkPoints,  true, sameZoneAllowed ? true : repeatLocationAllowed);
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
