package com.ringdingdong.ridingthewind.model.mapper;

import com.ringdingdong.ridingthewind.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
	int idCheck(String memberId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	
	/* Admin */
	List<MemberDto> listMember(Map<String, Object> map) throws SQLException;
	MemberDto getMember(String memberId) throws SQLException;
	int updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String memberId) throws SQLException;


	void saveRefreshToken(Map<String, String> map);

    MemberDto findMemberId(String memberId);

	void deleteRefreshToken(Map<String, String> map);

	int passwordCheck(Map<String, String> map);

    void setPassword(Map<String, String> map);

    ArrayList<String> getFollowingList(String userId);

	ArrayList<String> getFollowerList(String userId);

    int checkFollow(Map<String, String> map);

	int deleteFollow(Map<String, String> map);

	int insertFollow(Map<String, String> map);
}
