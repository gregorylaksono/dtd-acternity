package dtd.acternity.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	private String address;
	private Double latitude;
	private Double longitude;
	private Long add_id;
	
	public Location(Long add_id,String address,  Double lat, Double lon) {
		this.address = address;
		this.latitude = lat;
		this.longitude = lon;
		this.add_id = add_id;
	}
	
	public Location(){}
	
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

	public Long getAdd_id() {
		return add_id;
	}

	public void setAdd_id(Long add_id) {
		this.add_id = add_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
}
