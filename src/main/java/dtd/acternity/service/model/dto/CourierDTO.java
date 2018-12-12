package dtd.acternity.service.model.dto;



import dtd.acternity.service.model.Location;

public class CourierDTO {

	private Long id;
	private String name;
	private Double price_per_km;
	
	private Location location;
	private String token_device;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	
	
}
