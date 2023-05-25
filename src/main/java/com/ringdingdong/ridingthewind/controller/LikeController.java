package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.LikeDto;
import com.ringdingdong.ridingthewind.model.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/like")
@RequiredArgsConstructor
@Slf4j
public class LikeController {

	private final LikeService likeService;

	@PostMapping
	public ResponseEntity<String> write(@RequestBody LikeDto likeDto) throws Exception {
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
