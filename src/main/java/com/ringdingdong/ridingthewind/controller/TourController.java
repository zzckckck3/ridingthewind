package com.ringdingdong.ridingthewind.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import com.ringdingdong.ridingthewind.model.service.TourService;

import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/tour")
public class TourController {
	private final Logger logger = LoggerFactory.getLogger(TourController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private TourService tourService;

	public TourController(TourService tourService) {
		super();
		this.tourService = tourService;
	}

	@GetMapping("/sido")
	public ResponseEntity<List<TourSidoDto>> sido() throws Exception {
		List<TourSidoDto> list = tourService.getSido();
		logger.debug("sido info : {}", list);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/sido/{sidoCode}")
	public ResponseEntity<String> addSidoCount(@PathVariable("sidoCode") int sidoCode) throws Exception{
		if(tourService.addSidoCount(sidoCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@GetMapping("/gugun")
	public ResponseEntity<List<TourGugunDto>> gugun(@RequestParam("search-area") int sidoCode) throws Exception {
		List<TourGugunDto> list = tourService.getGugun(sidoCode);
		logger.debug("gugun info : {}", list);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/gugun/{sidoCode}/{gugunCode}")
	public ResponseEntity<String> addGugunCount(@PathVariable("sidoCode") int sidoCode, @PathVariable("gugunCode") int gugunCode) throws Exception{
		if(tourService.addGugunCount(sidoCode, gugunCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/attraction-info")
	public ResponseEntity<List<TourDto>> attraction_info(@RequestParam("search-area") int sidoCode, @RequestParam("search-area-gu") int gugunCode) throws Exception {
		List<TourDto> list = tourService.getList(sidoCode, gugunCode);
		logger.debug("attraction info : {}", list);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/attraction-info-bycontentids")
	public ResponseEntity<List<TourDto>> getListByContentIds(@RequestParam("contentIds") String contentIds) throws Exception {

		return new ResponseEntity<>(tourService.getListByContentIds(
				Arrays.stream(contentIds.split(","))
						.map(Integer::parseInt)
						.collect(Collectors.toList())), HttpStatus.OK);
	}

	@GetMapping("/attraction-info-bykeyword")
	public ResponseEntity<List<TourDto>> getListByKeyword(
			@RequestParam("keyword") String keyword,
			@RequestParam("search-area") int sidoCode,
			@RequestParam("search-area-gu") int gugunCode) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sidoCode", sidoCode);
		paramMap.put("gugunCode", gugunCode);
		paramMap.put("keyword", keyword);
		List<TourDto> list = tourService.getListByKeyword(paramMap);
		logger.debug("attraction info by keyword : {}", list);
		return ResponseEntity.ok(list);
	}

	//	@PostMapping("/addtour/{contentid}")
//	public ResponseEntity<String> addtour(@PathVariable("contentid") int contentId, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("memberinfo");
//		String memberId = memberDto.getMemberId();
//		if(tourService.addtour(contentId, memberId)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
	@GetMapping("/mylikelist/{memberid}")
	public ResponseEntity<List<PersonalTripDto>> mylikelist(@PathVariable("memberid") String memberId) throws SQLException{
		List<PersonalTripDto> list = tourService.getLikeList(memberId);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/addtour/{memberid}/{contentid}")
	public ResponseEntity<String> addtour(@PathVariable("contentid") int contentId, @PathVariable("memberid") String memberId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberinfo");
//		String memberId = memberDto.getMemberId();
		if(tourService.addtour(contentId, memberId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TourDto>> popularTour() throws SQLException{
		List<TourDto> list = tourService.getPopularTourList();
		return ResponseEntity.ok(list);
	}


}
