package dtd.acternity.dtd;

import java.util.Date;


import dtd.acternity.service.model.Location;

public class BookingData {

	private String booking_id;
	private String address_from;
	private String address_to;

	private Location from;
	
	private Location to;
	private String item_weight;
	private String item_dimension;
	private String courier_from_id;
	private String courier_to_id;
	private Date expiration_date;
	private String rate_id;
	private String qr_data;
	private Double pickup_price;
	private Double deliver_price;
	private Date deliverDate;
	
	
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getAddress_from() {
		return address_from;
	}
	public void setAddress_from(String address_from) {
		this.address_from = address_from;
	}
	public String getAddress_to() {
		return address_to;
	}
	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}
	public Location getFrom() {
		return from;
	}
	public void setFrom(Location from) {
		this.from = from;
	}
	public Location getTo() {
		return to;
	}
	public void setTo(Location to) {
		this.to = to;
	}
	public String getItem_weight() {
		return item_weight;
	}
	public void setItem_weight(String item_weight) {
		this.item_weight = item_weight;
	}
	public String getItem_dimension() {
		return item_dimension;
	}
	public void setItem_dimension(String item_dimension) {
		this.item_dimension = item_dimension;
	}
	public String getCourier_from_id() {
		return courier_from_id;
	}
	public void setCourier_from_id(String courier_from_id) {
		this.courier_from_id = courier_from_id;
	}
	public String getCourier_to_id() {
		return courier_to_id;
	}
	public void setCourier_to_id(String courier_to_id) {
		this.courier_to_id = courier_to_id;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getRate_id() {
		return rate_id;
	}
	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}
	public String getQr_data() {
		return qr_data;
	}
	public void setQr_data(String qr_data) {
		this.qr_data = qr_data;
	}
	public Double getPickup_price() {
		return pickup_price;
	}
	public void setPickup_price(Double pickup_price) {
		this.pickup_price = pickup_price;
	}
	public Double getDeliver_price() {
		return deliver_price;
	}
	public void setDeliver_price(Double deliver_price) {
		this.deliver_price = deliver_price;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	
	
}
