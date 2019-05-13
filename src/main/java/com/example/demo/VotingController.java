package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.VotingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 클래스 설명
* @author 개발자
* @exception 메소드에서의 예외 확인
* @param 메소드의 매개변수
* @return 메소드의 반환값
* @see 다른 주제에 관한 링크 지정
* @serial 직렬화 필드
* @since 릴리즈 기록
* @throws 메소드에서의 예외
* @version 클래스의 버전
*/


/**
 * Voting WebApplication's Controller
 * 
 * @author jh.choi
 * @since 2019.04.07
 * @version 1.0
 * @see
 * 
 * <pre>
 * URL 호출시 호출
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자                수정내용
 *  -------    --------    ---------------------------
 *  2019.04.07 jh.choi     최초생성
 * 
 * </pre>
 */

@RestController
//@RestController = @Controller + ResponseBody
public class VotingController {
	protected final Logger logger = LoggerFactory.getLogger(VotingController.class);

    @Autowired
    private VotingService votingSvc;

	/**
	 * 관리자용 설문 관리 마스터 Web page 목록 호출 메소드
	 * @author jh.choi
	 * @exception Exception
	 * @param ModelAndView mv
	 * @return 호출 jsp 및 객체를 전달하기 위한 ModelAndView 반환값
	 */
    @RequestMapping(value = "/mgmt/votingExemMgmtList", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getSelectExemMasterList(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/mgmt/votingExemMgmtList getSelectExemMasterList Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        // 보여질 View jsp 페이지를 설정한다. WEB-INF / views / votingExemMgmtList.jsp
        mav.setViewName("votingExemMgmtList");
        
        return mav;
    }

    @RequestMapping(value = "/mgmt/votingExemMgmtSelect", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody void getSelectList(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		VotingVO vo){
        
        try {
            
            logger.info("/mgmt/votingMgmtSelect getSelect Call"); // print
            //logger.debug("_search = " + _search + " : nd = " + nd + " : rows = " + rows + " : pages = " + page + " : sidx = " + sidx + " : sord =" + sord);
            logger.info("sel_main_cd_id 1 = [" + request.getParameter("sel_main_cd_id") + "]");
            logger.info("votingMgmtSelect votingVO.getExem_id()  = [" + vo.getExem_id() + "]");

            boolean _search = false;
            if(request.getParameter("_search") != null) _search = java.lang.Boolean.parseBoolean(request.getParameter("_search"));
            long nd = 0;
            if(request.getParameter("nd") != null) nd = java.lang.Long.parseLong(request.getParameter("nd"));
            
            int rows = 0;
            if(request.getParameter("rows") != null) rows = java.lang.Integer.parseInt(request.getParameter("rows"));
            int page = 0;
            if(request.getParameter("page") != null) page = java.lang.Integer.parseInt(request.getParameter("page"));
            String sidx = "exem_id";
            if(request.getParameter("sidx") != null) sidx = request.getParameter("sidx");
            String sord = "";
            if(request.getParameter("sord") != null) sord = request.getParameter("sord");
            
            int start = (page - 1) * rows + 1;
            int limit = start + rows - 1;
            vo.setStartRowNum(start);
            vo.setEndRowNum(limit);
            
            // order by 조건세팅
            vo.setSidx(sidx);
            vo.setSord(sord);
            
            if (_search) {
                // 검색조건
                String searchField = request.getParameter("searchField");
                String searchString = request.getParameter("searchString");
                String searchOper = request.getParameter("searchOper");
                logger.info("searchField = " + searchField + " : searchOper = " + searchOper + " : searchString = " + searchString);
                
                /*
                 * http://www.trirand.com/jqgridwiki/doku.php?id=wiki:
                 * singe_searching Use this option to set common search rules.
                 * If not set all the available options will be used. jqGrid
                 * "sopt" option are:(sopt) [
                 * 'eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc','nu','nn']
                 * The corresponding texts are in language file and mean the
                 * following:(odata) ['equal','not equal', 'less', 'less or
                 * equal','greater','greater or equal', 'begins with','does not
                 * begin with','is in','is not in','ends with','does not end
                 * with','contains','does not contain','is null','is not null']
                 * Note that the elements in sopt array can be mixed in any
                 * order.
                 */
                
                // 검색조건 세팅
                vo.setSearchField(searchField);
                vo.setSearchOper(searchOper);
                vo.setSearchString(searchString.toLowerCase());
            }
            
            List<VotingVO> selectList = votingSvc.selectExemList(vo);
            
            ObjectMapper mapper = new ObjectMapper();
            
            Map<String, Object> modelMap = new HashMap<String, Object>();
            // total = Total Page
            // record = Total Records
            // rows = list data
            // page = current page
            
            long rowCnt = 0;
            if (selectList != null && selectList.size() > 0) {
                rowCnt = selectList.get(0).getRowCnt();
            }
            double total = (double) rowCnt / rows;
            modelMap.put("total", (int) Math.ceil(total));
            modelMap.put("records", rowCnt);
            modelMap.put("rows", selectList);
            modelMap.put("page", page);
            
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(), modelMap);
            
            logger.debug("After response");
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
    
    @RequestMapping(value = "/mgmt/votingExemMgmtUpdate", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody
    void update(HttpServletRequest request, HttpServletResponse response, VotingVO vo, @RequestParam
    String oper) {
        
        try {
            logger.info("oper = [" + oper + "]");
            
            int flag = 0;
            if (oper.equals("add")) {
                //Exem_id 만들기
            	long lSeq = votingSvc.selectExemSeq();
           	    String sSeq= "E-"+String.format("%018d", lSeq);
           	    vo.setExem_id(sSeq);
           	    
                flag = votingSvc.insert(vo);
                
            } else if (oper.equals("edit")) {
            	
                flag = votingSvc.update(vo);
                
            } else if (oper.equals("del")) {
                String[] arrMainCdId = vo.getExem_id().split(",");
                List<VotingVO> paramList = new ArrayList<VotingVO>();
                
                for (int i = 0; i < arrMainCdId.length; i++) {
                    VotingVO arrVO = new VotingVO();
                    arrVO.setExem_id(arrMainCdId[i]);
                    paramList.add(i, arrVO);
                }
                
                flag = votingSvc.delete(paramList);
                
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print("oper not found");
                return;
            }
            
            logger.info("flag = [" + flag + "]");
            
        } catch (Exception e) {
            e.printStackTrace();
            
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
	/**
	 * 메소드 설명
	 * @author jh.choi
	 * @exception Exception
	 * @param ModelAndView mv
	 * @return 호출 jsp 및 객체를 전달하기 위한 ModelAndView 반환값
	 */
    @RequestMapping(value = "/mgmt/votingQuestionMgmtList", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView votingQuestionMgmtList(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/mgmt/votingQuestMgmtList votingQuestionMgmtList Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        
        // 추가 버튼에 따른 속성코드값 여부 조회
        //HashMap<String, String> param = new HashMap<String, String>();
        //param.clear();
        //param.put("mapperInfo", "selectExemQuestionList");
        //vo.setSearchField("");
        // 입력된 조건으로 DB조회
        List<VotingVO> selectExemQuestion = votingSvc.selectQuestionList(vo);
        
        // 조회 결과 값을 뷰(jsp 페이지)로 넘기기 위해 selectList라는 이름으로 ModelAndView에 저장한다.
        mav.addObject("selectCombo", selectExemQuestion);
        logger.info("/mgmt/votingQuestionMgmtList selectExemQuestion Result : " + selectExemQuestion.size()); // print
        
        // 보여질 View 페이지를 설정한다.
        mav.setViewName("votingQuestionMgmtList");
        
        return mav;
    }

    @RequestMapping(value = "/mgmt/votingQuestionMgmtUpdate", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody
    void updateQuestionMaster(HttpServletRequest request, HttpServletResponse response, VotingVO vo, @RequestParam 
    String oper) {
        
        try {
            logger.info("oper = [" + oper + "]");
            
            int flag = 0;
            if (oper.equals("add")) {

                //Question_id 만들기
            	long lSeq = votingSvc.selectQuestionSeq();
           	    String sSeq= "Q-"+String.format("%018d", lSeq);
           	    vo.setQuestion_id(sSeq);
           	    
                flag = votingSvc.insertQuestionMaster(vo);
            } else if (oper.equals("edit")) {
                flag = votingSvc.updateQuestionMaster(vo);
            } else if (oper.equals("del")) {
                String[] arrMainCdId = vo.getQuestion_id().split(",");
                List<VotingVO> paramList = new ArrayList<VotingVO>();
                for (int i = 0; i < arrMainCdId.length; i++) {
                    VotingVO arrVO = new VotingVO();
                    arrVO.setQuestion_id(arrMainCdId[i]);
                    paramList.add(i, arrVO);
                }
                
                flag = votingSvc.deleteQuestionMaster(paramList);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print("oper not found");
                return;
            }
            
            logger.info("flag = [" + flag + "]");
            
        } catch (Exception e) {
            e.printStackTrace();
            
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


@RequestMapping(value = "/mgmt/votingQuestionMgmtSelect", method = { RequestMethod.POST, RequestMethod.GET })
public @ResponseBody
void getSelectQuestionMgmtList(
		HttpServletRequest request,
		HttpServletResponse response,
		VotingVO vo){
    
    try {

        logger.info("votingQuestionMgmtSelect vo.getExem_id()  = [" + vo.getExem_id() + "]");

        boolean _search = false;
        if(request.getParameter("_search") != null) _search = java.lang.Boolean.parseBoolean(request.getParameter("_search"));
        long nd = 0;
        if(request.getParameter("nd") != null) nd = java.lang.Long.parseLong(request.getParameter("nd"));
        
        int rows = 0;
        if(request.getParameter("rows") != null) rows = java.lang.Integer.parseInt(request.getParameter("rows"));
        int page = 0;
        if(request.getParameter("page") != null) page = java.lang.Integer.parseInt(request.getParameter("page"));
        String sidx = "question_order";
        if(request.getParameter("sidx") != null) sidx = request.getParameter("sidx");
        String sord = "";
        if(request.getParameter("sord") != null) sord = request.getParameter("sord");
        
        int start = (page - 1) * rows + 1;
        int limit = start + rows - 1;
        vo.setStartRowNum(start);
        vo.setEndRowNum(limit);
        
        // order by 조건세팅
        vo.setSidx(sidx);
        vo.setSord(sord);
        
        if (_search) {
            // 검색조건
            String searchField = request.getParameter("searchField");
            String searchString = request.getParameter("searchString");
            String searchOper = request.getParameter("searchOper");
            logger.info("searchField = " + searchField + " : searchOper = " + searchOper + " : searchString = " + searchString);
            
            /*
             * http://www.trirand.com/jqgridwiki/doku.php?id=wiki:
             * singe_searching Use this option to set common search rules.
             * If not set all the available options will be used. jqGrid
             * "sopt" option are:(sopt) [
             * 'eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc','nu','nn']
             * The corresponding texts are in language file and mean the
             * following:(odata) ['equal','not equal', 'less', 'less or
             * equal','greater','greater or equal', 'begins with','does not
             * begin with','is in','is not in','ends with','does not end
             * with','contains','does not contain','is null','is not null']
             * Note that the elements in sopt array can be mixed in any
             * order.
             */
            
            // 검색조건 세팅
            vo.setSearchField(searchField);
            vo.setSearchOper(searchOper);
            vo.setSearchString(searchString.toLowerCase());
        }
        
        List<VotingVO> selectList = votingSvc.selectQuestionList(vo);
        
        ObjectMapper mapper = new ObjectMapper();
        
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page
        
        long rowCnt = 0;
        if (selectList != null && selectList.size() > 0) {
            rowCnt = selectList.get(0).getRowCnt();
        }
        double total = (double) rowCnt / rows;
        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", rowCnt);
        modelMap.put("rows", selectList);
        modelMap.put("page", page);
        
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), modelMap);
        
        logger.debug("After response");
        
    } catch (Exception e) {
        e.printStackTrace();
        try {
            // 500 error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(e.getCause());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
}

@RequestMapping(value = "/mgmt/votingItemMgmtSelect", method = { RequestMethod.POST, RequestMethod.GET })
public @ResponseBody
void getSelectItemMgmtList(
		HttpServletRequest request,
		HttpServletResponse response,
		VotingVO vo){
    
    try {
        
        logger.info("/mgmt/votingItemMgmtSelect Call"); // print
        //logger.debug("_search = " + _search + " : nd = " + nd + " : rows = " + rows + " : pages = " + page + " : sidx = " + sidx + " : sord =" + sord);
        //logger.info("sel_main_cd_id 1 = [" + request.getParameter("sel_main_cd_id") + "]");
        logger.info("votingItemMgmtSelect vo.getQuestion_id()  = [" + vo.getQuestion_id() + "]");

        boolean _search = false;
        if(request.getParameter("_search") != null) _search = java.lang.Boolean.parseBoolean(request.getParameter("_search"));
        
        long nd = 0;
        if(request.getParameter("nd") != null) nd = java.lang.Long.parseLong(request.getParameter("nd"));
        
        int rows = 0;
        if(request.getParameter("rows") != null) rows = java.lang.Integer.parseInt(request.getParameter("rows"));
        int page = 0;
        if(request.getParameter("page") != null) page = java.lang.Integer.parseInt(request.getParameter("page"));
        String sidx = "item_order";
        if(request.getParameter("sidx") != null) sidx = request.getParameter("sidx");
        String sord = "";
        if(request.getParameter("sord") != null) sord = request.getParameter("sord");
        
        int start = (page - 1) * rows + 1;
        int limit = start + rows - 1;
        vo.setStartRowNum(start);
        vo.setEndRowNum(limit);
        
        // order by 조건세팅
        vo.setSidx(sidx);
        vo.setSord(sord);
        
        if (_search) {
            // 검색조건
            String searchField = request.getParameter("searchField");
            String searchString = request.getParameter("searchString");
            String searchOper = request.getParameter("searchOper");
            logger.info("searchField = " + searchField + " : searchOper = " + searchOper + " : searchString = " + searchString);
            
            /*
             * http://www.trirand.com/jqgridwiki/doku.php?id=wiki:
             * singe_searching Use this option to set common search rules.
             * If not set all the available options will be used. jqGrid
             * "sopt" option are:(sopt) [
             * 'eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc','nu','nn']
             * The corresponding texts are in language file and mean the
             * following:(odata) ['equal','not equal', 'less', 'less or
             * equal','greater','greater or equal', 'begins with','does not
             * begin with','is in','is not in','ends with','does not end
             * with','contains','does not contain','is null','is not null']
             * Note that the elements in sopt array can be mixed in any
             * order.
             */
            
            // 검색조건 세팅
            vo.setSearchField(searchField);
            vo.setSearchOper(searchOper);
            vo.setSearchString(searchString.toLowerCase());
        }
        
        List<VotingVO> selectList = votingSvc.selectItemList(vo);
        
        ObjectMapper mapper = new ObjectMapper();
        
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page
        
        long rowCnt = 0;
        if (selectList != null && selectList.size() > 0) {
            rowCnt = selectList.get(0).getRowCnt();
        }
        double total = (double) rowCnt / rows;
        modelMap.put("total", (int) Math.ceil(total));
        modelMap.put("records", rowCnt);
        modelMap.put("rows", selectList);
        modelMap.put("page", page);
        
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), modelMap);
        
        logger.debug("After response");
        
    } catch (Exception e) {
        e.printStackTrace();
        try {
            // 500 error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(e.getCause());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
}

@RequestMapping(value = "/mgmt/votingItemMgmtUpdate", method = { RequestMethod.POST, RequestMethod.GET })
public @ResponseBody
void updateItemMaster(HttpServletRequest request, HttpServletResponse response, VotingVO vo, @RequestParam
String oper) {
    
    try {
        logger.info("oper = [" + oper + "]");
        
        int flag = 0;
        if (oper.equals("add")) {
            //Item_id 만들기
        	long lSeq = votingSvc.selectItemSeq();
       	    String sSeq= "I-"+String.format("%018d", lSeq);
       	    vo.setItem_id(sSeq);
       	    
            flag = votingSvc.insertItemMaster(vo);
        } else if (oper.equals("edit")) {
            flag = votingSvc.updateItemMaster(vo);
        } else if (oper.equals("del")) {
            String[] arrMainCdId = vo.getItem_id().split(","); 
            List<VotingVO> paramList = new ArrayList<VotingVO>();
            for (int i = 0; i < arrMainCdId.length; i++) {
            	VotingVO arrVO = new VotingVO();
            	arrVO.setItem_id(arrMainCdId[i]);

                logger.info("delete setItem_id = [" + arrMainCdId[i] + "]");
                paramList.add(i, arrVO);
            }
            
            flag = votingSvc.deleteItemMaster(paramList);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print("oper not found");
            return;
        }
        
        logger.info("flag = [" + flag + "]");
        
    } catch (Exception e) {
        e.printStackTrace();
        
        try {
            // 500 error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(e.getCause());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

    // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
    @RequestMapping(value = "/mgmt/votingQuestMgmtList2", method= RequestMethod.GET)
    public ModelAndView votingQuestionMgmtList2(ModelAndView mv) throws Exception {
    	
    	// /mgmt/votingQuestMgmtList
    	//slf4j(Simple Logging Facade for Java): java의 로깅 모듈들의 추상체 사용
    	logger.info("UsersController.class => url : /mgmt/votingQuestMgmtList -> Method : votingQuestionMgmtList Call"); // print
    	//logger.debug("debug = ");
    	mv.addObject("result", votingSvc.findAll());
    	mv.setViewName("userslist"); //jsp Name
    	logger.info("votingQuestionMgmtList -> userslist.jsp Call"); // print
    	//System.out.println("userslist.jsp 호출");
        return mv;
        
    }
    

	/**
	 * 일반 사용자의 투표를 위한 로그인 폼 화면 호출
	 * @author jh.choi
	 * @exception Exception
	 * @param ModelAndView mv
	 * @return 호출 jsp 및 객체를 전달하기 위한 ModelAndView 반환값
	 */
    @RequestMapping(value = "/voting/login", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getVotingLoginForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/voting/login getVotingLoginForm Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        // 보여질 View 페이지를 설정한다.
        mav.getModel().put("emp_id",request.getParameter("emp_id"));
        mav.setViewName("/votingLoginForm");
        
        return mav;
    }

    // select
    @RequestMapping(value = "/voting/votingExemList", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getSelectExemList(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/voting/votingExemList getSelectExemMasterList Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        // 보여질 View 페이지를 설정한다.
        mav.getModel().put("emp_id",request.getParameter("emp_id"));
        logger.info("getSelectExemMasterList emp_id : "+ request.getParameter("emp_id")); // print
        mav.setViewName("/votingExemList");
        
        return mav;
    }

    // select
    @RequestMapping(value = "/voting/votingQuestionList", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getSelectQuestionList(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/voting/votingQuestionList getSelectQuestionList Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        
        ModelAndView mav = new ModelAndView();
        // 보여질 View 페이지를 설정한다.
        mav.getModel().put("exem_id",request.getParameter("exem_id"));
        mav.getModel().put("emp_id",request.getParameter("emp_id"));
        mav.setViewName("/votingQuestionList");
        
        
        logger.info("getSelectQuestionList exem_id : "+ request.getParameter("exem_id")); // print
        
        return mav;
    }

    // select
    @RequestMapping(value = "/voting/votingItemform", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getSelectItemForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/voting/votingItemform getSelectItemForm Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        // 보여질 View 페이지를 설정한다.
        mav.getModel().put("question_id",request.getParameter("question_id"));
        mav.getModel().put("question_nm",request.getParameter("question_nm"));
        mav.getModel().put("exem_id",request.getParameter("exem_id"));
        
        mav.getModel().put("emp_id",request.getParameter("emp_id"));
        mav.setViewName("/votingItemform");
        logger.info("getSelectItemForm question_id : "+ request.getParameter("question_id")); // print
        
        return mav;
    }

    // select
    @RequestMapping(value = "/voting/votingQuestionHistoryUpdate", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView setVotingQuestionHistoryUpdate(HttpSession session, HttpServletRequest request, HttpServletResponse response, VotingVO vo) throws Exception {
        logger.info("/voting/votingQuestionHistoryUpdate setVotingQuestionHistoryUpdate Call"); // print
        // 페이지 뷰 및 객체를 전달하기 위한 ModelAndView 생성
        ModelAndView mav = new ModelAndView();
        // 보여질 View 페이지를 설정한다.
        vo.setExem_id(request.getParameter("exem_id"));
        vo.setQuestion_id(request.getParameter("question_id"));
        vo.setEmp_id(request.getParameter("emp_id"));
        vo.setAnswer(request.getParameter("answer"));
        
        votingSvc.insertQuestionHistroy(vo);
        
        mav.getModel().put("question_id",request.getParameter("question_id"));
        mav.getModel().put("emp_id",request.getParameter("emp_id"));
        mav.getModel().put("exem_id",request.getParameter("exem_id"));
        mav.setViewName("/votingQuestionList");
        
        return mav;
    }

    @RequestMapping(value = "/voting/votingUserExemSelect", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody void selectUserExemList(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		VotingVO vo){        
        try {
            
            logger.info("/voting/votingUserExemSelect selectUserExemList Call"); 
            logger.info("selectUserExemList votingVO.getExem_id()  = [" + vo.getExem_id() + "]");
         
            List<VotingVO> selectList = votingSvc.selectUserExemList(vo);
            
            ObjectMapper mapper = new ObjectMapper();
            
            Map<String, Object> modelMap = new HashMap<String, Object>();
            
            logger.info("selectUserExemList selectList.size()  = [" + selectList.size() + "]");
            modelMap.put("total", selectList.size());
            modelMap.put("rows", selectList);
            
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(), modelMap);
            
            logger.debug("After response");
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }        
    }

    @RequestMapping(value = "/voting/votingUserQuestionSelect", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody void selectUserQuestionList(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		VotingVO vo){        
        try {
            
            logger.info("/voting/votingUserQuestionSelect selectUserQuestionList Call"); 
            logger.info("selectUserQuestionList votingVO.getExem_id()  = [" + vo.getExem_id() + "]");


            List<VotingVO> selectList = votingSvc.selectUserQuestionList(vo);
            
            ObjectMapper mapper = new ObjectMapper();
            
            Map<String, Object> modelMap = new HashMap<String, Object>();
            
            modelMap.put("total", selectList.size());
            modelMap.put("rows", selectList);
            
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(), modelMap);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }        
    }

    @RequestMapping(value = "/voting/votingUserItemSelect", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody void selectUserItemList(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		VotingVO vo){        
        try {
            
            logger.info("/voting/votingUserItemSelect selectUserItemList Call"); 
            logger.info("selectUserItemList votingVO.getQuestion_id()  = [" + vo.getQuestion_id() + "]");


            List<VotingVO> selectList = votingSvc.selectUserItemList(vo);
            
            ObjectMapper mapper = new ObjectMapper();
            
            Map<String, Object> modelMap = new HashMap<String, Object>();
            
            modelMap.put("total", selectList.size());
            modelMap.put("rows", selectList);
            
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(), modelMap);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 500 error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(e.getCause());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }        
    }
    
}

