package dtd.acternity.service.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.CourierConfirm;

public interface CourierConfirmRepository extends JpaRepository<CourierConfirm, Long> {

	@Query("SELECT c FROM CourierConfirm c WHERE c.type in (1,2) HAVING COUNT(DISTINCT c.type ) = 2 ")
	List<CourierConfirm> findOfferOnPickupAndDeliveryAvailableByBookingId(String booking_id);


}
