package com.example.postgresdemo;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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
import junit.framework.Assert;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.Location;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationLauncher.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@WebAppConfiguration
public class ACTControllerTest {
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

	@Autowired
	private IBookingService bookingService;
	
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void test(){

	}
}
