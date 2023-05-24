package com.ringdingdong.ridingthewind.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.mapper.RecommendMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

	private final RecommendMapper recommendMapper;

	@Override
	public List<TourDto> getSidoCountList() throws Exception {
		return recommendMapper.getSidoCountList();
	}

	@Override
	public List<TourDto> getGugunCountList(int sidoCode) throws Exception {
		return recommendMapper.getGugunCountList(sidoCode);
	}

	@Override
	public List<TourDto> getMostLikeTourList() throws Exception {
		return recommendMapper.getMostLikeTourList();
	}

	@Override
	public List<TourDto> getPopularTourList(String sidoGugun, String orderByWhat) throws Exception {
		return recommendMapper.getPopularTourList(sidoGugun, orderByWhat);
	}

	@Override
	public List<TourDto> getPopularContentSido(int sidoCode) throws Exception {
		return recommendMapper.getPopularContentSido(sidoCode);
	}

	@Override
	public List<TourDto> getPopularContentGugun(int sidoCode, int gugunCode) throws Exception {
		return recommendMapper.getPopularContentGugun(sidoCode, gugunCode);
	}




}
