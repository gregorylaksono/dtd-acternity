package dtd.acternity.service.model.dto;

import java.util.Date;


import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

public class CourierOfferDTO {
	
	private Location location;
	private Double price;
	private Date acceptedOn;
	
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
	
	
}
