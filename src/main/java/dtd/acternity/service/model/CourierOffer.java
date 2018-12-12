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
import javax.persistence.Transient;

@Entity
public class CourierOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	private String address;
	
	@Transient
	private Location location;
	private Double price;
	private Date acceptedOn;
	
	@Transient
	private Courier courier;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="booking_data")
	private BookingTempDTD bookingData;
	
	private boolean isPickup;
	private boolean isStart;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setCourier(Courier c) {
		this.courier = c;
	}
	
	public BookingTempDTD getBookingData() {
		return bookingData;
	}
	public void setBookingData(BookingTempDTD bookingData) {
		this.bookingData = bookingData;
	}
	public Courier getCourier() {
		return courier;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		CourierOffer c = (CourierOffer) obj;
		return this.courier.getId().longValue() == c.getCourier().getId().longValue();
	}
	public boolean isPickup() {
		return isPickup;
	}
	public void setPickup(boolean isPickup) {
		this.isPickup = isPickup;
	}
	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public Date getAcceptedOn() {
		return acceptedOn;
	}
	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}
	
	
	
}
