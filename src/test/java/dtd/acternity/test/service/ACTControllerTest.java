package dtd.acternity.test.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import de.act.common.interfaces.ILogin;
import de.act.common.interfaces.ISession;
import de.act.common.interfaces.Webservice;
import dtd.acternity.service.IBookingService;
import dtd.acternity.service.IMainService;
import dtd.acternity.service.db.repository.BookingDTDRepository;
import dtd.acternity.service.db.repository.BookingScheduleRepository;
import dtd.acternity.service.db.repository.BookingStageRepository;
import dtd.acternity.service.db.repository.CourierOfferRepository;
import dtd.acternity.service.launcher.ApplicationLauncher;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.BookingTempDTD.BookingState;
import dtd.acternity.test.constant.DataTemplateGenerator;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.Location;
import dtd.acternity.service.model.dto.CourierOfferDTO;
import dtd.acternity.service.model.dto.ScheduleDTO;

@RunWith(SpringRunner.class)

public class ACTControllerTest {

	private RestTemplate restTemplate;
	private String url = "http://localhost:8080";
	private String booking_id;
	private CourierOfferDTO courier1_job;
	private CourierOfferDTO courier2_job;
	private ScheduleDTO schedule_choosen;

	@Before
	public void do_before(){
		restTemplate = new RestTemplate();
	}

	@Test
	public void test_booking(){
		//Booking data
		BookingTempDTD data = new BookingTempDTD();
		data.setFrom(DataTemplateGenerator.getShipper());
		data.setTo(DataTemplateGenerator.getConsignee());
		data.setItemDescription(DataTemplateGenerator.getItemDescription());
		data.setDeliverDate(new Date());
		
		ResponseEntity<HashMap> result = restTemplate.postForEntity(url+"/booking/create", data, HashMap.class);
		Assert.assertNotNull(result.getBody());
		booking_id = String.valueOf(result.getBody().get("booking_id"));
		Assert.assertNotNull(booking_id);

	}
	@Test
	public void test_check_order_incoming_courier1(){
		ResponseEntity<List<CourierOfferDTO>> result = restTemplate.exchange(url+"courier/job/state/2/"+DataTemplateGenerator.getCourier1AddId(),HttpMethod.GET ,null,
				new ParameterizedTypeReference<List<CourierOfferDTO>>() {});
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
		List<CourierOfferDTO> responseBody = result.getBody();
		Assert.assertTrue(responseBody.size() > 0);
		courier1_job = responseBody.get(0);
	}
	
	@Test
	public void test_check_order_incoming_courier2(){
		ResponseEntity<List<CourierOfferDTO>> result = restTemplate.exchange(url+"courier/job/state/2/"+DataTemplateGenerator.getCourier2AddId(),HttpMethod.GET ,null,
				new ParameterizedTypeReference<List<CourierOfferDTO>>() {});
		Assert.assertTrue(result.getStatusCode().is2xxSuccessful());
		List<CourierOfferDTO> responseBody = result.getBody();
		Assert.assertTrue(responseBody.size() > 0);
		courier2_job = responseBody.get(0);
	}
	
	@Test
	public void test_courier1_confirm_order(){
		courier1_job.setAcceptedOn(new Date());
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/courier/confirm", courier1_job, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
	
	@Test
	public void test_courier2_confirm_order(){
		courier1_job.setAcceptedOn(new Date());
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/courier/confirm", courier2_job, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
	
	@Test
	public void test_user_check_schedule(){
		ResponseEntity<List<ScheduleDTO>> result = restTemplate.exchange(url+"/user/schedule/"+booking_id, HttpMethod.GET, null, new ParameterizedTypeReference<List<ScheduleDTO>>() {});
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody().size() > 0);
		schedule_choosen = result.getBody().get(0);
	}
	
	@Test
	public void test_user_choose_schedule(){
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/user/"+DataTemplateGenerator.getShipper().getAdd_id()+"/"+schedule_choosen.getRate_id()+"/"+booking_id,
				null, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
	
	public void test_courier1_pickup(){
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/courier/pickup", courier1_job, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
	
	public void test_courier2_pickup(){
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/courier/pickup", courier2_job, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
	
	public void test_courier2_delivery(){
		ResponseEntity<Object> result = restTemplate.postForEntity(url+"/booking/delivery", courier2_job, Object.class);
		Assert.assertTrue(result.getStatusCodeValue() == 202);
	}
}
