package com.ringdingdong.ridingthewind.model;

public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberPhone;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	private String birthday;
	private String nickname;
	
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
				", birthday='" + birthday + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}
}
