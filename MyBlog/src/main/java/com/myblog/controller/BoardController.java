package com.myblog.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myblog.common.BaseConstants;
import com.myblog.service.BoardService;
import com.myblog.util.CommonUtil;
import com.myblog.util.DateUtil;
 
@Controller
public class BoardController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BoardService boardService;
     
    @RequestMapping(value = "/boardList.do" , method = {RequestMethod.GET, RequestMethod.POST})
    public String boardList(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	log.info(">>>>>>>> boardList");
    	
    	
    	
    	
    	
    	//모바일 접속 여부 체크
    	request.getSession(true).setAttribute("isMobile",CommonUtil.isMobile(request));
    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
    	
    	String category = (reqHashMap.get("category")==null?"1":reqHashMap.get("category").toString());
    	reqHashMap.put("category",category);
    	
    	int pageNum 	= (int) (reqHashMap.get("pageNum")==null?1:Integer.parseInt(reqHashMap.get("pageNum").toString()));
    	int pageSize 	= BaseConstants.PAGE_SIZE;
    	int pageBlock 	= BaseConstants.PAGE_BLOCK;
    	
    	int totalCount = boardService.getBoardListTotalCount(reqHashMap);  //전체등록 카운트
        int totalPage = (totalCount + (pageSize - 1)) / pageSize; // 마지막 페이지
        
        if (totalPage==0) totalPage = 1;
        
        int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1; // 시작 페이지 (페이징 네비 기준)
        int endPage = startPage + pageBlock - 1; 		// 끝 페이지 (페이징 네비 기준)
        if (endPage > totalPage)	// [마지막 페이지 (페이징 네비 기준) > 마지막 페이지] 보다 큰 경우 
        { 
            endPage = totalPage;
        }

        int startNo = pageNum==1?0:(pageNum-1) * pageSize;
    	int endNo = pageSize;
    	
    	log.debug("startPage>>>"+ startPage);
    	log.debug("endPage>>>"+ endPage);
    	log.debug("startNo >>>"+ startNo);
    	log.debug("endNo >>>"+ endNo);
    	log.debug("category >>>"+ category);
    	
    	reqHashMap.put("startNo",startNo);
    	reqHashMap.put("endNo",endNo);
    	
    	reqHashMap.put("category",category);

    	model.addAttribute("pageNum", pageNum);
    	model.addAttribute("totalCount", totalCount);
    	model.addAttribute("pageBlock", pageBlock);
    	model.addAttribute("totalPage", totalPage);
    	model.addAttribute("startPage", startPage);
    	model.addAttribute("endPage", endPage);
    	model.addAttribute("category", category);
    	
    	model.addAttribute("categoryList", boardService.getCategoryList(reqHashMap));
    	model.addAttribute("boardList", boardService.getBoardList(reqHashMap));
    	model.addAttribute("boardRecentList", boardService.getBoardRecentList(reqHashMap));
        
        return "board/list";
    }
    
    @RequestMapping(value = "/boardWrite.do")
    public String boardWrite(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	log.info(">>>>>>>> boardWrite.do");
    	
    	String r = "board/write";
    	
    	String userid = request.getSession(false).getAttribute("USERID")==null?"":(String)request.getSession(false).getAttribute("USERID");
    	String grade = request.getSession(false).getAttribute("GRADE")==null?"":(String)request.getSession(false).getAttribute("GRADE");
    	
    	log.info("userid>>>"+ userid);
    	log.info("grade>>>"+ grade);
    	
    	if (grade.equals("9") && !userid.equals(""))
    	{
    		HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
    		model.addAttribute("boardRecentList", boardService.getBoardRecentList(reqHashMap));
    		model.addAttribute("categoryList", boardService.getCategoryList(reqHashMap));
    	}
    	else
    	{
    		model.addAttribute("message", "글쓰기 권한이 없습니다.");
    		model.addAttribute("url", "/");
    		r = "common/callBackUrl";
    	}

    	return r;
    }
    
    //@RequestMapping(value = "/boardView.do")
    @RequestMapping(value="/boardView.do" , method = {RequestMethod.GET, RequestMethod.POST})
    public String boardView(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	log.info(">>>>>>>> boardView.do");
    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
    	String category = (reqHashMap.get("category")==null?"":reqHashMap.get("category").toString());
    	int pageNum = (int) (reqHashMap.get("pageNum")==null?1:Integer.parseInt(reqHashMap.get("pageNum").toString()));
    	
    	log.info("category >>>>>>>> " + category);
    	log.info("pageNum >>>>>>>> " + pageNum);
    	
    	//조회수 증가
    	boardService.boardUpdateReadCnt(reqHashMap);
    	
    	model.addAttribute("category", category);
    	model.addAttribute("pageNum", pageNum);
    	model.addAttribute("categoryList", boardService.getCategoryList(reqHashMap));
    	model.addAttribute("boardView", boardService.getBoardView(reqHashMap));
    	model.addAttribute("boardRecentList", boardService.getBoardRecentList(reqHashMap));
    	
    	return "board/view";
    }
    
    @RequestMapping(value = "/boardDelete.do")
    public @ResponseBody Map<String, Object> boardDelete(
    		Model model,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	log.info(">>>>>>>> boardDelete.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	
    	String userid = request.getSession(false).getAttribute("USERID")==null?"":(String)request.getSession(false).getAttribute("USERID");
    	String grade = request.getSession(false).getAttribute("GRADE")==null?"":(String)request.getSession(false).getAttribute("GRADE");
    	
    	log.debug("userid >>>"+ userid);
    	log.debug("grade >>>"+ grade);
    	
    	if (grade.equals("9") && !userid.equals(""))
    	{
    		
    		try {
    			JSONObject jObj = new JSONObject(CommonUtil.requestToJsonString(request));
    			HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(jObj);
    			log.debug(">>>>>>>>> reqHashMap : " +  reqHashMap.toString());
    			
            	if(boardService.boardDelete(reqHashMap))
            	{
            		rt_code = "0000";
            		rt_msg = "데이터 삭제 처리 하였습니다.";
            	}
            	else
            	{
            		rt_code = "9999";
            		rt_msg = "데이터 삭제 실패하였습니다.";
            	}

    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			
        		rt_code = "9999";
        		rt_msg = "데이터 삭제 실패하였습니다.error: "+e.toString();
    		}
    	}
    	else
    	{
    		rt_code = "8888";
    		rt_msg = "삭제 권한이 없습니다.";
    	}
    		
    	Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    return jsonObject;
    }
    
    
    @RequestMapping(value = "/boardInsert.do")
    public @ResponseBody Map<String, Object> boardInsert(
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	log.info(">>>>>>>> boardInsert.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	
    	String filename = "";
    	String filepath = "";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		final Map<String, MultipartFile> files =  multipartRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		
		String Path = BaseConstants.isReal?BaseConstants.UPLOAD_PATH_REAL:BaseConstants.UPLOAD_PATH_LOCAL;
		Path += "/"+DateUtil.getDateFormat(System.currentTimeMillis(), "yyyy/MM/dd");
		
		try 
		{
			File dir = new File(Path); 
			if (!dir.exists()) 
			{
				if ( dir.mkdirs() == false ) throw new Exception();
			}
			
			try 
			{
				log.debug(">>> upload file count = [" + files.size()+"]");
				
				while (itr.hasNext()) 
				{
					Entry<String, MultipartFile> entry = itr.next();
					MultipartFile file = entry.getValue();
					if (!"".equals(file.getOriginalFilename())) 
					{
						filename = new String(file.getOriginalFilename().getBytes("8859_1"),"utf-8");
//							String filePath = Path + File.separator + file.getOriginalFilename();
						filepath = Path + File.separator + filename;
						log.debug(">>> file path : [" + filepath + "]");
						file.transferTo(new File(filepath));
					}
				}
				
				rt_code = "0000";
				rt_msg = "정상처리 하였습니다. ";
				
			}
			catch(Exception e) 
			{
				rt_code = "8888";
				rt_msg = "폴더 업로드에 실패하였습니다. : "+ e.toString();
			}
		}
		catch (Exception e) 
		{
			rt_code = "9999";
			rt_msg = "폴더 생성 실패 하였습니다. : "+ e.toString();
		}
		
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
    	if(reqHashMap.get("userid")==null || reqHashMap.get("userid").equals(""))
    	{
    		reqHashMap.put("userid", "master");
    	}
    	
    	if(!filepath.equals("")) reqHashMap.put("filename", filename);
    	
    	log.debug(">>>>>>>>> reqHashMap : " +  reqHashMap.toString());

    	if(boardService.boardInsert(reqHashMap))
    	{
    		rt_code = "0000";
    		rt_msg = "정상처리 하였습니다. ";
    	}
    	else
    	{
    		rt_code = "9999";
    		rt_msg ="데이터 저장하는데 실패하였습니다";
    	}
    	
    	Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    return jsonObject;
    }
    
}
