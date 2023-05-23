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
	List<TourDto> getList(int sidoCode, int gugunCode) throws Exception;
	List<TourDto> getList(String memberId) throws Exception;
	List<TourDto> getListByKeyword(Map<String, Object> map) throws Exception;

	List<TourDto> getListByContentIds(List<Integer> contentIds) throws Exception;

	List<PersonalTripDto> getLikeList(String memberId) throws Exception;
	boolean addtour(int contentId, String memberId) throws Exception;

	
	boolean addSidoCount(int sidoCode, int birthyear) throws Exception;
	boolean addGugunCount(int sidoCode, int gugunCode, int birthyear) throws Exception;
	boolean addPostCount(int contentId) throws Exception;
	
	
	

}
