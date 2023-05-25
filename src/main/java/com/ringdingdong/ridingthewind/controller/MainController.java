package com.ringdingdong.ridingthewind.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.service.ArticleService;
import com.ringdingdong.ridingthewind.model.service.TourService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(PersonalTripController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
