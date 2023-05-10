package com.ringdingdong.ridingthewind.model;


public class TourSidoDto {
	private int sidoCode;
	private String sidoName;
	public int getSidoCode() {
		return sidoCode;
	}
	
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	
	@Override
	public String toString() {
		return "TourSidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
	
	
}
