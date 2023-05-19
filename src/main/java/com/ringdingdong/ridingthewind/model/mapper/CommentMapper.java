package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {
    // Insert
    int writeComment(CommentDto commentDto) throws SQLException;

    // Select
    List<CommentDto> listComment(int articleNo) throws SQLException;

    // Delete
    int deleteComment(int commentNo) throws SQLException;

}
