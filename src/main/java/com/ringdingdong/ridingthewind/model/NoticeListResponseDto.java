package com.ringdingdong.ridingthewind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class NoticeListResponseDto {

    private List<ArticleDto> articles;
    private PageNavigationResponseDto pageNavigation;

}
