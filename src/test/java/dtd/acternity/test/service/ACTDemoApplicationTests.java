package dtd.acternity.test.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jws.WebService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.act.common.dtd.ScheduleDoorToDoor;
import de.act.common.interfaces.ILogin;
import de.act.common.interfaces.ISession;
import de.act.common.interfaces.Webservice;
import de.act.common.types.User;
import de.act.common.types.nonstaticobjects.CourierInformation;
import dtd.acternity.service.IMainService;
import dtd.acternity.service.db.repository.BookingDTDRepository;
import dtd.acternity.service.db.repository.BookingScheduleRepository;
import dtd.acternity.service.db.repository.BookingStageRepository;
import dtd.acternity.service.db.repository.CourierOfferRepository;
import dtd.acternity.service.launcher.ApplicationLauncher;
import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.Location;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationLauncher.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class ACTDemoApplicationTests {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		RmiProxyFactoryBean rmiProxy() {
			RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
			bean.setServiceInterface(Webservice.class);
			bean.setServiceUrl("rmi://localhost:18886/mobileService");

			return bean;
		}

		@Bean
		RmiProxyFactoryBean rmiLoginProxy() {
			RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
			bean.setServiceInterface(ILogin.class);
			bean.setServiceUrl("rmi://localhost:18886/login");

			return bean;
		}
	}
	@Autowired
	private Webservice mobileService;

	@Autowired
	private ILogin loginService; 
	
	@Autowired
	private IMainService mainService;

	@Autowired
	private CourierOfferRepository courierOfferRepository;
	
	@Autowired
	private BookingScheduleRepository bookingScheduleRepository;
	
	@Autowired
	private BookingDTDRepository bookingTempRepository;
	
	@Autowired
	private BookingStageRepository bookingStageRepository;
	
	@Test
	public void func_test(){
		Map consigneeMap = mobileService.getConsigneeAddress(123451042L);
		Map shipperMap = mobileService.getShipperAddress(123451432L);
		Assert.assertTrue(shipperMap.size() > 0);
	}
	
	public void contextLoads() {
		String fromLat = "48.3550665200702";
		String fromLon = "11.770747194412252";
		String toLat = "47.3766969";
		String toLon = "8.540349299999999";
		Integer fromAddId = 0;
		Integer toAddId = 0;

		ISession session = loginService.login("bold", "ffw");

		BookingTempDTD bookingData = new BookingTempDTD();
		
//		bookingData.setDeliverDate(new Date());
//		bookingData.setFrom(new Location("123451042", "customer 1 test", Double.parseDouble(fromLat), Double.parseDouble(fromLon)));
//		bookingData.setTo(new Location("123451432", "customer 2 test2", Double.parseDouble(toLat), Double.parseDouble(toLon)));
		bookingData.setItemDescription("1354:0:asses |AVI|1|Each|1.0|1|1|1|1.0|| | | | | | ");
		
		
		String bookingId = mainService.saveBookingRequestData(bookingData);
		List<CourierOffer> offers = courierOfferRepository.findCouriersByBookingData(bookingId);
		CourierOffer offerPickup = null;
		CourierOffer offerDelivery = null;
		for(CourierOffer f: offers){
			if(f.isPickup() && offerPickup == null){
				offerPickup = f;
			}else if(!f.isPickup() && offerDelivery == null){
				offerDelivery = f;
			}
		}
		
		offerPickup.setAcceptedOn(new Date());
		mainService.courierConfirmPickUp(offerPickup);
		
		offerDelivery.setAcceptedOn(new Date());
		mainService.courierConfirmPickUp(offerDelivery);
		List<BookingSchedule> bookingSchedules = bookingScheduleRepository.findBookingScheduleByBookingId(bookingId);
		Assert.assertTrue(bookingSchedules.size() > 0);
		
		mainService.customerChooseRate(123451042L, Long.parseLong(bookingSchedules.get(0).getRate_id()), bookingId);
		
		BookingTempDTD data = bookingTempRepository.findDataById(bookingId);
		Boolean isPickup = mainService.doPackageHandover(Double.parseDouble(fromLat), Double.parseDouble(fromLon), 
				data.getCourier_from_id(), data.getBooking_id(), data.getQr_data());
		
		Assert.assertTrue(isPickup);
		
		Assert.assertTrue(mainService.doPackageHandover(Double.parseDouble(toLat), Double.parseDouble(toLon), 
				data.getCourier_to_id(), data.getBooking_id(), data.getQr_data()));
		Assert.assertTrue(mainService.deliverBooking(Double.parseDouble(toLat), Double.parseDouble(toLon), 
				data.getBooking_id(), "123451432"));
		List<BookingStageDTD> stages = bookingStageRepository.findByBookingData(data);
		Assert.assertTrue(stages.size() == 3);
		
//		
//		//		Integer resultFrom = mobileService.saveUserDummy( session.getUser(),  "c",  "customer 1 test",  "customer 1 test",  "Am Sportpl. 3",  "Freising",  "",  "",  "",
//		//				 "Germany",  fromLon,  fromLat);
//		//		
//				Integer resultTo = mobileService.saveUserDummy(session.getUser(), "c","customer 2 test2", "customer 2 test2",
//						"Bahnhofstrasse 77","Zurich","","","","Switzerland",toLon,toLat);
//
//
//		//		123451428, 123451429
//		List<CourierInformation> deliveryResult = mobileService.getCheckRangeInCourierByLatitudeLangitude("123451042", "47.3766969", "8.540349299999999");
//		List<CourierInformation> pickupResult = mobileService.getCheckRangeInCourierByLatitudeLangitude("123451432", "48.3550665200702", "11.770747194412252");
////
////		List temp = new ArrayList();
////		List<CourierInformation> pResult = new ArrayList<CourierInformation>();
////		
////		for(CourierInformation c: pickupResult){
////			System.out.println(c.getCourierAddId()+". "+c.getCourierAddName()+" -- "+c.getCourierLatitude()+","+c.getCourierLangitude());
////		}
////		
////		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, 3);
//		String[] comm = new String[]{"1354:0:asses |AVI|1|Each|1.0|1|1|1|1.0|| | | | | | "};
//		 List<ScheduleDoorToDoor> sch = mobileService.getDepAndArrAndRateByDistanceDepartureToDestinationsDTD( 
//				"gregke12344",  "customer 1 test",  "customer 2 test",  new Date(), cal.getTime(), 
//				comm,  session.getUser(),  123451428,  "48.3550665200702",  "11.770747194412252",  123451429,
//				"47.3766969",  "8.540349299999999",  "");
//		 List<ScheduleDoorToDoor> result = new ArrayList();
//		 for(ScheduleDoorToDoor d: sch){
//			 if(d.getShipper_add_id().equals("12345858") && d.getConsignee_add_id().equals("123451399")){
//				 System.out.println(d.getShipper_add_id()+","+d.getConsignee_add_id()+","+d.getTotal_fee_from()+","+d.getTotal_fee_to());
//				 result.add(d);
//			 }
//		 }
//		Assert.assertNotNull(sch);
		//		48.3550665200702,11.770747194412252	Muenchen
		//		47.3766969,8.540349299999999		Zuerich
	}

}
