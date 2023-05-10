package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import com.ringdingdong.ridingthewind.model.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/tour")
public class TourController {
	private final Logger logger = LoggerFactory.getLogger(TourController.class);
	
	private TourService tourService;

	public TourController(TourService tourService) {
		super();
		this.tourService = tourService;
	}
	
	@GetMapping("/sido")
	public String mvsido() {
		return "tours/tour";
	}
	
	@PostMapping("/sido")
	@ResponseBody
	public ResponseEntity<List<TourSidoDto>> sido() throws Exception {
		 List<TourSidoDto> list = tourService.getSido();
		 return ResponseEntity.ok(list);
	}
	
	@GetMapping("/gugun")
	@ResponseBody
	public ResponseEntity<List<TourGugunDto>> gugun(@RequestParam("search-area") int sidoCode) throws Exception {
		List<TourGugunDto> list = tourService.getGugun(sidoCode);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/attraction-info")
	@ResponseBody
	public ResponseEntity<List<TourDto>> attraction_info(@RequestParam("search-area") int sidoCode, @RequestParam("search-area-gu") int gugunCode) throws Exception {
		List<TourDto> list = tourService.getList(sidoCode, gugunCode);
		return ResponseEntity.ok(list);
	}
	

	@PostMapping("/addtour/{contentid}")
	@ResponseBody
	public void addtour(@PathVariable("contentid") int contentId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberinfo");
		String memberId = memberDto.getMemberId();
		tourService.addtour(contentId, memberId);
	}

	@GetMapping("/share-list")
	public String shareList() {
		return "tours/shareboardlist";
	}
	

}
