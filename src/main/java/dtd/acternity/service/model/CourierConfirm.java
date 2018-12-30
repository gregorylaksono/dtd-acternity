package dtd.acternity.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CourierConfirm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="courier")
	private CourierOffer courier_offer;
	
	@ManyToOne
	@JoinColumn(name="booking")
	private BookingTempDTD booking;
	
	private Date createdOn;
	
	//1 for pickup, 2 for delivery
	private Short type;
	
	public CourierConfirm(){}
	
	public CourierConfirm(CourierOffer c, BookingTempDTD booking, Date d, Short type){
		setCourier_offer(c);
		setBooking(booking);
		setCreatedOn(d);
		setType(type);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public CourierOffer getCourier_offer() {
		return courier_offer;
	}

	public void setCourier_offer(CourierOffer courier_offer) {
		this.courier_offer = courier_offer;
	}

	public BookingTempDTD getBooking() {
		return booking;
	}

	public void setBooking(BookingTempDTD booking) {
		this.booking = booking;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
	
	
}
