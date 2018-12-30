package dtd.acternity.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.CourierRating;

public interface RatingRepository extends JpaRepository<CourierRating, Long>{

	
	@Query("SELECT avg(r.rating) FROM CourierRating r, Courier c WHERE c.id = ?1 AND r.courier.id = c.id ")
	public Double calculateCourierRating(Long courierId);
}
