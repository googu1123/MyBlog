package com.myblog.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

 
@Repository("BoardDao")
public class BoardDao 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private SqlSession sqlSession;
    
    @Autowired
	private DataSourceTransactionManager transactionManager ;
 
    public void setSqlSession(SqlSession sqlSession)
    {
    	this.sqlSession = sqlSession;
    }
 
    public int getBoardListTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("com.myblog.board.getBoardListTotalCount", reqHashMap);
    }
    	
    public List<HashMap<String , Object>> getBoardList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("com.myblog.board.getBoardList", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getBoardRecentList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("com.myblog.board.getBoardRecentList", reqHashMap);
    }
    
    public HashMap<String , Object> getBoardView(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("com.myblog.board.getBoardView", reqHashMap);
    }
    
    
    public boolean boardUpdateReadCnt(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("com.myblog.board.boardUpdateReadCnt",reqHashMap);
    		
    		transactionManager.commit(status);
    		
    		r = true;
    	}
    	catch (Exception e) 
    	{
    		log.error("------------------------");
    		log.error(e.toString());
    		log.error("------------------------");
    		
    		transactionManager.rollback(status);
    		r = false;
//    		throw e;
    	}
    	
    	
    	return r;
    }
    public boolean boardInsert(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("com.myblog.board.boardInsert",reqHashMap);
    		
    		transactionManager.commit(status);
    		
    		r = true;
    	}
    	catch (Exception e) 
    	{
    		log.error("------------------------");
    		log.error(e.toString());
    		log.error("------------------------");
    		
    		transactionManager.rollback(status);
    		r = false;
//    		throw e;
    	}
    	
    	
    	return r;
    }
    
    public boolean boardDelete(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("com.myblog.board.boardDelete",reqHashMap);
    		
    		transactionManager.commit(status);
    		
    		r = true;
    	}
    	catch (Exception e) 
    	{
    		log.error("------------------------");
    		log.error(e.toString());
    		log.error("------------------------");
    		
    		transactionManager.rollback(status);
    		r = false;
//    		throw e;
    	}
    	
    	
    	return r;
    }
    
    public List<HashMap<String , Object>> getCategoryList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("com.myblog.board.getCategoryList", reqHashMap);
    }
 
}

