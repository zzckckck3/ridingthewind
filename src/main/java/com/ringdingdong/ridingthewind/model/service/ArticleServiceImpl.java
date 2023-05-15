package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.ArticleDto;
import com.ringdingdong.ridingthewind.model.mapper.ArticleMapper;
import com.ringdingdong.ridingthewind.util.PageNavigation;
import com.ringdingdong.ridingthewind.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleMapper articleMapper;

	@Override
	public boolean writeArticle(ArticleDto articleDto) throws Exception {
		return articleMapper.writeArticle(articleDto) == 1;
	}

	@Override
	// 변경 예정
	public List<ArticleDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return articleMapper.listArticle(param);
	}
	
	@Override
	// 변경 예정
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if ("memberid".equals(key))
//			key = "member_id";
		param.put("key",  key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = articleMapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
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
