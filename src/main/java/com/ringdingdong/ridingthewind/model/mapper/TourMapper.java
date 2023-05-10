package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface TourMapper {
	List<TourSidoDto> getSido() throws Exception;
	List<TourGugunDto> getGugun(int sidoCode) throws Exception;
	List<TourDto> getList(int sidoCode, int gugunCode) throws SQLException;
	List<TourDto> getList(String memberId) throws SQLException;
	void addtour(@Param("contentId") int contentId, @Param("memberId") String memberId) throws SQLException;
}
