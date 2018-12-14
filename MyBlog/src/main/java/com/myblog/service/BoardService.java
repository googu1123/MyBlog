package com.myblog.service;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.myblog.dao.BoardDao;


@Service("BoardService")
public class BoardService 
{
    @Resource(name="BoardDao")
    private BoardDao boardDao;
    
    //총게시물 카운트
    public int getBoardListTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return boardDao.getBoardListTotalCount(reqHashMap);
    }
    
    //게시판 리스트 조회
    public List<HashMap<String, Object>> getBoardList(HashMap<String , Object> reqHashMap) 
    {
        return boardDao.getBoardList(reqHashMap);
    }
    
    //최신 게시물 조회
    public List<HashMap<String, Object>> getBoardRecentList(HashMap<String , Object> reqHashMap) 
    {
        return boardDao.getBoardRecentList(reqHashMap);
    }
    
    //게시물 상세 보기
    public HashMap<String, Object> getBoardView(HashMap<String , Object> reqHashMap) 
    {
    	return boardDao.getBoardView(reqHashMap);
    }
    
    //게시물 조회수 Update
    public boolean boardUpdateReadCnt(HashMap<String , Object> reqHashMap) 
    {
    	return boardDao.boardUpdateReadCnt(reqHashMap);
    }
    
    //게시물 저장
    public boolean boardInsert(HashMap<String , Object> reqHashMap) 
    {
    	return boardDao.boardInsert(reqHashMap);
    }
    
    //게시물 삭제
    public boolean boardDelete(HashMap<String , Object> reqHashMap) 
    {
    	return boardDao.boardDelete(reqHashMap);
    }
    
    //카테고리 리스트 조회
    public List<HashMap<String, Object>> getCategoryList(HashMap<String , Object> reqHashMap) 
    {
        return boardDao.getCategoryList(reqHashMap);
    }
}
