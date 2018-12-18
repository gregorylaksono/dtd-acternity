package dtd.acternity.service.model.dto;

import dtd.acternity.service.model.Location;

public class CourierCommonInfoDTO {

	protected String booking_id;
	protected CourierDTO courier;
	protected Location location;
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	public CourierDTO getCourier() {
		return courier;
	}
	public void setCourier(CourierDTO courier) {
		this.courier = courier;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
