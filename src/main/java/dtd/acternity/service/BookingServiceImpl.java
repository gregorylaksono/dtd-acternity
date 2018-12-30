package dtd.acternity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.act.common.interfaces.ILogin;
import de.act.common.interfaces.Webservice;
import dtd.acternity.service.db.repository.BookingDTDRepository;
import dtd.acternity.service.db.repository.BookingScheduleRepository;
import dtd.acternity.service.db.repository.BookingStageRepository;
import dtd.acternity.service.db.repository.CourierOfferRepository;
import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.BookingTempDTD.BookingState;
import dtd.acternity.util.RestUtil;
import dtd.acternity.service.model.CourierOffer;

@Service
public class BookingServiceImpl implements IBookingService {
	@Autowired
	private BookingStageRepository bookingStageRepository;

	@Autowired
	private BookingDTDRepository bookingTempRepository;

	@Autowired
	private ICourierDataService courierDataService;

	@Autowired
	private IPushNotificationService pushNotificationService;

	@Autowired
	private CourierOfferRepository courierOfferRepository;

	@Autowired
	private BookingScheduleRepository bookingScheduleRepository;

	@Autowired
	private RestUtil restConnectionUtil;

	@Autowired
	private Webservice mobileService;

	@Autowired
	private ILogin loginService; 
	@Override
	public BookingTempDTD getBookingDataByBookingId(String bookingId) {

		return bookingTempRepository.findDataById(bookingId);
	}

	@Override
	public List<BookingTempDTD> getBookingByState(BookingState state) {
		List<BookingTempDTD> result = null;
		switch(state){
		case WAITING_COURIER_CONFIRM : result = bookingTempRepository.findBookingOnWaitingCourierConfirm();break;
		case COURIER_PICKUP: result = bookingTempRepository.findBookingCourierAlreadyPickUp();break;
		case DELIVERY : result = bookingTempRepository.findBookingOnDelivery(); break;
		case DELIVERED: result = bookingTempRepository.findBookingOnDelivered();break;
		case UNKNOWN : result = new ArrayList();break;
		}


		return result;
	}

	@Override
	public List<BookingStageDTD> getBookingStageByBookingId(String bookingId) {
		BookingTempDTD bookingData = bookingTempRepository.findDataById(bookingId);
		List<BookingStageDTD> bookingStages = bookingStageRepository.findByBookingData(bookingData);
		return bookingStages;
	}

	@Override
	public List<CourierOffer> getCourierJobByBookingState(BookingState state, Long user_id) {
		List<CourierOffer> result = null;

		switch(state){
			case WAITING_COURIER_CONFIRM : result = courierOfferRepository.findBookingOnWaitingCourierConfirm(user_id);break;
			case COURIER_PICKUP: result = courierOfferRepository.findBookingCourierAlreadyPickUp(user_id);break;
			case DELIVERY : result = courierOfferRepository.findBookingOnDelivery(user_id); break;
			case DELIVERED: result = courierOfferRepository.findBookingOnDelivered(user_id);break;
			case UNKNOWN : result = new ArrayList();break;
		}

		return result;
	}

	@Override
	public List<BookingSchedule> getScheduleByBookingId(String bookingId) {
		return bookingScheduleRepository.findBookingScheduleByBookingId(bookingId);
	}

	@Override
	public List<BookingTempDTD> getBookingByUserId(Long user_id) {
		List<BookingTempDTD> bookings =  bookingTempRepository.getBookingByUserId(user_id);
		return bookings;
	}


}
