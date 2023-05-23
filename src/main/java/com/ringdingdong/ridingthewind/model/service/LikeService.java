package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.LikeDto;

import java.util.List;

public interface LikeService {

	boolean insertLike(LikeDto likeDto) throws Exception;
	boolean likeThisArticle(LikeDto likeDto) throws Exception;
	boolean deleteLike(LikeDto likeDto) throws Exception;

}
