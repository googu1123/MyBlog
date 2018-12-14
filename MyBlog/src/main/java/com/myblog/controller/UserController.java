package com.myblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myblog.service.UserService;
import com.myblog.util.CommonUtil;
 
@Controller
public class UserController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    UserService userService;
    
    @RequestMapping(value="/login.do" , method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Map<String, Object> login(HttpServletRequest request, HttpServletResponse response)
    {
    	log.info(">>>>>>>> login.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	
		try {
			JSONObject jObj = new JSONObject(CommonUtil.requestToJsonString(request));
			HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(jObj);

			log.debug(">>>>>>>>> Login reqHashMap : " +  reqHashMap.toString());
				
			HashMap<String, Object> login_result = userService.userLogIn(reqHashMap);
			
			if(login_result != null)
			{
				if(login_result.get("pwd").equals((String)reqHashMap.get("pwd")))
				{
					rt_code = "0000";
					rt_msg = "로그인 성공";
					
					int loginUpdate = userService.lastLoginUpdate(reqHashMap);
					
					log.debug("update last login time " + Integer.toString(loginUpdate));
					
					request.getSession(false).setAttribute("USERID",login_result.get("userid"));
					request.getSession(false).setAttribute("USERNAME",login_result.get("username"));
					request.getSession(false).setAttribute("GRADE",login_result.get("grade"));
					
					log.debug("userid : " + login_result.get("userid"));
					log.debug("username : " + login_result.get("username"));
					log.debug("grade : " + login_result.get("grade"));
				}
				else
				{
					rt_code = "7777";
					rt_msg = "비밀번호가 올바르지 않습니다.";
				}
			}
			else
			{
				rt_code = "8888";
				rt_msg = "등록된 아이디가 아닙니다.";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			rt_code = "9999";
			rt_msg = "로그인에 실패하였습니다. error: "+e.toString();
		}

    	
    	Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
    	
	    return jsonObject;
    }
    
    @RequestMapping(value = "/logout.do")
    public String logout(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	request.getSession(false).setAttribute("USERID","");
    	request.getSession(false).setAttribute("USERNAME","");
    	request.getSession(false).setAttribute("GRADE","");
    	request.getSession(false).invalidate();
    	
    	model.addAttribute("message", "로그아웃 처리 하였습니다.");
    	model.addAttribute("url", "/");
    	
    	return "common/callBackUrl";
    	//return "redirect:/boardList.do";
    }
    
}
