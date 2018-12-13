package dtd.acternity.service.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.CourierOffer;

public interface CourierOfferRepository extends JpaRepository<CourierOffer, Long> {

	@Query("SELECT c FROM CourierOffer c WHERE c.bookingData.booking_id = ?1 AND c.acceptedOn IS NULL")
	List<CourierOffer> findCouriersByBookingData(String booking_id);

//	@Query(value= "SELECT c FROM CourierOffer c INNER JOIN BookingTempDTD d on c.bookingData = d"
//									 + " INNER JOIN Courier co on c.courier = co "
//									 + " WHERE co.add_id = ?1 AND d.courier_from_id IS NULL AND"
//									 + " d.courier_to_id IS NULL ",nativeQuery =true)
	@Query("SELECT c FROM CourierOffer c, BookingTempDTD d, Courier co WHERE c.bookingData = d"
			+ " AND c.courier = co AND co.add_id = ?1 AND d.courier_from_id IS NULL AND d.courier_to_id IS NULL")
	List<CourierOffer> findBookingOnWaitingCourierConfirm(Long user_id);

	@Query(value= "SELECT c FROM CourierOffer c INNER JOIN BookingTempDTD d on c.bookingData = d"
			 + " INNER JOIN Courier co on c.courier = co "
			 + " WHERE co.add_id = ?1 AND d.courier_from_id IS NOT NULL AND"
			 + " d.courier_to_id IS NOT NULL ")
	List<CourierOffer> findBookingCourierAlreadyPickUp(Long user_id);

	@Query("SELECT c FROM CourierOffer c, BookingTempDTD d WHERE c.bookingData=d AND"
			+ "  d.courier_from_id =?1 ")
	List<CourierOffer> findBookingOnDelivery(Long user_id );
	
	@Query("SELECT c FROM CourierOffer c, BookingTempDTD d WHERE c.bookingData=d AND"
			+ "  d.deliverDate IS NOT NULL AND d.courier_to_id = ?1 ")
	List<CourierOffer> findBookingOnDelivered(Long user_id);
	
}
