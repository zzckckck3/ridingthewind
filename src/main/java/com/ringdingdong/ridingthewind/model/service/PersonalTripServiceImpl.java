package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.mapper.PersonalTripMapper;
import com.ringdingdong.ridingthewind.util.PasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTripServiceImpl implements PersonalTripService {

	private PersonalTripMapper personalTripMapper;
	
	public PersonalTripServiceImpl (PersonalTripMapper personalTripMapper) {
		super();
		this.personalTripMapper = personalTripMapper;
	}

	@Override
	public List<PersonalTripDto> listPersonalTrip(String memberId) throws Exception {
		return personalTripMapper.listPersonalTrip(memberId);
	}

	@Override
	public void deleteTrip(String memberId, int contentId) throws Exception {
		personalTripMapper.deleteTrip(memberId, contentId);
	}

	@Override
	public void editMember(MemberDto memberDto) throws Exception {
		memberDto.setMemberPassword(PasswordEncryptor.encrypt(memberDto.getMemberPassword()));
		personalTripMapper.editMember(memberDto);
		
	}

	@Override
	public void deleteMember(String memberId, String memberPassword) throws Exception {
		memberPassword = PasswordEncryptor.encrypt(memberPassword);
		personalTripMapper.deleteMember(memberId, memberPassword);
	}
	

}
