package dtd.acternity.service.model;

public class User {
	private Long id;
	private String name;
	private Location location;
	
	public User(Long id, String name, Location location){
		setId(id);
		setName(name);
		setLocation(location);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
