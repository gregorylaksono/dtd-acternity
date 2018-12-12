package dtd.acternity.service.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="s_booking_stage_dtd")
public class BookingStageDTD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;

	private Double latitude;
	private Double longitude;
	private Long add_id_created_by;
	private Date createdOn;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="booking_data")
	private BookingTempDTD bookingData;
	private Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Long getAdd_id_created_by() {
		return add_id_created_by;
	}
	public void setAdd_id_created_by(Long add_id_created_by) {
		this.add_id_created_by = add_id_created_by;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public BookingTempDTD getBookingData() {
		return bookingData;
	}
	public void setBookingData(BookingTempDTD bookingData) {
		this.bookingData = bookingData;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
