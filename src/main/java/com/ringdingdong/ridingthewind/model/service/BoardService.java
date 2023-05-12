package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.BoardDto;
import com.ringdingdong.ridingthewind.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface BoardService {

	boolean writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	boolean updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(BoardDto boardDto) throws Exception;
	boolean deleteArticle(int articleNo) throws Exception;
	
}
