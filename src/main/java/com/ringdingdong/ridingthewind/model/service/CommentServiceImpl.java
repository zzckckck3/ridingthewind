package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.enumerate.Constant;
import com.ringdingdong.ridingthewind.model.*;
import com.ringdingdong.ridingthewind.model.mapper.ArticleMapper;
import com.ringdingdong.ridingthewind.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		return commentMapper.writeComment(commentDto) == 1;
	}

	@Override
	public boolean deleteComment(int commentNo) throws Exception {
		return commentMapper.deleteComment(commentNo) == 1;
	}
}
