package dtd.acternity.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="courier")
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	private String name;
	private Double price_per_km;
	
	@Transient
	private Location location;
	private String token_device;

	public Courier(Long id, String name, Double price, Location location, String token) {
		setId(id);
		setName(name);
		setPrice_per_km(price);
		setLocation(location);
		setToken_device(token);

	}
	
	public Courier() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice_per_km() {
		return price_per_km;
	}
	public void setPrice_per_km(Double price_per_km) {
		this.price_per_km = price_per_km;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getToken_device() {
		return token_device;
	}
	public void setToken_device(String token_device) {
		this.token_device = token_device;
	}



}
