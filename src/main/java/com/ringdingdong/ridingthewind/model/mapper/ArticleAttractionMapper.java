package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ArticleAttractionMapper {
	// Insert
	int writeArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws SQLException;

	// Select
	List<ArticleAttractionDto> listArticleAttraction(int articleNo) throws SQLException;

	// Delete
	int deleteArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws SQLException;
}
