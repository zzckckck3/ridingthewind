package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
	// Insert
	int writeArticle(BoardDto boardDto) throws SQLException;

	// Select
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;

	// Update
	int updateHit(int articleNo) throws SQLException;
	int modifyArticle(BoardDto boardDto) throws SQLException;

	// Delete
	int deleteArticle(int articleNo) throws SQLException;
}
