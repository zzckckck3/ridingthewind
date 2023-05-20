package com.ringdingdong.ridingthewind.controller;

import com.ringdingdong.ridingthewind.enumerate.ResponseResult;
import com.ringdingdong.ridingthewind.model.*;
import com.ringdingdong.ridingthewind.model.service.ArticleService;
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
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
@Api("댓글 컨트롤러 API V1")
public class CommentController {

	private final CommentService commentService;

	@ApiOperation(value = "댓글 작성", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> write(@RequestBody @ApiParam(value = "댓글 정보", required = true) CommentDto commentDto) throws Exception {
		if (commentService.writeComment(commentDto)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 삭제", notes = "댓글 번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo") @ApiParam(value = "삭제할 댓글의 글번호.", required = true) int commentNo) throws Exception {
		if (commentService.deleteComment(commentNo)) {
			return new ResponseEntity<>(ResponseResult.SUCCESS.name(), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseResult.FAIL.name(), HttpStatus.NO_CONTENT);
	}

}
