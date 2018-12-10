package dtd.acternity.service.model;

import javax.persistence.Entity;

public class Location {
	
	private String address;
	private Double latitude;
	private Double longitude;
	private String add_id_target;
	
	public Location(String add_id_target,String address,  Double lat, Double lon) {
		this.address = address;
		this.latitude = lat;
		this.longitude = lon;
		this.add_id_target=add_id_target;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAdd_id_target() {
		return add_id_target;
	}
	public void setAdd_id_target(String add_id_target) {
		this.add_id_target = add_id_target;
	}


	
}
