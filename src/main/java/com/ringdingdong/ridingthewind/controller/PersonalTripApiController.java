package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.service.PersonalTripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypage")
@CrossOrigin("*")
public class PersonalTripApiController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonalTripApiController.class);
	
	private PersonalTripService personalTripService;

	public PersonalTripApiController(PersonalTripService personalTripService) {
		super();
		this.personalTripService = personalTripService;
	}
	
	@GetMapping("/list/{memberid}")
	public ResponseEntity<?> tripList(@PathVariable("memberid") String memberId){
		logger.debug("mypage tripList called");
		try {
			List<PersonalTripDto> list = personalTripService.listPersonalTrip(memberId);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PersonalTripDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/delete/{memberid}/{contentid}")
	public ResponseEntity<?> tripDelete(@PathVariable("memberid") String memberId, @PathVariable("contentid")  int contentId) {
		logger.debug("userDelete memberid : {}", memberId);
		try {
			personalTripService.deleteTrip(memberId, contentId);
			List<PersonalTripDto> list = personalTripService.listPersonalTrip(memberId);
			return new ResponseEntity<List<PersonalTripDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
