package dtd.acternity.service;

import java.util.List;

import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierOffer;

public interface IMainService {
	public Boolean courierUpdatePosition(Long courier_id, Double latitude, Double longitude);
	public String saveBookingRequestData(BookingTempDTD bookingData);
	public Boolean customerChooseRate(Long user_id, Long rate_id, String booking_id);
	public Boolean customerChooseCourier(String booking_id, Long courier_pickup, Long courier_delivery);
	public Boolean courierConfirmPickUp(CourierOffer courierOffer);
	public Boolean doPackageHandover(Double latitude, Double longitude, Long courier_id, String booking_id, String qr);
	public Boolean deliverBooking(Double lat, Double lon, String booking_id, String user_id);
	public Boolean registerUser(String name, String email,String street, String city, String telp,String country,
			Double latitude, Double longitude);
	List<CourierOffer> getCourierChoiceByBookingId(String bookingId);
}
