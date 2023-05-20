package com.ringdingdong.ridingthewind.model;

public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberPhone;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	private String birth;
	private String nickname;
	private String role;

	public String getBirth() {
		return birth;
	}

	@Override
	public String toString() {
		return "MemberDto{" +
				"memberId='" + memberId + '\'' +
				", memberName='" + memberName + '\'' +
				", memberPassword='" + memberPassword + '\'' +
				", memberPhone='" + memberPhone + '\'' +
				", emailId='" + emailId + '\'' +
				", emailDomain='" + emailDomain + '\'' +
				", joinDate='" + joinDate + '\'' +
				", birth='" + birth + '\'' +
				", nickname='" + nickname + '\'' +
				", role='" + role + '\'' +
				'}';
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmailDomain() {
		return emailDomain;
	}
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
