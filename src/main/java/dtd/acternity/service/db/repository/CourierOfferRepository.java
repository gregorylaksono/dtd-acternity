package dtd.acternity.service.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.CourierOffer;

public interface CourierOfferRepository extends JpaRepository<CourierOffer, Long> {

	@Query("SELECT c FROM CourierOffer c WHERE c.booking_id = ?1 AND c.acceptedOn IS NULL")
	List<CourierOffer> findCouriersByBookingData(String booking_id);

	



}
