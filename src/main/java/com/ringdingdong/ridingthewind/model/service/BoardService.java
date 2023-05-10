package com.ringdingdong.ridingthewind.model.service;

import com.ringdingdong.ridingthewind.model.BoardDto;
import com.ringdingdong.ridingthewind.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	
}
