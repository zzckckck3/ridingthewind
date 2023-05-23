package com.ringdingdong.ridingthewind.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.service.ArticleService;
import com.ringdingdong.ridingthewind.model.service.TourService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final TourService tourService;
	
	@GetMapping("/like")
	public ResponseEntity<List<TourDto>> popularTour() throws SQLException{
		List<TourDto> list = tourService.getPopularTourList();
		return ResponseEntity.ok(list);
	}
}
