package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.enumerate.Constant;
import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.ArticleParameterDto;
import com.ringdingdong.ridingthewind.model.ArticleListResponseDto;
import com.ringdingdong.ridingthewind.model.mapper.ArticleMapper;
import com.ringdingdong.ridingthewind.model.PageNavigationResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleMapper articleMapper;

	@Override
	public boolean writeArticle(ArticleDto articleDto) throws Exception {
		return articleMapper.writeArticle(articleDto) == 1;
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
	public ArticleDto getArticle(int articleNo) throws Exception {
		return articleMapper.getArticle(articleNo);
	}

	@Override
	public boolean updateHit(int articleNo) throws Exception {
		return articleMapper.updateHit(articleNo) == 1;
	}

	@Override
	public boolean modifyArticle(ArticleDto articleDto) throws Exception {
		return articleMapper.modifyArticle(articleDto) == 1;
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		return articleMapper.deleteArticle(articleNo) == 1;
	}

}
