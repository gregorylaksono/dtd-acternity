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
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.BookingTempDTD.BookingState;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.dto.CourierOfferDTO;
import dtd.acternity.service.model.dto.ScheduleDTO;
import dtd.acternity.util.GeneralUtil;

@RestController
@RequestMapping("/courier")
public class CourierController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IBookingService bookingService;
	
	@Autowired
	private IMainService mainService;
	
	@GetMapping("/job/state/{state_id}")
	public ResponseEntity getCouriersJobByState(@PathVariable("state_id") Integer state_id){
		BookingState bookingState = GeneralUtil.convertToBookingState(state_id);
		List<CourierOffer> couriersJobEntity = bookingService.getCourierJobByBookingState(bookingState);
		 java.lang.reflect.Type targetListType = new TypeToken<List<CourierOfferDTO>>() {}.getType();
		 List<CourierOfferDTO> couriersJobDTOResult = modelMapper.map(couriersJobEntity, targetListType);
		return ResponseEntity.ok(couriersJobDTOResult);
	}
	
	
	@PostMapping("/confirm")
	public ResponseEntity courierConfirmJob(@RequestBody CourierOfferDTO courierOffer){
		CourierOffer offerEntity = modelMapper.map(courierOffer, CourierOffer.class);
		Boolean isConfirmSuccess = mainService.courierConfirmPickUp(offerEntity);
		if(isConfirmSuccess){
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	
	@GetMapping("/schedule/{booking_id}")
	public ResponseEntity getScheduleByBookingId(@PathVariable("booking_id") String bookingId){
		List<BookingSchedule> scheduleData = bookingService.getScheduleByBookingId(bookingId);
		java.lang.reflect.Type targetListType = new TypeToken<List<ScheduleDTO>>() {}.getType();
		List<ScheduleDTO> scheduleDto= modelMapper.map(scheduleData, targetListType);
		return ResponseEntity.ok(scheduleDto);
	}
	
	@PostMapping("/pickup")
	public ResponseEntity courierPickup(@RequestBody CourierOfferDTO courierOffer){
		BookingTempDTD bookingData = bookingService.getBookingDataByBookingId(courierOffer.getBooking_id());
		Boolean isPickUpSuccess = mainService.doPackageHandover(courierOffer.getLocation().getLatitude(), courierOffer.getLocation().getLongitude(),
				courierOffer.getCourier().getId(), courierOffer.getBooking_id(), bookingData.getQr_data());
		if(isPickUpSuccess){
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();			
		}
	}
	
	
}
