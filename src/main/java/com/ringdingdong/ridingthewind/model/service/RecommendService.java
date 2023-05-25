package com.ringdingdong.ridingthewind.model.service;

import java.util.List;

import com.ringdingdong.ridingthewind.model.TourDto;

public interface RecommendService {

	List<TourDto> getSidoCountList() throws Exception;
	List<TourDto> getGugunCountList(int sidoCode) throws Exception;
	List<TourDto> getMostLikeTourList() throws Exception;
	List<TourDto> getPopularTourList(String sidoGugun, String orderByWhat) throws Exception;
	List<TourDto> getPopularContentSido(int sidoCode) throws Exception;
	List<TourDto> getPopularContentGugun(int sidoCode, int gugunCode) throws Exception;
}
