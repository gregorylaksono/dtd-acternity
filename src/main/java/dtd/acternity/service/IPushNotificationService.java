package dtd.acternity.service;

import java.util.List;

import de.act.common.dtd.ScheduleDoorToDoor;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierOffer;

public interface IPushNotificationService {

	public void searchAndsendScheduleToUser(List<ScheduleDoorToDoor> scheduleData, String booking_id);
	public BookingStageDTD getCourierBookingStage(String booking_id);
	public void sendOfferToCourier(List<CourierOffer> shippmentOffer);
	public void sendPickupOrderToCourier(Courier c, BookingTempDTD temp);
	
}
