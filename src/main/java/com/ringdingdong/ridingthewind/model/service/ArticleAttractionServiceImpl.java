package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.*;
import com.ringdingdong.ridingthewind.model.mapper.ArticleAttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleAttractionServiceImpl implements ArticleAttractionService {

	private final ArticleAttractionMapper articleAttractionMapper;

	@Override
	public boolean writeArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception {
		return articleAttractionMapper.writeArticleAttraction(articleAttractionList) == 1;
	}

	@Override
	public boolean updateArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception {
		return writeArticleAttraction(articleAttractionList) && deleteArticleAttraction(articleAttractionList);
	}

	@Override
	public boolean deleteArticleAttraction(List<ArticleAttractionDto> articleAttractionList) throws Exception {
		return articleAttractionMapper.deleteArticleAttraction(articleAttractionList) == 1;
	}

}
