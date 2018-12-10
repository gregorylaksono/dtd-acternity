package dtd.acternity.service.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.jpa.repository.JpaRepository;

import dtd.acternity.service.model.BookingStageDTD;

public interface BookingStageRepository extends JpaRepository<BookingStageDTD, Long> {
	
}
