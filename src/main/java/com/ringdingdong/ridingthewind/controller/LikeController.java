package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.LikeDto;
import com.ringdingdong.ridingthewind.model.service.ArticleAttractionService;
import com.ringdingdong.ridingthewind.model.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/like")
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping
	public ResponseEntity<String> write(LikeDto likeDto) throws Exception {
		if (likeService.insertLike(likeDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<String> isThisArticleLike(LikeDto likeDto) throws Exception {
		if (likeService.likeThisArticle(likeDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteLike(LikeDto likeDto) throws Exception {
		if (likeService.deleteLike(likeDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

}