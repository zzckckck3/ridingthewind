package com.ringdingdong.ridingthewind.model.service;

import java.util.List;

import com.ringdingdong.ridingthewind.model.TourDto;

public interface RecommendService {

	List<TourDto> getSidoCountList() throws Exception;
	List<TourDto> getGugunCountList(int sidoCode) throws Exception;
}
