package com.ringdingdong.ridingthewind.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.ArticleDetailDto;
import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import com.ringdingdong.ridingthewind.model.ArticleListResponseDto;
import com.ringdingdong.ridingthewind.model.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/article")
@RequiredArgsConstructor
@Api("게시판 컨트롤러 API V1")
public class ArticleController {

	private final ArticleService articleService;

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> write(@RequestBody @ApiParam(value = "게시글 정보", required = true) ArticleDto articleDto) throws Exception {
		if (articleService.writeArticle(articleDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<ArticleListResponseDto> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) ArticleParameterDto articleParameterDto) throws Exception {
		return new ResponseEntity<>(articleService.listArticle(articleParameterDto), HttpStatus.OK);
	}

	@ApiOperation(value = "핫글 목록", notes = "특정 기간안의 추천수가 높은 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/hot")
	public ResponseEntity<List<ArticleDetailDto>> listHotArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) @RequestParam int period) throws Exception {
		return new ResponseEntity<>(articleService.listHotArticle(period), HttpStatus.OK);
	}

	@ApiOperation(value = "핫글 목록", notes = "특정 기간안의 추천수가 높은 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/{userId}/like")
	public ResponseEntity<List<ArticleDto>> listMyLikeArticle(@ApiParam(value = "추천 게시글을 얻기 위한 회원의 ID", required = true) @PathVariable("userId") String memberId) throws Exception {
		return new ResponseEntity<>(articleService.listMyLikeArticle(memberId), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = ArticleDto.class)
	@GetMapping("/{articleNo}")
	public ResponseEntity<ArticleDetailDto> getArticle(@PathVariable("articleNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleNo) throws Exception {
		return new ResponseEntity<>(articleService.getArticle(articleNo), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 조회수 수정", notes = "게시글의 조회수를 1 더한다.", response = List.class)
	@PutMapping("/{articleNo}/hit")
	public ResponseEntity<String> updateHit(@ApiParam(value = "게시글 번호", required = true) @PathVariable("articleNo") int articleNo) throws Exception {
		if (articleService.updateHit(articleNo)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) ArticleDto articleDto) throws Exception {
		if (articleService.modifyArticle(articleDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleNo") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleNo) throws Exception {
		if (articleService.deleteArticle(articleNo)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

}
