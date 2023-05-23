package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.*;

import java.util.List;

public interface ArticleService {

	boolean writeArticle(ArticleDto articleDto) throws Exception;
	ArticleListResponseDto listArticle(ArticleParameterDto articleParameterDto) throws Exception;
	List<ArticleDetailDto> listHotArticle(int period) throws Exception;
	PageNavigationResponseDto makePageNavigation(ArticleParameterDto articleParameterDto) throws Exception;
	ArticleDetailDto getArticle(int articleNo) throws Exception;
	boolean updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(ArticleDto articleDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
