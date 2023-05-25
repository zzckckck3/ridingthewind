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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	@ApiOperation(value = "게시글을 찜 많은 순으로 정렬하여 반환", notes = "X개 반환함")
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
	
	@GetMapping("/post-count/{sidoGugun}/{orderByWhat}")
	@ApiOperation(value = "sido/gugun을 기반으로 정렬하여 반환함", notes = "post_count=해당 sido/gugun에 등록된 글 갯수 기반 반환 / ten/twenty~sixty=해당 나이대가 쓴 글이 많은 sido/gugun반환")
	public ResponseEntity<?> getPopularTourList(
			@PathVariable("sidoGugun") String sidoGugun,
			@PathVariable("orderByWhat") String orderByWhat) throws Exception {
		logger.debug("getPopularTourSidoList called");
		
		try {
			List<TourDto> list = recommendService.getPopularTourList(sidoGugun, orderByWhat);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getPopularTourList(sidoGugun, orderByWhat), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/get-popular-content/sido/{sidoCode}")
	public ResponseEntity<?> getPopularContentSido(
			@PathVariable("sidoCode") int sidoCode) throws Exception{
		logger.debug("getPopularContentSido called");

		try {
			List<TourDto> list = recommendService.getPopularContentSido(sidoCode);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getPopularContentSido(sidoCode), HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/get-popular-content/gugun/{sidoCode}/{gugunCode}")
	public ResponseEntity<?> getPopularContentGugun(
			@PathVariable("sidoCode") int sidoCode, 
			@PathVariable("gugunCode") int gugunCode) throws Exception{
		logger.debug("getPopularContentGugun called");

		try {
			List<TourDto> list = recommendService.getPopularContentGugun(sidoCode, gugunCode);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourDto>>(recommendService.getPopularContentGugun(sidoCode, gugunCode), HttpStatus.OK);
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
