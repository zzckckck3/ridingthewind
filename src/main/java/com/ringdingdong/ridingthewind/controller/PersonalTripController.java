package com.ringdingdong.ridingthewind.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.service.PersonalTripService;

@RestController
@RequestMapping(value = "/mypage")
public class PersonalTripController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonalTripController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private PersonalTripService personalTripService;

	public PersonalTripController(PersonalTripService personalTripService) {
		super();
		this.personalTripService = personalTripService;
	}
	
	@PutMapping("/edit")
	public ResponseEntity<String> edit(@RequestBody MemberDto memberDto) throws Exception {
		logger.debug("memberDto info : {}", memberDto);
		if(personalTripService.editMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/withdrawal/{memberId}/{memberPassword}")
	public ResponseEntity<String> withdrawal(@PathVariable("memberId") String memberId, @PathVariable("memberPassword") String memberPassword, HttpSession session) throws Exception {
		logger.debug("memberId info : {}, memberPassword info : {}", memberId, memberPassword);
		if(personalTripService.deleteMember(memberId, memberPassword)) {
			session.invalidate();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);				
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}


