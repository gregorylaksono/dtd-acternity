package dtd.acternity.service.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

public class CourierOfferDTO extends CourierCommonInfoDTO{
	
	private Long id;
	private Double price;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date acceptedOn;
	private String address;
	private boolean isPickup;
	private boolean isStart;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	
}
