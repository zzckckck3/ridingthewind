package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.TourGugunDto;
import com.ringdingdong.ridingthewind.model.TourSidoDto;
import com.ringdingdong.ridingthewind.model.mapper.TourMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Year;
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
	
	// 사용자의 생일 정보에서 들어온 birthyear가 ageRange로 변환되어 Mapper로 넘어간다
	@Override
	public boolean addSidoCount(int sidoCode, int birthyear) throws Exception {
		Year currentYear = Year.now();
		int year = currentYear.getValue();
		int age = (year - birthyear) / 10;
		String ageRange = "";
		
		switch (age) {
	    case 0:
	    case 1:
	        ageRange = "ten";
	        break;
	    case 2:
	    	ageRange = "twenty";
	        break;
	    case 3:
	    	ageRange = "thirty";
	        break;
	    case 4:
	    	ageRange = "fourty";
	        break;
	    case 5:
	    	ageRange = "fifty";
	        break;
	    case 6:
	    	ageRange = "sixty";
	        break;
	    default:
	    	ageRange = "no_input";
	        break;
		}
		
		System.out.println("=============================");
		System.out.println(ageRange);
		System.out.println("=============================");
		
		return (tourMapper.addSidoCount(sidoCode) == 1 &&
				tourMapper.addSidoCountAge(sidoCode, ageRange) == 1);
	}
	
	@Override
	public List<TourGugunDto> getGugun(int sidoCode) throws Exception {
		return tourMapper.getGugun(sidoCode);
	}
	
	// 사용자의 생일 정보에서 들어온 birthyear가 ageRange로 변환되어 Mapper로 넘어간다
	@Override
	public boolean addGugunCount(int sidoCode, int gugunCode, int birthyear) throws Exception {
		Year currentYear = Year.now();
		int year = currentYear.getValue();
		int age = (year - birthyear) / 10;
		String ageRange = "";
		
		switch (age) {
	    case 0:
	    case 1:
	        ageRange = "ten";
	        break;
	    case 2:
	    	ageRange = "twenty";
	        break;
	    case 3:
	    	ageRange = "thirty";
	        break;
	    case 4:
	    	ageRange = "fourty";
	        break;
	    case 5:
	    	ageRange = "fifty";
	        break;
	    case 6:
	    	ageRange = "sixty";
	        break;
	    default:
	    	ageRange = "no_input";
	        break;
		}
		System.out.println("=============================");
		System.out.println(ageRange);
		System.out.println("=============================");
		
		return (tourMapper.addGugunCount(sidoCode, gugunCode) == 1 &&
				tourMapper.addGugunCountAge(sidoCode, gugunCode, ageRange) == 1);
	}
	
	@Override
	public boolean addPostCount(int contentId) throws Exception {
		return tourMapper.addPostCount(contentId) == 1;
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
