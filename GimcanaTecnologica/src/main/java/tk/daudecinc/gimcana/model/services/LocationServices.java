package tk.daudecinc.gimcana.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.gimcana.model.dao.LocationDAO;
import tk.daudecinc.gimcana.model.entities.Location;

@Service
public class LocationServices {
	
	@Autowired
	private LocationDAO locationDao;
	
	private List<Location> allLocations;
	
	public LocationServices() {
		allLocations = locationDao.findAll();
	}

	public List<Location> listLocations() {
		return allLocations;
	}

	public void saveLocation(Location location) {
		locationDao.save(location);
		
		allLocations = locationDao.findAll();
	}

	public Location getLocation(Long locationId) {
		return locationDao.findById(locationId).orElse(null);
	}

	public Location findByCode(String locationCode) {
		return locationDao.findByCode(locationCode);
	}

}
