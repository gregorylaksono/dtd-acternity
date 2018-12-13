package dtd.acternity.service.model;

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
public class Courier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	private Long add_id;
	private String name;
	private Double price_per_km;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="location")
	private Location location;
	
	private String token_device;

	public Courier(Long id, String name, Double price, Location location, String token) {
		setAdd_id(id);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdd_id() {
		return add_id;
	}

	public void setAdd_id(Long add_id) {
		this.add_id = add_id;
	}




}
