package com.ringdingdong.ridingthewind.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;

public interface TourMapper {
	List<TourSidoDto> getSido() throws Exception;
	List<TourGugunDto> getGugun(int sidoCode) throws Exception;
	List<TourDto> getList(int sidoCode, int gugunCode) throws SQLException;
	List<TourDto> getList(String memberId) throws SQLException;
	List<TourDto> getListByKeyword(Map<String, Object> map) throws SQLException;
	int addtour(@Param("contentId") int contentId, @Param("memberId") String memberId) throws SQLException;
	List<PersonalTripDto> getLikeList(String memberId) throws SQLException;
}
