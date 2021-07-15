package tk.daudecinc.gimcana.model.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.LocationDAO;
import tk.daudecinc.gimcana.model.entities.Location;

@Service
public class LocationServices {
	
	@Autowired
	private LocationDAO locationDao;
	
	private List<Location> allLocations;
	

	public List<Location> listLocations() {
		return allLocations;
	}

	public void saveLocation(Location location) {
		locationDao.save(location);
		
		initAllLocations();
	}

	public Location getLocation(Long locationId) {
		return locationDao.findById(locationId).orElse(null);
	}

	public Location findByCode(String locationCode) {
		return locationDao.findByCode(locationCode);
	}
	
	@PostConstruct
	private void initAllLocations() {
		allLocations = locationDao.findAll();
		sortByName();
		
	}

	private void sortByName() {
		if(allLocations != null) {
			allLocations = allLocations.stream()
					.sorted(Comparator.comparing(Location::getName))
					.collect(Collectors.toList());
		}
		
	}

}
