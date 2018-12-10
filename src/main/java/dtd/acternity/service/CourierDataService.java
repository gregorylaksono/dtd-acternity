package dtd.acternity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.act.common.interfaces.Webservice;
import de.act.common.types.nonstaticobjects.CourierInformation;
import dtd.acternity.service.db.repository.CourierRepository;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

@Service
public class CourierDataService implements ICourierDataService {

	@Autowired
	private CourierRepository courierRepository;
	
	@Autowired
	private Webservice mobileService;
	
	@Override
	public List<CourierInformation> getCourierBasedOnCoordinate(Location location) {
		
		List<CourierInformation> pickupResult = 
				mobileService.getCheckRangeInCourierByLatitudeLangitude(
						location.getAdd_id_target(), String.valueOf(location.getLatitude()), 
						String.valueOf(location.getLongitude()));

		return pickupResult;
	}

	@Override
	public CourierRepository getCourierRepository() {
		return courierRepository;
	}

}
