package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface TourMapper {
	List<TourSidoDto> getSido() throws Exception;
	List<TourGugunDto> getGugun(int sidoCode) throws Exception;
	List<TourDto> getList(int sidoCode, int gugunCode) throws SQLException;
	List<TourDto> getList(String memberId) throws SQLException;
	List<TourDto> getListByKeyword(Map<String, Object> map) throws SQLException;
	List<TourDto> getListByContentIds(List<Integer> contentIds) throws SQLException;
	int addtour(@Param("contentId") int contentId, @Param("memberId") String memberId) throws SQLException;
	List<PersonalTripDto> getLikeList(String memberId) throws SQLException;
	List<TourDto> getMostLikeTourList() throws SQLException;
	
	
	int addSidoCount(@Param("sidoCode") int sidoCode) throws SQLException;
	int addSidoCountAge(@Param("sidoCode") int sidoCode, 
						@Param("ageRange") String ageRange) throws SQLException;
	int addGugunCount(@Param("sidoCode") int sidoCode, @Param("gugunCode") int gugunCode) throws SQLException;
	int addGugunCountAge(@Param("sidoCode") int sidoCode, 
						@Param("gugunCode") int gugunCode, 
						@Param("ageRange") String ageRange) throws SQLException;
	int addPostCount(@Param("contentId") int contentId) throws SQLException;

}
