package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.PersonalTripDto;

import java.util.List;

public interface PersonalTripService {
	
	/* API */
	List<PersonalTripDto> listPersonalTrip(String memberId) throws Exception;
	void deleteTrip(String memberId, int contentId) throws Exception;
	void editMember(MemberDto memberDto) throws Exception;
	void deleteMember(String memberId, String memberPassword)throws Exception;
}
