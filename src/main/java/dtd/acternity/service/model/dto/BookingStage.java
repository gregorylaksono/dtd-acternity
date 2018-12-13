package dtd.acternity.service.model.dto;

import java.util.Date;


import dtd.acternity.service.model.BookingTempDTD;

public class BookingStage {


	private Double latitude;
	private Double longitude;
	private Long add_id_created_by;
	private Date createdOn;
	

	private String booking_id;
	private Double price;
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
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
