package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import com.ringdingdong.ridingthewind.model.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
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
	
	@GetMapping("/gugun")
	public ResponseEntity<List<TourGugunDto>> gugun(@RequestParam("search-area") int sidoCode) throws Exception {
		List<TourGugunDto> list = tourService.getGugun(sidoCode);
		logger.debug("gugun info : {}", list); 
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/attraction-info")
	public ResponseEntity<List<TourDto>> attraction_info(@RequestParam("search-area") int sidoCode, @RequestParam("search-area-gu") int gugunCode) throws Exception {
		List<TourDto> list = tourService.getList(sidoCode, gugunCode);
		logger.debug("attraction info : {}", list); 
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/addtour/{contentid}")
	public ResponseEntity<String> addtour(@PathVariable("contentid") int contentId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberinfo");
		String memberId = memberDto.getMemberId();
		if(tourService.addtour(contentId, memberId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


}
