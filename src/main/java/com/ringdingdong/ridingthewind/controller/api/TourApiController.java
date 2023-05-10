package com.ringdingdong.ridingthewind.controller.api;//package com.ssafy.enjoytrip.controller.api;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.enjoytrip.model.TourDto;
//import com.ssafy.enjoytrip.model.service.TourService;
//
//@RestController
//@RequestMapping("/tour")
//@CrossOrigin("*")
//public class TourApiController {
//	private final Logger logger = LoggerFactory.getLogger(TourApiController.class);
//	
//	private TourService tourService;
//
//	public TourApiController(TourService tourService) {
//		super();
//		this.tourService = tourService;
//	}
//	
//	
//	private ResponseEntity<String> exceptionHandling(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
