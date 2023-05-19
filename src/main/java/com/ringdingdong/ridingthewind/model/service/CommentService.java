package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.*;

public interface CommentService {

	boolean writeComment(CommentDto commentDto) throws Exception;
	boolean deleteComment(int commentNo) throws Exception;
	
}
