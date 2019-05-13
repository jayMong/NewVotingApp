package com.example.demo;

import com.example.demo.VotingVO;
import java.util.List;
import java.util.Map;

public interface VotingService {
	public List<Map<String, String>> findAll();
	
	public long selectExemSeq() ;
    public long selectQuestionSeq() ;
    public long selectItemSeq() ;
    
    public List<VotingVO> select(VotingVO vo) ;
        
    public List<VotingVO> selectExemList(VotingVO vo) ;
        
    public List<VotingVO> selectQuestionList(VotingVO vo) ;

    public List<VotingVO> selectItemList(VotingVO vo) ;
    
    public Integer insert(VotingVO vo) ;
        
    public Integer update(VotingVO vo) ;
        
    public Integer delete(VotingVO vo) ;
        
    public Integer insert(List<VotingVO> vo) ;
        
    public Integer update(List<VotingVO> vo) ;
        
    public Integer delete(List<VotingVO> vo) ;
    
    public Integer insertQuestionMaster(VotingVO vo) ;
    
    public Integer updateQuestionMaster(VotingVO vo) ;
    
    public Integer deleteQuestionMaster(List<VotingVO> vo) ;

    public Integer insertItemMaster(VotingVO vo) ;
    
    public Integer updateItemMaster(VotingVO vo) ;
    
    public Integer deleteItemMaster(List<VotingVO> vo) ;

    public Integer insertQuestionHistroy(VotingVO vo) ;

    public List<VotingVO> selectUserExemList(VotingVO vo) ;
    public List<VotingVO> selectUserQuestionList(VotingVO vo) ; 
    public List<VotingVO> selectUserItemList(VotingVO vo) ;    
    
}