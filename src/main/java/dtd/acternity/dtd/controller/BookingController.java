package dtd.acternity.dtd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtd.acternity.dtd.BookingData;
import dtd.acternity.service.IBookingService;
import dtd.acternity.service.IMainService;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.BookingTempDTD.BookingState;
import dtd.acternity.service.model.dto.BookingStage;
import dtd.acternity.service.model.dto.DeliveryPackageDTO;
import dtd.acternity.util.GeneralUtil;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private IMainService mainService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IBookingService bookingService;
	
	@PostMapping("/create")
	public ResponseEntity createBooking(@RequestBody(required = true) BookingData bookingData){
		BookingTempDTD bookingDataEntity = modelMapper.map(bookingData, BookingTempDTD.class);
		String bookingId = mainService.saveBookingRequestData(bookingDataEntity);
		Map<String, Object> response = new HashMap<>();
		response.put("booking_id", bookingId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{state_id}")
	public ResponseEntity getBookingByState(@PathVariable(name="state_id") Integer stateId){
		BookingState state = GeneralUtil.convertToBookingState(stateId);
		List<BookingTempDTD> bookings = bookingService.getBookingByState(state);
	    java.lang.reflect.Type targetListType = new TypeToken<List<BookingData>>() {}.getType();
	    List<BookingData> bookingResult = modelMapper.map(bookings, targetListType);
		return ResponseEntity.ok(bookingResult);
	}
	
	@PostMapping("/delivery")
	public ResponseEntity markPackageAsDeliver(@RequestBody DeliveryPackageDTO deliveryInfo){
		Boolean deliveryStatusInfo = mainService.deliverBooking(deliveryInfo.getLatitude(), deliveryInfo.getLongitude(), 
				deliveryInfo.getBookingId(), deliveryInfo.getSubjectId());
		if(deliveryStatusInfo){
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/stage/{booking_id}")
	public ResponseEntity getBookingStageByBookingId(@PathVariable(name="booking_id") String booking_id){
		List<BookingStageDTD> bookingStagesEntity = bookingService.getBookingStageByBookingId(booking_id);
		
	    java.lang.reflect.Type targetListType = new TypeToken<List<BookingStage>>() {}.getType();

	    List<BookingStage> bookingStageDtoResult = modelMapper.map(bookingStagesEntity, targetListType);
		return ResponseEntity.ok(bookingStageDtoResult);
	}
	
	
}