package dtd.acternity.service.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CourierRating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid", columnDefinition = "serial")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="courier")
	private Courier courier;
	
	private Short rating;
	private Integer add_id_user;
	private Date createdOn;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public Short getRating() {
		return rating;
	}
	public void setRating(Short rating) {
		this.rating = rating;
	}
	public Integer getAdd_id_user() {
		return add_id_user;
	}
	public void setAdd_id_user(Integer add_id_user) {
		this.add_id_user = add_id_user;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
}
