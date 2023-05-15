package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PersonalTripMapper {
	
	List<PersonalTripDto> listPersonalTrip(String memberId) throws SQLException; // 전체 리스트 띄우기
	void deleteTrip(@Param("memberId") String memberId, @Param("contentId") int contentId) throws SQLException;
	int editMember(MemberDto memberDto) throws SQLException;
	int deleteMember(@Param("memberId") String memberId, @Param("memberPassword") String memberPassword) throws SQLException;
	
}
