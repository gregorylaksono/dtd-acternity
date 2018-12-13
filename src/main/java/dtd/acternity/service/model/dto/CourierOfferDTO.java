package dtd.acternity.service.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

public class CourierOfferDTO {
	
	private Long id;
	private Location location;
	private Double price;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date acceptedOn;
	private String address;
	private CourierDTO courier;
	private String booking_id;
	private boolean isPickup;
	private boolean isStart;
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
	public Date getAcceptedOn() {
		return acceptedOn;
	}
	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
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
	public CourierDTO getCourier() {
		return courier;
	}
	public void setCourier(CourierDTO courier) {
		this.courier = courier;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
