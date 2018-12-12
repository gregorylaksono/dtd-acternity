package dtd.acternity.service.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import dtd.acternity.service.model.BookingTempDTD;

public interface BookingDTDRepository extends JpaRepository<BookingTempDTD, String> {
	
	@Query("SELECT d FROM BookingTempDTD d where d.booking_id = ?1")
	public BookingTempDTD findDataById(String booking_id);

	@Query("SELECT d FROM BookingTempDTD d WHERE d.courier_from_id IS NULL OR d.courier_to_id IS NULL")
	public List<BookingTempDTD> findBookingOnWaitingCourierConfirm();

	@Query("SELECT d FROM BookingTempDTD d WHERE d.courier_from_id IS NOT NULL AND d.courier_to_id NOT IS NULL")
	public List<BookingTempDTD> findBookingCourierAlreadyPickUp();

	@Query("SELECT d FROM BookingTempDTD d WHERE d.deliverDate IS NOT NULL ")
	public List<BookingTempDTD> findBookingOnDelivered();

	@Query("SELECT d FROM BookingTempDTD d WHERE d.courier_from_id IS NOT NULL")
	public List<BookingTempDTD> findBookingOnDelivery();

	
}
