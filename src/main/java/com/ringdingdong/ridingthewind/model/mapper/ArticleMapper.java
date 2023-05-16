package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.ArticleDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
	// Insert
	int writeArticle(ArticleDto articleDto) throws SQLException;

	// Select
	List<ArticleDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	ArticleDto getArticle(int articleNo) throws SQLException;

	// Update
	int updateHit(int articleNo) throws SQLException;
	int modifyArticle(ArticleDto articleDto) throws SQLException;

	// Delete
	int deleteArticle(int articleNo) throws SQLException;
}
