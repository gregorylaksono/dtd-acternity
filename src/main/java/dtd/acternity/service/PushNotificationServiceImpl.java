package dtd.acternity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.act.common.dtd.ScheduleDoorToDoor;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.Schedule;

@Service
public class PushNotificationServiceImpl implements IPushNotificationService {

	@Override
	public void searchAndsendScheduleToUser(List<ScheduleDoorToDoor> scheduleData, String booking_id) {
		
	}
	@Override
	public BookingStageDTD getCourierBookingStage(String booking_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendOfferToCourier(List<CourierOffer> offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPickupOrderToCourier(Courier c, BookingTempDTD temp) {
		// TODO Auto-generated method stub
		
	}
//	private ScheduleRepository scheduleDataService ;
//	private IDataService<BookingStageDTD, Long> bookingStageDataService ;
//	private IDataService<BookingTempDTD, String> bookingTempDbService;
//	public void searchAndsendScheduleToUser(String booking_id) {
//		
//	}
//
//	public BookingStageDTD getCourierBookingStage(String booking_id) {
//		List<BookingStageDTD> all = bookingStageDataService.getAll();
//		BookingStageDTD data = null;
//		for(BookingStageDTD d: all){
//			if(d.getBookingData().getBooking_id().equals(booking_id)){
//				if(data == null) data = d;
//				else{
//					if(data.getCreatedOn().before(d.getCreatedOn())){
//						data = d;
//					}
//				}
//			}
//		}
//		return data;
//	}
//
//	public void sendOfferToCourier(CourierOffer offer) {
//		
//	}
//
//	public void sendPickupOrderToCourier(Courier c, BookingTempDTD temp) {
//		
//		
//	}


}
