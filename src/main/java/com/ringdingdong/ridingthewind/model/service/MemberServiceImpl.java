package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.MemberDto;
import com.ringdingdong.ridingthewind.model.mapper.MemberMapper;
import com.ringdingdong.ridingthewind.util.PasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MemberServiceImpl implements MemberService {
	
//	@Autowired
//	private SqlSession sqlSession;
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String memberId) throws Exception {
		return memberMapper.idCheck(memberId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		memberDto.setMemberPassword(PasswordEncryptor.encrypt(memberDto.getMemberPassword()));
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		//map.replace("memberPw", PasswordEncryptor.encrypt(map.get("memberPw")));
		return memberMapper.loginMember(map);
	}
	
	/* ADMIN */
	@Override
	public List<MemberDto> listMember(Map<String, Object> map) throws Exception {
		return memberMapper.listMember(map);
	}

	@Override
	public MemberDto getMember(String memberId) throws Exception {
		return memberMapper.getMember(memberId);
	}

	@Override
	public int updateMember(MemberDto memberDto) throws Exception {
		return memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(String memberId) throws Exception {
		memberMapper.deleteMember(memberId);		
	}


}
