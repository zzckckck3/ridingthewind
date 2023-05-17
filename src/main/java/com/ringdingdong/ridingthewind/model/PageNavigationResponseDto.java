package com.ringdingdong.ridingthewind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PageNavigationResponseDto {

	private int curPage;		// 현재 페이지 번호
	private int spp;		// 페이지당 글 갯수
	private int maxPage;		// 총 페이지 갯수
	private int naviSize;		// 네비게이션 사이즈

}
