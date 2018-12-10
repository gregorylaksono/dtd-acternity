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
	public BookingTempDTD getDataById(String booking_id);

	
}
