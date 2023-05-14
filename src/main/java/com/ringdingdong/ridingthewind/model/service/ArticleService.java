package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.Article;
import com.ringdingdong.ridingthewind.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface ArticleService {

	boolean writeArticle(Article article) throws Exception;
	List<Article> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	Article getArticle(int articleNo) throws Exception;
	boolean updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(Article article) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
