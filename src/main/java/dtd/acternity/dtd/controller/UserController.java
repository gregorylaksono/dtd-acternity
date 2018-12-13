package dtd.acternity.dtd.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtd.acternity.service.IBookingService;
import dtd.acternity.service.IMainService;
import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.dto.BookingDataDTO;
import dtd.acternity.service.model.dto.BookingStage;
import dtd.acternity.service.model.dto.ScheduleDTO;
import dtd.acternity.service.model.dto.UserRegistration;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IMainService mainService;
	
	@Autowired
	private IBookingService bookingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(name="/register")
	public ResponseEntity registerUser(@RequestBody(required=true) UserRegistration userRegistrationData){
		Boolean isUserRegisterSuccess = mainService.registerUser(userRegistrationData.getName(),userRegistrationData.getEmail(), 
				userRegistrationData.getStreet(), userRegistrationData.getCity(), 
				userRegistrationData.getTelp(), userRegistrationData.getCity(), 
				userRegistrationData.getLatitude(), userRegistrationData.getLongitude());
		if(isUserRegisterSuccess){
			return ResponseEntity.accepted().build();	
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
	}
	
	@GetMapping("/schedule/{booking_id}")
	public ResponseEntity getScheduleByBookingId(@PathVariable("booking_id") String bookingId){
		List<BookingSchedule> scheduleData = bookingService.getScheduleByBookingId(bookingId);
		java.lang.reflect.Type targetListType = new TypeToken<List<ScheduleDTO>>() {}.getType();
		List<ScheduleDTO> scheduleDto= modelMapper.map(scheduleData, targetListType);
		return ResponseEntity.ok(scheduleDto);
	}

	@PostMapping("/book/{user_id}/{rate_id}/{booking_id}")
	public ResponseEntity userChooseBooking(@PathVariable("user_id") Long user_id, 
			@PathVariable("rate_id") Long rate_id, @PathVariable("booking_id") String booking_id){
		Boolean isBookingSuccess = mainService.customerChooseRate(user_id, rate_id, booking_id);
		if(isBookingSuccess){
			return ResponseEntity.accepted().build();	
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/booking/{user_id}")
	public ResponseEntity getMyBookings(@PathVariable("user_id") Long user_id){
		List<BookingTempDTD> bookingsData = bookingService.getBookingByUserId(user_id);
		java.lang.reflect.Type targetListType = new TypeToken<List<BookingDataDTO>>() {}.getType();
		List<BookingDataDTO> bookingData =  modelMapper.map(bookingsData, targetListType);
		return ResponseEntity.ok(bookingData);
	}
	
	@GetMapping("/booking_stage/{booking_id}")
	public ResponseEntity getBookingStageByBookingId(@PathVariable("booking_id") String booking_id){
		List<BookingStageDTD> stagesEntity = bookingService.getBookingStageByBookingId(booking_id);
		java.lang.reflect.Type targetListType = new TypeToken<List<BookingStage>>() {}.getType();
		List<BookingStage> bookingData =  modelMapper.map(stagesEntity, targetListType);
		return ResponseEntity.ok(bookingData);
	}
	
}
