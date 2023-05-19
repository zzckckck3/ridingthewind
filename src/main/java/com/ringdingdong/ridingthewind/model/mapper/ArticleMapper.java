package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.ArticleDetailDto;
import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
	// Insert
	int writeArticle(ArticleDto articleDto) throws SQLException;

	// Select
	List<ArticleDto> listArticle(ArticleParameterDto articleParameterDto) throws SQLException;
	int getTotalArticleCount(ArticleParameterDto articleParameterDto) throws SQLException;
	ArticleDetailDto getArticle(int articleNo) throws SQLException;

	// Update
	int updateHit(int articleNo) throws SQLException;
	int modifyArticle(ArticleDto articleDto) throws SQLException;

	// Delete
	int deleteArticle(int articleNo) throws SQLException;
}
