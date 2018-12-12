package dtd.acternity.service.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;

public interface BookingStageRepository extends JpaRepository<BookingStageDTD, Long> {
	
	@Query("SELECT b FROM BookingStageDTD b WHERE b.bookingData = ?1")
	List<BookingStageDTD> findByBookingData(BookingTempDTD bookingData);
}
