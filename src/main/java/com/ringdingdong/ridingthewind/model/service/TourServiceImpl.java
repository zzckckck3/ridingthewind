package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import com.ringdingdong.ridingthewind.model.mapper.TourMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class TourServiceImpl implements TourService{
	
	private TourMapper tourMapper;
	
	public TourServiceImpl(TourMapper tourMapper) {
		super();
		this.tourMapper = tourMapper;
	}

	@Override
	public List<TourSidoDto> getSido() throws Exception {
		return tourMapper.getSido();
	}
	
	@Override
	public boolean addSidoCount(int sidoCode) throws Exception {
		return tourMapper.addSidoCount(sidoCode) == 1;
	}
	
	@Override
	public List<TourGugunDto> getGugun(int sidoCode) throws Exception {
		return tourMapper.getGugun(sidoCode);
	}
	
	@Override
	public boolean addGugunCount(int sidoCode, int gugunCode) throws Exception {
		return tourMapper.addGugunCount(sidoCode, gugunCode) == 1;
	}

	@Override
	public List<TourDto> getList(int sidoCode, int gugunCode) throws SQLException {
		return tourMapper.getList(sidoCode, gugunCode);
	}

	@Override
	public List<TourDto> getList(String memberId) throws SQLException {
		return tourMapper.getList(memberId);
	}
	
	@Override
	public List<TourDto> getListByKeyword(Map<String, Object> map) throws SQLException {
		return tourMapper.getListByKeyword(map);
	}

	@Override
	public List<TourDto> getListByContentIds(List<Integer> contentIds) throws SQLException {
		return tourMapper.getListByContentIds(contentIds);
	}

	@Override
	public List<PersonalTripDto> getLikeList(String memberId) throws SQLException {
		return tourMapper.getLikeList(memberId);
	}
	
	@Override
	public boolean addtour(int contentId, String memberId) throws Exception {
		return tourMapper.addtour(contentId, memberId) == 1;
	}

	@Override
	public List<TourDto> getPopularTourList() throws SQLException {
		return tourMapper.getPopularTourList();
	}


}
