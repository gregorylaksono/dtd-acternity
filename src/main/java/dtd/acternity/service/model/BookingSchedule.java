package dtd.acternity.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class BookingSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	private String booking_id;
	
	private String rate_id;
	
	private String total_from;
	
	private String total_to;
	
	private String pickup_courier;
	
	private String delivery_courier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	public String getRate_id() {
		return rate_id;
	}

	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}

	public String getTotal_from() {
		return total_from;
	}

	public void setTotal_from(String total_from) {
		this.total_from = total_from;
	}

	public String getTotal_to() {
		return total_to;
	}

	public void setTotal_to(String total_to) {
		this.total_to = total_to;
	}

	public String getPickup_courier() {
		return pickup_courier;
	}

	public void setPickup_courier(String pickup_courier) {
		this.pickup_courier = pickup_courier;
	}

	public String getDelivery_courier() {
		return delivery_courier;
	}

	public void setDelivery_courier(String delivery_courier) {
		this.delivery_courier = delivery_courier;
	}
	
	
	
}
