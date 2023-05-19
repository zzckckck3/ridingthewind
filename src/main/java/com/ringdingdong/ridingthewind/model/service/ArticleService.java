package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import com.ringdingdong.ridingthewind.model.ArticleListResponseDto;
import com.ringdingdong.ridingthewind.model.PageNavigationResponseDto;

public interface ArticleService {

	boolean writeArticle(ArticleDto articleDto) throws Exception;
	ArticleListResponseDto listArticle(ArticleParameterDto articleParameterDto) throws Exception;
	PageNavigationResponseDto makePageNavigation(ArticleParameterDto articleParameterDto) throws Exception;
	ArticleDto getArticle(int articleNo) throws Exception;
	boolean updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(ArticleDto articleDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
