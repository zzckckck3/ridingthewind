package com.ringdingdong.ridingthewind.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.PersonalTripDto;
import com.ringdingdong.ridingthewind.model.mapper.PersonalTripMapper;
import com.ringdingdong.ridingthewind.util.PasswordEncryptor;

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
	@Transactional
	public boolean editMember(MemberDto memberDto) throws Exception {
		memberDto.setMemberPassword(PasswordEncryptor.encrypt(memberDto.getMemberPassword()));
		return personalTripMapper.editMember(memberDto) == 1;
		
	}

	@Override
	@Transactional
	public boolean deleteMember(String memberId, String memberPassword) throws Exception {
		memberPassword = PasswordEncryptor.encrypt(memberPassword);
		return personalTripMapper.deleteMember(memberId, memberPassword) == 1;
	}
	

}
