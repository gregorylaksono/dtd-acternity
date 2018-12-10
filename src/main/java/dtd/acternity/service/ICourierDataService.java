package dtd.acternity.service;

import java.util.List;

import de.act.common.types.nonstaticobjects.CourierInformation;
import dtd.acternity.service.db.repository.CourierRepository;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

public interface ICourierDataService {

	public List<CourierInformation> getCourierBasedOnCoordinate(Location location);
	public CourierRepository getCourierRepository();
}
