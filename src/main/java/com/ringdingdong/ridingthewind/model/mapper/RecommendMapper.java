package com.ringdingdong.ridingthewind.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ringdingdong.ridingthewind.model.TourDto;

@Mapper
public interface RecommendMapper {
	List<TourDto> getSidoCountList() throws SQLException;
	List<TourDto> getGugunCountList(@Param("sidoCode") int sidoCode) throws SQLException;

}