package dtd.acternity.util;

import dtd.acternity.service.model.BookingTempDTD.BookingState;

public class GeneralUtil {

	public static BookingState convertToBookingState(int stateId){
		switch(stateId){
		case 1: return BookingState.WAITING_COURIER_CONFIRM;
		case 2: return BookingState.COURIER_PICKUP; 
		case 3: return BookingState.DELIVERY;
		case 4: return BookingState.DELIVERED;
		default: return BookingState.UNKNOWN;
		}
	}
}
