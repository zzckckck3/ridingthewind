package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.MemberDto;

import java.util.List;
import java.util.Map;


public interface MemberService {


	int idCheck(String memberId) throws Exception;
	void joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(Map<String, String> map) throws Exception;
	
	/* Admin */
	List<MemberDto> listMember(Map<String, Object> map) throws Exception;
	MemberDto getMember(String memberId) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	void deleteMember(String memberId) throws Exception;

	void saveRefreshToken(String memberId, String refreshToken);

	MemberDto findMemberId(String memberId);

    void deleteRefreshToken(String userId);

    int passwordCheck(Map<String, String> map);

    void setPassword(Map<String, String> map);
}
