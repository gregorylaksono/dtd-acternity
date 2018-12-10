package dtd.acternity.service.model;

import java.util.Date;

public class Schedule {

	private Long rate_id;
	private Date flight_on;
	private Double total_price;
	
	public Schedule(Long rate_id, Date flight_on, Double total_price){
		setRate_id(rate_id);
		setFlight_on(flight_on);
		setTotal_price(total_price);
	}
	public Long getRate_id() {
		return rate_id;
	}
	public void setRate_id(Long rate_id) {
		this.rate_id = rate_id;
	}
	public Date getFlight_on() {
		return flight_on;
	}
	public void setFlight_on(Date flight_on) {
		this.flight_on = flight_on;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	
	
	
}
