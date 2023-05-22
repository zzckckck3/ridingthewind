package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.ArticleAttractionDto;
import com.ringdingdong.ridingthewind.model.LikeDto;
import com.ringdingdong.ridingthewind.model.mapper.ArticleAttractionMapper;
import com.ringdingdong.ridingthewind.model.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 삭제 예정
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

	private final LikeMapper likeMapper;

	@Override
	public boolean insertLike(LikeDto likeDto) throws Exception {
		return likeMapper.insertLike(likeDto) == 1;
	}

	@Override
	public boolean likeThisArticle(LikeDto likeDto) throws Exception {
		return likeMapper.likeThisArticle(likeDto) == 1;
	}

	@Override
	public boolean deleteLike(LikeDto likeDto) throws Exception {
		return likeMapper.deleteLike(likeDto) == 1;
	}
}
