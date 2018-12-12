package dtd.acternity.dtd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtd.acternity.service.IMainService;
import dtd.acternity.service.model.dto.UserRegistration;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IMainService mainService;
	
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
	
	
}
