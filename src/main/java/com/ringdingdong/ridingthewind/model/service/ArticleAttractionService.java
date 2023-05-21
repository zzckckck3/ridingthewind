package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.*;

import java.util.List;

public interface ArticleAttractionService {

	boolean writeArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;
	boolean deleteArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;

	boolean updateArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;
}
