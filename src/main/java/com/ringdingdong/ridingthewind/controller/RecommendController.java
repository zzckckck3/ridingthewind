package com.ringdingdong.ridingthewind.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.service.RecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/recommend")
@RequiredArgsConstructor
public class RecommendController {
	private final Logger logger = LoggerFactory.getLogger(PersonalTripController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final RecommendService recommendService;
	
	@GetMapping("/sido")
	public ResponseEntity<?> getSidoCountList() throws Exception {
		logger.debug("getSidoCount called");
		
		try {
			List<TourDto> list = recommendService.getSidoCountList();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getSidoCountList(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<?> getGugunCountList(@PathVariable("sidoCode") int sidoCode) throws Exception {
		logger.debug("getSidoCount called");
		
		try {
			List<TourDto> list = recommendService.getGugunCountList(sidoCode);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getGugunCountList(sidoCode), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/like")
	public ResponseEntity<?> getMostLikeTourList() throws Exception{
		logger.debug("getMostLikeTourList called");

		try {
			List<TourDto> list = recommendService.getMostLikeTourList();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getMostLikeTourList(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/post-count-sido/{orderByWhat}")
	public ResponseEntity<?> getPopularTourSidoList(@PathVariable("orderByWhat") String orderByWhat) throws Exception {
		logger.debug("getPopularTourSidoList called");
		
		try {
			List<TourDto> list = recommendService.getPopularTourSidoList(orderByWhat);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getPopularTourSidoList(orderByWhat), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
