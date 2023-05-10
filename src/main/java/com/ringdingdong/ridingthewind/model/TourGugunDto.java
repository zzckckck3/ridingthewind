package com.ringdingdong.ridingthewind.model;

public class TourGugunDto {
	private int gugunCode;
	private String gugunName;
	
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	
	@Override
	public String toString() {
		return "TourGugunDto [gugunCode=" + gugunCode + ", gugunName=" + gugunName + "]";
	}
	

	
	
	
	
	
}
