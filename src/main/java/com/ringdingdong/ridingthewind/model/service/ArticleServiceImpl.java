package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.enumerate.Constant;
import com.ringdingdong.ridingthewind.model.*;
import com.ringdingdong.ridingthewind.model.mapper.*;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
		articleDetailDto.setTourList(tourMapper.getListByContentIds(articleAttractionMapper.listArticleAttraction(articleNo).stream().map(ArticleAttractionDto::getContentId).collect(Collectors.toList())));
		return articleDetailDto;
	}

	@Override
	public boolean updateHit(int articleNo) throws Exception {
		return articleMapper.updateHit(articleNo) == 1;
	}

	@Override
	public boolean modifyArticle(ArticleDto articleDto) throws Exception {
		if (articleDto.getArticleAttractionList().size() != 0) {
			articleAttractionMapper.deleteArticleAttraction(articleDto.getArticleNo());
			articleAttractionMapper.writeArticleAttraction(articleDto.getArticleAttractionList());
		}
		return articleMapper.modifyArticle(articleDto) == 1;
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		return articleMapper.deleteArticle(articleNo) == 1;
	}

}
