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


}