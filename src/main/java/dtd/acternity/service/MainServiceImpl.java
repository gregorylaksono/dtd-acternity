package dtd.acternity.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.act.common.dtd.ScheduleDoorToDoor;
import de.act.common.interfaces.ILogin;
import de.act.common.interfaces.ISession;
import de.act.common.interfaces.Webservice;
import de.act.common.types.User;
import de.act.common.types.nonstaticobjects.CourierInformation;
import dtd.acternity.service.db.repository.BookingDTDRepository;
import dtd.acternity.service.db.repository.BookingScheduleRepository;
import dtd.acternity.service.db.repository.BookingStageRepository;
import dtd.acternity.service.db.repository.CourierConfirmRepository;
import dtd.acternity.service.db.repository.CourierOfferRepository;
import dtd.acternity.service.db.repository.CourierRepository;
import dtd.acternity.service.db.repository.RatingRepository;
import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierConfirm;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.Location;
import dtd.acternity.service.model.Schedule;
import dtd.acternity.util.RestUtil;

@Service
public class MainServiceImpl implements IMainService {

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

	@Autowired
	private CourierConfirmRepository courierConfirmRepository;

	@Autowired
	private CourierRepository courierRepository;
	
	@Autowired
	private RatingRepository ratingRepository;

	@Transactional(propagation=Propagation.REQUIRED)
	public String saveBookingRequestData(BookingTempDTD bookingData) {
		String id = null;
		try{
			String qr = UUID.randomUUID().toString();
			id = String.valueOf(Math.abs(qr.hashCode()));
			bookingData.setQr_data(qr);
			bookingData.setBooking_id(id);
			
			int distance = restConnectionUtil.getDistanceByTwoPoints(bookingData.getFrom(), bookingData.getTo());
			if(distance <= 49){
				bookingData.setSameCity(true);
			}
			bookingTempRepository.save(bookingData);
			
			if(!bookingData.isSameCity()){		
				createPickupAndDeliveryOffer(bookingData);
			}else{
				createSameCityOffer(bookingData);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return id;
	}


	private void createSameCityOffer(BookingTempDTD bookingData) {
		List<CourierOffer> shippmentOffer = createCourierOffer(bookingData, true);
		CourierOffer offer = shippmentOffer.get(0);
		CourierOffer delivery = new CourierOffer();
		delivery.setLocation(offer.getLocation());
		delivery.setPickup(false);
		Courier courier = courierRepository.getOne(offer.getCourier().getId());
		double distance = restConnectionUtil.getDistanceByTwoPoints(bookingData.getFrom(), bookingData.getTo()) / 1000;
		double price = distance * courier.getPrice_per_km();
		delivery.setPrice(price);
		delivery.setBookingData(bookingData);
		shippmentOffer.add(delivery);
		
		courierOfferRepository.saveAll(shippmentOffer);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private List<CourierOffer> createCourierOffer(BookingTempDTD bookingData, boolean isPickup) {
		Location location = null;
		if(isPickup){
			location = bookingData.getFrom();
		}
		else{
			location = bookingData.getTo();
		}
		List<CourierInformation> couriersInfo = courierDataService.getCourierBasedOnCoordinate(location);
		List<CourierOffer> offers = new ArrayList();
		BookingTempDTD data = bookingTempRepository.findDataById(bookingData.getBooking_id());
		for(CourierInformation ci: couriersInfo){
			if(ci.getRatePer() == null) continue;

			CourierOffer offer = new CourierOffer();

			Location courierLocation = new Location((long)ci.getCourierAddId(),ci.getCourierAddName(),Double.parseDouble(ci.getCourierLatitude()),Double.parseDouble(ci.getCourierLangitude()));
			Courier c = new Courier((long)ci.getCourierAddId(), ci.getCourierAddName(), ci.getRatePer(), courierLocation,"");
			offer.setCourier(c);

			if(offers.contains(offer)) continue;

			offer.setPickup(isPickup);
			offer.setLocation(location);
			double distance = restConnectionUtil.getDistanceByTwoPoints(bookingData.getFrom(), courierLocation) / 1000;
			double price =  ci.getRatePer() * distance;
			offer.setPrice(price);
			offer.setBookingData(data);
			offers.add(offer);

		}
		return offers;
	}


	private void createPickupAndDeliveryOffer(BookingTempDTD bookingData) {
		List<CourierOffer> shippmentOffer = createCourierOffer(bookingData, true);
		List<CourierOffer> deliveryOffer = createCourierOffer(bookingData, false);
		courierOfferRepository.saveAll(shippmentOffer);
		courierOfferRepository.saveAll(deliveryOffer);
		pushNotificationService.sendOfferToCourier(shippmentOffer);
		pushNotificationService.sendOfferToCourier(deliveryOffer);
		
	}


	public Boolean courierConfirmPickUp(CourierOffer courierOffer) {
		if(courierOffer.getAcceptedOn() != null){
			BookingTempDTD data = bookingTempRepository.findDataById(courierOffer.getBookingData().getBooking_id());
			if(courierOffer.isPickup() && data.getCourier_from_id() != null) return false;
			else if(!courierOffer.isPickup() && data.getCourier_to_id() != null) return false;

			if(data.isSameCity()){
				List<CourierOffer> offers = courierOfferRepository.findCouriersByBookingData(data.getBooking_id());
				offers.forEach(e->{
					e.setAcceptedOn(new Date());
					Short type = 1;
					if(!e.isPickup()){
						type = 2;
					}
					courierConfirmRepository.save(new CourierConfirm(e, data,new Date(), type));
				});
				courierOfferRepository.saveAll(offers);
			}else{
				CourierOffer offer = courierOfferRepository.findById(courierOffer.getId()).get();
				offer.setAcceptedOn(new Date());
				courierOfferRepository.save(offer);
				
				CourierOffer c = courierOfferRepository.findById(courierOffer.getId()).get();
				Short type = 1;
				if(!courierOffer.isPickup())type = 2;
				
				courierConfirmRepository.save(new CourierConfirm(c, data,new Date(), type));
			}
			 List<CourierConfirm> result = courierConfirmRepository.findOfferOnPickupAndDeliveryAvailableByBookingId(data.getBooking_id());
			if(result.size() == 2){
				pushNotificationService.notifyCustomerCourierAvailable(data.getBooking_id());
			}

		}
		return false;
	}

	public Boolean doPackageHandover(Double latitude, Double longitude, Long subject_id, String booking_id, String qr) {
		BookingTempDTD dataTemp = bookingTempRepository.findDataById(booking_id);
		if(dataTemp.getQr_data().equals(qr)){
			BookingStageDTD bookingStageData = buildBookingStage(latitude, longitude, subject_id, booking_id); 
			bookingStageRepository.save(bookingStageData);
			return true;
		}
		return false;
	}

	public Boolean customerChooseRate(Long user_id, Long rate_id, String booking_id) {
		//		Schedule l = scheduleDataService.getDataById(rate_id);
		BookingTempDTD temp = bookingTempRepository.findDataById(booking_id);
		temp.setRate_id(String.valueOf(rate_id));

		bookingTempRepository.save(temp);
		ISession session = loginService.login("bold", "ffw");
		Map result = mobileService.createBookingDoorToDoorAct(session.getUser(), temp.getBooking_id(), 
				String.valueOf(rate_id), true);
		//		pushNotificationService.sendPickupOrderToCourier(c, temp);
		result.size();
		return true;
	}

	public Boolean courierUpdatePosition(Long courier_id, Double latitude, Double longitude) {
		Courier courier = courierDataService.getCourierRepository().findById(courier_id).get();

		courier.getLocation().setLatitude(latitude);
		courier.getLocation().setLongitude(longitude);
		courierDataService.getCourierRepository().save(courier);
		return true;
	}

	public Boolean deliverBooking(Double lat, Double lon, String booking_id, String user_id) {
		BookingTempDTD booking = bookingTempRepository.findDataById(booking_id);
		booking.setDeliverDate(new Date());
		BookingStageDTD stage = buildBookingStage(lat, lon, Long.parseLong(user_id), booking_id);
		bookingStageRepository.save(stage);
		bookingTempRepository.save(booking);
		return true;
	}



	private void saveBookingSchedule(BookingTempDTD data, List<ScheduleDoorToDoor> schedule){

		for(ScheduleDoorToDoor d: schedule){
			BookingSchedule s = new BookingSchedule();
			s.setBooking_id(data.getBooking_id());
			s.setRate_id(d.getRateId());
			s.setTotal_from(d.getTotal_fee_from());
			s.setTotal_to(d.getTotal_fee_to());
			bookingScheduleRepository.save(s);
		}
	}

	private List<ScheduleDoorToDoor> filterData(List<ScheduleDoorToDoor> scheduleData, List<CourierOffer> couriers) {
		//		List<ScheduleDoorToDoor> result = new ArrayList();
		//		List<Long> las = couriers.stream().map(e-> e.getCourier().getId()).collect(Collectors.toList());
		//		
		//		for(ScheduleDoorToDoor d: scheduleData){
		//			if(las.contains(Long.parseLong(d.getShipper_add_id())) && las.contains(Long.parseLong(d.getConsignee_add_id()))){
		//				
		//			}
		//		}
		return scheduleData;
	}
	private List<ScheduleDoorToDoor> findSchedule(BookingTempDTD data) {
		ISession session = loginService.login("bold", "ffw");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 2);

		List<ScheduleDoorToDoor> result = mobileService.getDepAndArrAndRateByDistanceDepartureToDestinationsDTD(data.getBooking_id(), 
				data.getFrom().getAddress(), data.getTo().getAddress(),
				now ,c.getTime(), 
				new String[]{data.getItemDescription()}, session.getUser(), 
				data.getFrom().getAdd_id().intValue(), 
				String.valueOf(data.getFrom().getLatitude()), String.valueOf(data.getFrom().getLongitude()),
				data.getTo().getAdd_id().intValue(),
				String.valueOf(data.getTo().getLatitude()), String.valueOf(data.getTo().getLongitude()),"");
		return result;
	}


	private  BookingStageDTD buildBookingStage(Double latitude, Double longitude, Long user_id, String booking_id){
		BookingStageDTD data = new BookingStageDTD();
		data.setId(new Date().getTime());
		data.setLatitude(latitude);
		data.setLongitude(longitude);
		data.setAdd_id_created_by(user_id);
		BookingTempDTD bookingData = bookingTempRepository.findDataById(booking_id);
		data.setBookingData(bookingData);
		data.setCreatedOn(new Date());
		return data;
	}


	@Override
	public Boolean registerUser(String name, String email, String street, String city, String telp, String country,
			Double latitude, Double longitude) {
		ISession session = loginService.login("bold", "ffw");
		Integer result = mobileService.saveUserDummy(session.getUser(), "c", name, name,street,city, "",telp,email,
				country, String.valueOf(longitude), String.valueOf(latitude));

		if(result > 0)return true;

		return false;
	}


	@Override
	public List<CourierOffer> getCourierChoiceByBookingId(String bookingId) {
		List<CourierConfirm> confirms =  courierConfirmRepository.findOfferOnPickupAndDeliveryAvailableByBookingId(bookingId);
		List<CourierOffer> offerAvailable = confirms.stream().map(CourierConfirm::getCourier_offer).collect(Collectors.toList());
		List<CourierOffer> result = new ArrayList();
		for(CourierOffer c: offerAvailable){
			double rating = ratingRepository.calculateCourierRating(c.getCourier().getId());
			c.getCourier().setRating(rating);
			result.add(c);
		}
		
		return offerAvailable;
	}


	@Override
	public Boolean customerChooseCourier(String booking_id, Long courier_pickup, Long courier_delivery) {
		CourierOffer pickup_courier_offer = courierOfferRepository.findById(courier_pickup).get();
		CourierOffer delivery_courier_offer = courierOfferRepository.findById(courier_delivery).get();
		if(pickup_courier_offer == null || delivery_courier_offer == null){
			return false;
		}
		BookingTempDTD data = bookingTempRepository.findDataById(booking_id);
		
		data.setCourier_from_id(pickup_courier_offer.getId());
		data.setPickup_price(pickup_courier_offer.getPrice());
		data.setCourier_to_id(delivery_courier_offer.getId());
		data.setDeliver_price(delivery_courier_offer.getPrice());

		bookingTempRepository.save(data);
		List<CourierOffer> offers = courierOfferRepository.findCouriersByBookingData(data.getBooking_id());
		if(offers.size() == 0){
			List<ScheduleDoorToDoor> scheduleData = findSchedule(data);
			List<CourierOffer> couriers = courierOfferRepository.findCouriersByBookingData(data.getBooking_id());
			scheduleData = filterData(scheduleData, couriers);
			saveBookingSchedule(data, scheduleData);
			pushNotificationService.searchAndsendScheduleToUser(scheduleData, booking_id);
		}
		return true;
	}

}
