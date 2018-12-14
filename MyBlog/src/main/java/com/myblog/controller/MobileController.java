package com.myblog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
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

import com.myblog.common.AES256Util;
import com.myblog.common.BaseConstants;
import com.myblog.service.BoardService;
import com.myblog.util.CommonUtil;
 
@Controller
public class MobileController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/mBoardList.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json;charset=utf8")
    public @ResponseBody String mBoardList(
    		Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	log.info(">>>>>>>> mBoardList");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	 
    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
    	
    	String category = (reqHashMap.get("category")==null?"1":reqHashMap.get("category").toString());
    	reqHashMap.put("category",category);
    	
    	int pageNum 	= (int) (reqHashMap.get("pageNum")==null?1:Integer.parseInt(reqHashMap.get("pageNum").toString()));
    	int pageSize 	= BaseConstants.M_PAGE_SIZE;
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
    	
    	List<HashMap<String, Object>> categoryList = null;
    	categoryList = boardService.getCategoryList(reqHashMap);
    	
    	List<HashMap<String, Object>> boardList = null;
    	boardList = boardService.getBoardList(reqHashMap);
    	
    	JSONObject jsonObject = new JSONObject();
    	JSONArray cateArray = new JSONArray();
		JSONArray boardListArray = new JSONArray();
    	try
    	{
    		if(categoryList != null)
        	{
    			for(int i=0;i<categoryList.size();i++)
        		{
    				JSONObject sObject = new JSONObject();
    				sObject.put("seq",categoryList.get(i).get("seq"));
    				sObject.put("codename",categoryList.get(i).get("codename"));
    				sObject.put("sortnum",categoryList.get(i).get("sortnum"));
    				cateArray.put(sObject);
        		}
        	}
    		
    		if(boardList != null)
        	{
    			for(int i=0;i<boardList.size();i++)
        		{
    				JSONObject sObject = new JSONObject();
    				sObject.put("userid",boardList.get(i).get("userid"));
    				sObject.put("category",boardList.get(i).get("category"));
    				sObject.put("username",boardList.get(i).get("username"));
    				sObject.put("title",boardList.get(i).get("title"));
    				sObject.put("regdate",boardList.get(i).get("regdate"));
    				sObject.put("readcnt",boardList.get(i).get("readcnt"));
    				sObject.put("replycnt",boardList.get(i).get("replycnt"));
    				boardListArray.put(sObject);
        		}
        	}
    		
    		rt_code = "0000";
    		rt_msg = "정상처리되었습니다.";
    		
    	}
    	catch (Exception e)
    	{
    		rt_code = "9999";
    		rt_msg = "시스템 오류발생 : "+ e.toString();
    		e.printStackTrace();
    		log.error(e.toString());
    	}
    	
    	try 
    	{
    		jsonObject.put("totalPage", totalPage);
    		jsonObject.put("startPage", startPage);
    		jsonObject.put("endPage", endPage);
    		jsonObject.put("category", category);
    		jsonObject.put("categoryList", cateArray);
    		jsonObject.put("boardList", boardListArray);
    		jsonObject.put("rt_code", rt_code);
    	    jsonObject.put("rt_msg", rt_msg);
    	}
    	catch (JSONException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	log.debug("jsonObject >>> "+jsonObject.toString());
	    
    	String output = "";
    	 
    	if (BaseConstants.AES256_USE){
	    	try {
	    		String key = BaseConstants.AES256_KEY;       // key는 16자 이상
				output = AES256Util.aesEncode(key, jsonObject.toString());
			} catch (InvalidKeyException | UnsupportedEncodingException
					| NoSuchAlgorithmException | NoSuchPaddingException
					| InvalidAlgorithmParameterException
					| IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		output = jsonObject.toString();
    	}
		
//	    output = CommonEnc.encrypt("TRIPLEDES", jsonObject.toString());
	    
	    return output;
    }
    
    
    @RequestMapping(value="sendFcm.do")
    public @ResponseBody String sendFcm(
    		Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	JSONObject jsonObject = new JSONObject();
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	
    	String FCM_URL = "https://fcm.googleapis.com/fcm/send";
        String FCM_SERVER_API_KEY    = "AAAAyAd4PSE:APA91bHFn43ZNaM5f4AdkScgqyYPvEXQtequga-Q7Fl715XF9JCIurSWNVOCwryvgZ0J1wc11Ua4IcgSDWoOfD-aH2XanizOGuxelfhC2YT0z7KW1FWomtxRD9kdVNn3HV8ZErMlT-ou0gHK3i6cc2NRDL4mT9QJPA";

        String defaultDeviceId = "cBEG4tWnm0k:APA91bE4QUoZyTgD8GB_HKqBtlIPeIpj963eRbLU2x9mcNnLtjnafqiTTkNV-I_-uUVDtDFDG-BHIZRfFjoH11qCgHC_mqjwUGvLA05tpcUtXYqMnmnPX-tp0DVygZOQXjDBpxoLqOZFww4MAKmEyK3vNlQ0RsU_IA";
//      String defaultDeviceId = "";
        
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
        	
    	String deviceRegistrationId = (reqHashMap.get("deviceRegistrationId")==null?defaultDeviceId:reqHashMap.get("deviceRegistrationId").toString());
    	
    	System.out.println("deviceRegistrationId>>>" + deviceRegistrationId);
    	
        int responseCode = -1;
        String responseBody = null;
        try
        {
            System.out.println("Sending FCM request");
            byte[] postData = getPostData(deviceRegistrationId);
            
            URL url = new URL(FCM_URL);
            HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
 
            //set timeputs to 10 seconds
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
 
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
            httpURLConnection.setRequestProperty("Authorization", "key="+FCM_SERVER_API_KEY);
 
             
 
            OutputStream out = httpURLConnection.getOutputStream();
            out.write(postData);
            out.close();
            responseCode = httpURLConnection.getResponseCode();
            //success
            if (responseCode == HttpStatus.SC_OK)
            {
                responseBody = convertStreamToString(httpURLConnection.getInputStream());
                System.out.println("FCM message sent : " + responseBody);
                
                jsonObject.put("rt_code", "0000");
        	    jsonObject.put("rt_msg",  responseBody);
            }
            //failure
            else
            {
                responseBody = convertStreamToString(httpURLConnection.getErrorStream());
                System.out.println("Sending FCM request failed for regId: " + deviceRegistrationId + " response: " + responseBody);
                
                jsonObject.put("rt_code", "2222");
        	    jsonObject.put("rt_msg",  "Sending FCM request failed for regId: " + deviceRegistrationId + " response: " + responseBody);
            }
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception in sending FCM request. regId: " + deviceRegistrationId);
            try {
				jsonObject.put("rt_code", "3333");
				jsonObject.put("rt_msg", "IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            ioe.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("Unknown exception in sending FCM request. regId: " + deviceRegistrationId);
            try {
				jsonObject.put("rt_code", "4444");
				jsonObject.put("rt_msg", "IO Exception in sending FCM request. regId: " + deviceRegistrationId);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            e.printStackTrace();
        }
        
        
        String output = "";
        output = jsonObject.toString();
        
        return output;
    }




    public static byte[] getPostData(String registrationId) throws JSONException {
        HashMap<String, String> dataMap = new HashMap<>();
        JSONObject payloadObject = new JSONObject();
        
        // 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
        // String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";
      
        // 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다  위에 둘중에 한개 골라서 날려주자
        // String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\" 여기가 받을 사람 토큰  \"}";
 
        //dataMap.put("name", "Aniket!");
        //dataMap.put("country", "India");
        dataMap.put("title", "FCM 제목 TEST!!!!");
        dataMap.put("content", "안녕하세요. FCM 테스트 중입니다.push 잘 받아지셨나요? 가나다라마바사아자차카타가나다라마바사아자차카타가나다라마바사아자차카타");
         
        JSONObject data = new JSONObject(dataMap);;
        payloadObject.put("data", data);
        payloadObject.put("to", registrationId);
 
        return payloadObject.toString().getBytes();
    }
     
    public static String convertStreamToString (InputStream inStream) throws Exception
    {
        InputStreamReader inputStream = new InputStreamReader(inStream);
        BufferedReader bReader = new BufferedReader(inputStream);
 
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = bReader.readLine()) != null)
        {
            sb.append(line);
        }
 
        return sb.toString();
    }
        
    
        
}
