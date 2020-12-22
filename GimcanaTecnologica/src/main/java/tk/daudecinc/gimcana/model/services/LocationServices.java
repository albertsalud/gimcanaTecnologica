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

	public List<Location> listLocations() {
		return locationDao.findAll();
	}

	public void saveLocation(Location location) {
		locationDao.save(location);
	}

	public Location getLocation(Long locationId) {
		return locationDao.findById(locationId).orElse(null);
	}

}
