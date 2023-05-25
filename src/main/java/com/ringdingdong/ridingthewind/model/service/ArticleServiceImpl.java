package com.ringdingdong.ridingthewind.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringdingdong.ridingthewind.enumerate.Constant;
import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.ArticleDetailDto;
import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleListResponseDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import com.ringdingdong.ridingthewind.model.PageNavigationResponseDto;
import com.ringdingdong.ridingthewind.model.TourDto;
import com.ringdingdong.ridingthewind.model.mapper.ArticleAttractionMapper;
import com.ringdingdong.ridingthewind.model.mapper.ArticleMapper;
import com.ringdingdong.ridingthewind.model.mapper.CommentMapper;
import com.ringdingdong.ridingthewind.model.mapper.TourMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class ArticleServiceImpl implements ArticleService {

	private final ArticleMapper articleMapper;
	private final CommentMapper commentMapper;
	private final ArticleAttractionMapper articleAttractionMapper;
	private final TourMapper tourMapper;

	@Override
	public boolean writeArticle(ArticleDto articleDto) throws Exception {
		int success = articleMapper.writeArticle(articleDto);
		if (articleDto.getArticleAttractionList().size() != 0) {
			articleDto.getArticleAttractionList().forEach(articleAttractionDto -> articleAttractionDto.setArticleNo(articleDto.getArticleNo()));
			return articleAttractionMapper.writeArticleAttraction(articleDto.getArticleAttractionList()) != 0;
		}

		return success == 1;
	}

	@Override
	public ArticleListResponseDto listArticle(ArticleParameterDto articleParameterDto) throws Exception {
		return ArticleListResponseDto.builder()
				.articles(articleMapper.listArticle(articleParameterDto))
				.pageNavigation(makePageNavigation(articleParameterDto))
				.build();
	}
	
	@Override
	public ArticleListResponseDto listArticleWithRecommend(ArticleParameterDto articleParameterDto) throws Exception {
		return ArticleListResponseDto.builder()
				.articles(articleMapper.listArticleWithRecommend(articleParameterDto))
				.pageNavigation(makePageNavigation(articleParameterDto))
				.build();
	}

	@Override
	public PageNavigationResponseDto makePageNavigation(ArticleParameterDto articleParameterDto) throws Exception {
		int spp = articleParameterDto.getSpp();
		int totalCount = articleMapper.getTotalArticleCount(articleParameterDto);
		int maxPage = (totalCount - 1) / spp + 1;

		return PageNavigationResponseDto.builder()
				.naviSize(Constant.BASIC.getNavigationSize())
				.spp(spp)
				.curPage(articleParameterDto.getCurPage())
				.maxPage(maxPage)
				.build();
	}
	
	@Override
    public ArticleDetailDto getArticle(int articleNo) throws Exception {
        ArticleDetailDto articleDetailDto = articleMapper.getArticle(articleNo);
        articleDetailDto.setCommentList(commentMapper.listComment(articleNo));

        List<Integer> contentIds = articleAttractionMapper.listArticleAttraction(articleNo).stream().map(ArticleAttractionDto::getContentId).collect(Collectors.toList());
        List<TourDto> result = tourMapper.getListByContentIds(contentIds);
        List<TourDto> ret = new ArrayList<>();

        for (Integer contentId : contentIds) {
            for (TourDto tourDto : result) {
                if (tourDto.getContentId() == contentId) {
                    ret.add(tourDto);
                    break;
                }
            }
        }

        articleDetailDto.setTourList(ret);
        return articleDetailDto;
    }

	@Override
	public boolean updateHit(int articleNo) throws Exception {
		return articleMapper.updateHit(articleNo) == 1;
	}

	@Override
	public boolean modifyArticle(ArticleDto articleDto) throws Exception {
		articleAttractionMapper.deleteArticleAttraction(articleDto.getArticleNo());
		if (articleDto.getArticleAttractionList().size() != 0) {
			articleAttractionMapper.writeArticleAttraction(articleDto.getArticleAttractionList());
		}
		return articleMapper.modifyArticle(articleDto) == 1;
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		return articleMapper.deleteArticle(articleNo) == 1;
	}

}
