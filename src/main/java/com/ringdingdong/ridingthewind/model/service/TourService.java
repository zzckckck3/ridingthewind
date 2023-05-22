package com.ringdingdong.ridingthewind.model.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;

public interface TourService {
	List<TourSidoDto> getSido() throws Exception;
	List<TourGugunDto> getGugun(int sidoCode) throws Exception;
	List<TourDto> getList(int sidoCode, int gugunCode) throws SQLException;
	List<TourDto> getList(String memberId) throws SQLException;
	List<TourDto> getListByKeyword(Map<String, Object> map) throws SQLException;

	List<TourDto> getListByContentIds(List<Integer> contentIds) throws SQLException;

	List<PersonalTripDto> getLikeList(String memberId) throws SQLException;
	boolean addtour(int contentId, String memberId) throws Exception;
	boolean addSidoCount(int sidoCode) throws Exception;
	boolean addGugunCount(int sidoCode, int gugunCode) throws Exception;
	
}
