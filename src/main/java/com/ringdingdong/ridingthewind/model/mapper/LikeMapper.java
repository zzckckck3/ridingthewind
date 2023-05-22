package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.LikeDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface LikeMapper {
	// Insert
	int insertLike(LikeDto likeDto) throws SQLException;

	// Select
	int likeThisArticle(LikeDto likeDto) throws SQLException;
	List<Integer> likeArticleList(String memberId) throws SQLException;

	// Delete
	int deleteLike(LikeDto likeDto) throws SQLException;
}
