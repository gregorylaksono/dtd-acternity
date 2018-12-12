package dtd.acternity.service;

import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierOffer;

public interface IMainService {
	public Boolean courierUpdatePosition(Long courier_id, Double latitude, Double longitude);
	public String saveBookingRequestData(BookingTempDTD bookingData);
	public Boolean customerChooseRate(Long user_id, Long rate_id, String booking_id);
	public Boolean courierConfirmPickUp(CourierOffer courierOffer);
	public Boolean doPackageHandover(Double latitude, Double longitude, Long courier_id, String booking_id, String qr);
	public Boolean deliverBooking(Double lat, Double lon, String booking_id, String user_id);
}
