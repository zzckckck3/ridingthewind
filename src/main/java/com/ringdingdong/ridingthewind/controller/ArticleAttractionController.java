package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.ArticleListResponseDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import com.ringdingdong.ridingthewind.model.CommentDto;
import com.ringdingdong.ridingthewind.model.service.ArticleAttractionService;
import com.ringdingdong.ridingthewind.model.service.CommentService;
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
@RequestMapping(value = "/article-attraction")
@RequiredArgsConstructor
@Api("게시판 경로 컨트롤러 API V1")
public class ArticleAttractionController {

	private final ArticleAttractionService articleAttractionService;

	@ApiOperation(value = "게시판 경로 작성", notes = "새로운 게시글 여행 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> write(@RequestBody @ApiParam(value = "게시글 여행 정보", required = true) List<ArticleAttractionDto> articleAttractionDto) throws Exception {
		if (articleAttractionService.writeArticleAttraction(articleAttractionDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시글 여행 수정", notes = "게시글 여행 번호에 해당하는 게시글 여행의 정보를 수정한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> putArticleAttraction(@RequestBody @ApiParam(value = "게시글 여행 정보", required = true) List<ArticleAttractionDto> articleAttractionDto) throws Exception {
		if (articleAttractionService.updateArticleAttraction(articleAttractionDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시글 여행 삭제", notes = "게시글 여행 번호에 해당하는 게시글 여행의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> deleteArticleAttraction(@RequestBody @ApiParam(value = "게시글 여행 정보", required = true) List<ArticleAttractionDto> articleAttractionDto) throws Exception {
		if (articleAttractionService.deleteArticleAttraction(articleAttractionDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

}
