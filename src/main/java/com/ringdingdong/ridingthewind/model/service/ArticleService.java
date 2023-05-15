package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {

	boolean writeArticle(ArticleDto articleDto) throws Exception;
	List<ArticleDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	ArticleDto getArticle(int articleNo) throws Exception;
	boolean updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(ArticleDto articleDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
