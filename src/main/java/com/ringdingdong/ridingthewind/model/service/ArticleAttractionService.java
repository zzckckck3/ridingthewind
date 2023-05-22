package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.*;

import java.util.List;

// 삭제 예정
public interface ArticleAttractionService {

	boolean writeArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;
	boolean deleteArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;

	boolean updateArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception;
}
