package com.example.demo;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.VotingVO;
import com.example.demo.VotingService;
import com.example.demo.VotingMapper;

/**
 * Voting Service Implementation Class
 * 
 * @author jh.choi
 * @since 2019.04.07
 * @version 1.0
 * @see
 * 
 *      <pre>
 * 서비스의 구현체 클래스  
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자                수정내용
 *  -------    --------    ---------------------------
 *  2019.04.07 jh.choi     최초생성
 * 
 *      </pre>
 */

//@Service
@Service("votingService")
public class VotingServiceImpl implements VotingService {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private VotingMapper mapper;

    @Override
    public List<VotingVO> select(VotingVO vo)  {
        return mapper.selectExemList(vo);
    }

    @Override
    public long selectExemSeq()  {
        return mapper.selectExemSeq();
    }

    @Override
    public long selectQuestionSeq()  {
        return mapper.selectQuestionSeq();
    }

    @Override
    public long selectItemSeq()  {
        return mapper.selectItemSeq();
    }
    
    @Override
    public List<VotingVO> selectExemList(VotingVO vo)  {
        return mapper.selectExemList(vo);
    }
    
    @Override
    public List<VotingVO> selectQuestionList(VotingVO vo)  {
        return mapper.selectQuestionList(vo);
    }
    
    @Override
    public List<VotingVO> selectItemList(VotingVO vo)  {
        return mapper.selectItemList(vo);
    }
    
    
    @Override
    public Integer insert(VotingVO vo)  {
        return mapper.insert(vo);
    }
        
    @Override
    public Integer update(VotingVO vo)  {
        return mapper.update(vo);
    }
        
    @Override
    public Integer delete(VotingVO vo)  {
        return mapper.delete(vo);
    }
        
    @Override
    public Integer insert(List<VotingVO> vo)  {
    	return 1;
    	//return mapper.insert(vo);
    }
        
    @Override
    public Integer update(List<VotingVO> vo)  {
    	return 1;
    	//return mapper.update(vo);
    }
        
    @Override
    public Integer delete(List<VotingVO> vo)  {
        return mapper.delete(vo);
    }
    
    @Override
    public Integer insertQuestionMaster(VotingVO vo)  {
        return mapper.insertQuestionMaster(vo);
    }

    @Override
    public Integer updateQuestionMaster(VotingVO vo)  {
        return mapper.updateQuestionMaster(vo);
    }

    @Override
    public Integer deleteQuestionMaster(List<VotingVO> vo)  {
        return mapper.deleteQuestionMaster(vo);
    }

    @Override
    public Integer insertItemMaster(VotingVO vo)  {
        return mapper.insertItemMaster(vo);
    }

    @Override
    public Integer updateItemMaster(VotingVO vo)  {
        return mapper.updateItemMaster(vo);
    }

    @Override
    public Integer deleteItemMaster(List<VotingVO> vo)  {
        return mapper.deleteItemMaster(vo);
    }

    @Override
    public Integer insertQuestionHistroy(VotingVO vo)  {
        return mapper.insertQuestionHistroy(vo);
    }

	@Override
	public List<Map<String, String>> findAll() {		
        return mapper.findAll();
	}

    @Override
    public List<VotingVO> selectUserExemList(VotingVO vo)  {
        return mapper.selectUserExemList(vo);
    }
    
    @Override
    public List<VotingVO> selectUserQuestionList(VotingVO vo)  {
        return mapper.selectUserQuestionList(vo);
    }

    @Override
    public List<VotingVO> selectUserItemList(VotingVO vo)  {
        return mapper.selectUserItemList(vo);
    }
}
