package dtd.acternity.service;

import java.util.List;

import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.CourierOffer;

public interface IBookingService {
	
	BookingTempDTD getBookingDataByBookingId(String bookingId);

	List<BookingTempDTD> getBookingByState(BookingTempDTD.BookingState state);
	List<BookingStageDTD> getBookingStageByBookingId(String bookingId);
	
	List<CourierOffer> getCourierJobByBookingState(BookingTempDTD.BookingState state);
	
	List<BookingSchedule> getScheduleByBookingId(String bookingId);
}
