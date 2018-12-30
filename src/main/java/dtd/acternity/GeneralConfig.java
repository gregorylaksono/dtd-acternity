package dtd.acternity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dtd.acternity.service.model.BookingSchedule;
import dtd.acternity.service.model.BookingStageDTD;
import dtd.acternity.service.model.BookingTempDTD;
import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.CourierOffer;
import dtd.acternity.service.model.dto.BookingDataDTO;
import dtd.acternity.service.model.dto.BookingStage;
import dtd.acternity.service.model.dto.CourierDTO;
import dtd.acternity.service.model.dto.CourierOfferDTO;
import dtd.acternity.service.model.dto.ScheduleDTO;

@Configuration
public class GeneralConfig {

	@Bean
	public ModelMapper getModelMapper(){
		final ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		Converter<CourierOffer, CourierOfferDTO> courierOfferDTOConverter = new Converter<CourierOffer, CourierOfferDTO>()
		{

			@Override
			public CourierOfferDTO convert(MappingContext<CourierOffer, CourierOfferDTO> arg0) {
				CourierOffer source = arg0.getSource();

				CourierDTO courierDTO = mapper.map(source.getCourier(), CourierDTO.class);
				CourierOfferDTO dto = new CourierOfferDTO();
				dto.setAcceptedOn(source.getAcceptedOn());
				dto.setBooking_id(source.getBookingData().getBooking_id());
				dto.setCourier(courierDTO);
				dto.setLocation(source.getLocation());
				dto.setPrice(source.getPrice());
				dto.setId(source.getId());
				return dto;
			}
		};

		Converter<CourierOfferDTO, CourierOffer> courierOfferEntityConverter = new Converter<CourierOfferDTO, CourierOffer>()
		{

			@Override
			public CourierOffer convert(MappingContext<CourierOfferDTO, CourierOffer> arg0) {
				CourierOfferDTO source = arg0.getSource();
				CourierOffer entity = new CourierOffer();
				entity.setAcceptedOn(source.getAcceptedOn());
				
				BookingTempDTD bookingData = new BookingTempDTD();
				Courier courierData = getModelMapper().map(source.getCourier(), Courier.class);

				bookingData.setBooking_id(source.getBooking_id());

				entity.setBookingData(bookingData);
				entity.setCourier(courierData);
				entity.setId(source.getId());
				entity.setLocation(source.getLocation());
				entity.setPrice(source.getPrice());
				entity.setStart(source.isStart());
				entity.setPickup(source.isPickup());
				return entity;
			}
		};

		Converter<Courier, CourierDTO> courierDTOConverter = new Converter<Courier, CourierDTO>(){

			@Override
			public CourierDTO convert(MappingContext<Courier, CourierDTO> arg0) {
				Courier source = arg0.getSource();
				CourierDTO dto = new CourierDTO();
				dto.setId(source.getId());
				dto.setLocation(source.getLocation());
				dto.setName(source.getName());
				dto.setPrice_per_km(source.getPrice_per_km());
				dto.setToken_device(source.getToken_device());
				return dto;
			}

		};
		Converter<BookingDataDTO, BookingTempDTD> bookingDataEntityConverter = new Converter<BookingDataDTO, BookingTempDTD>(){

			@Override
			public BookingTempDTD convert(MappingContext<BookingDataDTO, BookingTempDTD> arg0) {
				BookingDataDTO source = arg0.getSource();
				BookingTempDTD entity = new BookingTempDTD();
				entity.setBooking_id(source.getBooking_id());
				entity.setCourier_from_id(source.getCourier_from_id());
				entity.setCourier_to_id(source.getCourier_to_id());
				entity.setDeliver_price(source.getDeliver_price());
				entity.setDeliverDate(source.getDeliverDate());
				entity.setExpiration_date(source.getExpiration_date());
				entity.setFrom(source.getFrom());
				entity.setTo(source.getTo());
				entity.setItemDescription(source.getItemDescription());
				entity.setPickup_price(source.getPickup_price());
				entity.setQr_data(source.getQr_data());
				entity.setRate_id(source.getRate_id());
				return entity;
			}
			
		};

		Converter<BookingTempDTD, BookingDataDTO> bookingDataDTOConverter = new Converter<BookingTempDTD, BookingDataDTO>(){

			@Override
			public BookingDataDTO convert(MappingContext<BookingTempDTD, BookingDataDTO> arg0) {
				BookingTempDTD source = arg0.getSource();
				BookingDataDTO dto = new BookingDataDTO();
				dto.setBooking_id(source.getBooking_id());
				dto.setCourier_from_id(source.getCourier_from_id());
				dto.setCourier_to_id(source.getCourier_to_id());
				dto.setDeliver_price(source.getDeliver_price());
				dto.setDeliverDate(source.getDeliverDate());
				dto.setExpiration_date(source.getExpiration_date());
				dto.setFrom(source.getFrom());

				dto.setItemDescription(source.getItemDescription());
				dto.setPickup_price(source.getPickup_price());
				dto.setQr_data(source.getQr_data());
				dto.setRate_id(source.getRate_id());
				dto.setTo(source.getTo());



				return dto;
			}

		};
		Converter<BookingSchedule, ScheduleDTO> scheduleDataDTOConverter = new Converter<BookingSchedule, ScheduleDTO>(){

			@Override
			public ScheduleDTO convert(MappingContext<BookingSchedule, ScheduleDTO> arg0) {
				BookingSchedule source = arg0.getSource();
				ScheduleDTO dto = new ScheduleDTO();
				dto.setBooking_id(source.getBooking_id());
				dto.setDelivery_courier(source.getDelivery_courier());
				dto.setPickup_courier(source.getPickup_courier());
				dto.setRate_id(source.getRate_id());
				dto.setTotal_from(source.getTotal_from());
				dto.setTotal_to(source.getTotal_to());
				return dto;
			}

		};
		
		Converter<BookingStageDTD, BookingStage> bookingStageDTOConverter = new Converter<BookingStageDTD, BookingStage>(){

			@Override
			public BookingStage convert(MappingContext<BookingStageDTD, BookingStage> arg0) {
				BookingStageDTD source = arg0.getSource();
				BookingStage dto = new BookingStage();
				dto.setPrice(source.getPrice());
				dto.setBooking_id(source.getBookingData().getBooking_id());
				dto.setAdd_id_created_by(source.getAdd_id_created_by());
				dto.setCreatedOn(source.getCreatedOn());
				dto.setLatitude(source.getLatitude());
				dto.setLongitude(source.getLongitude());
				return dto;
			}


		};
		
		mapper.createTypeMap(BookingStageDTD.class, BookingStage.class).setConverter(bookingStageDTOConverter);
		mapper.createTypeMap(BookingDataDTO.class, BookingTempDTD.class).setConverter(bookingDataEntityConverter);
		mapper.createTypeMap(CourierOffer.class, CourierOfferDTO.class).setConverter(courierOfferDTOConverter);
		mapper.createTypeMap(CourierOfferDTO.class, CourierOffer.class).setConverter(courierOfferEntityConverter);
		mapper.createTypeMap(Courier.class, CourierDTO.class).setConverter(courierDTOConverter);
		mapper.createTypeMap(BookingTempDTD.class, BookingDataDTO.class).setConverter(bookingDataDTOConverter);
		mapper.createTypeMap(BookingSchedule.class, ScheduleDTO.class).setConverter(scheduleDataDTOConverter);
		return mapper;
	}

	@Bean
	public DateFormat controllerDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

}
