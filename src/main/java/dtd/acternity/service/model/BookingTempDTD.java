package dtd.acternity.service.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
public class BookingTempDTD {
	public enum BookingState{
		WAITING_COURIER_CONFIRM, 
		COURIER_PICKUP, 
		DELIVERY, 
		DELIVERED, 
		UNKNOWN;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	private String booking_id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="from_location")
	private Location from;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="to_location")
	private Location to;
	
	private String itemDescription;
	private Long courier_from_id;
	private Long courier_to_id;
	private Date expiration_date;
	private String rate_id;
	private String qr_data;
	private Double pickup_price;
	private Double deliver_price;
	private Date deliverDate;
	private boolean isSameCity;
	
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
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public Date getExpiration_date() {
		if(expiration_date == null)return null;
		else return (Date) expiration_date.clone();
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
	public Double getPickup_price() {
		return pickup_price;
	}
	public void setPickup_price(Double pickup_price) {
		this.pickup_price = pickup_price;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Double getDeliver_price() {
		return deliver_price;
	}
	public void setDeliver_price(Double deliver_price) {
		this.deliver_price = deliver_price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public boolean isSameCity() {
		return isSameCity;
	}
	public void setSameCity(boolean isSameCity) {
		this.isSameCity = isSameCity;
	}

	
}
