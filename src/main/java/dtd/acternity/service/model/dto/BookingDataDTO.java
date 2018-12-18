package dtd.acternity.service.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import dtd.acternity.service.model.Location;

public class BookingDataDTO {

	@NotNull
	private String booking_id;

	@NotNull
	private Location from;
	
	@NotNull
	private Location to;
	
	@NotNull
	private String itemDescription;
	
	private Long courier_from_id;
	
	private Long courier_to_id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date expiration_date;
	
	private String rate_id;
	private String qr_data;
	private Double pickup_price;
	private Double deliver_price;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date deliverDate;
	
	private Double total_rate;
	
	
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
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

	public Long getCourier_from_id() {
		return courier_from_id;
	}
	public void setCourier_from_id(Long courier_from_id) {
		this.courier_from_id = courier_from_id;
	}
	public Long getCourier_to_id() {
		return courier_to_id;
	}
	public void setCourier_to_id(Long courier_to_id) {
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
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public Double getTotal_rate() {
		return total_rate;
	}
	public void setTotal_rate(Double total_rate) {
		this.total_rate = total_rate;
	}
	
	
}
