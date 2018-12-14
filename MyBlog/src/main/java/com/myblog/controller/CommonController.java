package com.myblog.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.myblog.common.BaseConstants;
import com.myblog.service.BoardService;
import com.myblog.util.CommonUtil;
import com.myblog.util.DateUtil;
 
@Controller
public class CommonController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    BoardService boardService;
    
    @RequestMapping(value = "/message.do")
    public String message(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	log.debug(">>> message.do");
    	
    	String message = request.getParameter("message");
    	String url = request.getParameter("url");
    	
    	model.addAttribute("message", message);
    	model.addAttribute("url", url);
    	
    	return "common/callBackUrl";
    	//return "redirect:/boardList.do";
    }
    
    @RequestMapping(value = "/qrSave.do")
    public @ResponseBody Map<String, Object> qrSave(
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	log.debug(">>> qrSave.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	String rt_qrImg = "";

    	try
    	{
    		JSONObject jObj = new JSONObject(CommonUtil.requestToJsonString(request));
			HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(jObj);
			log.debug(">>>>>>>>> reqHashMap : " +  reqHashMap.toString());
			
	    	String uuid = UUID.randomUUID().toString().toUpperCase();
	    	
	    	String qr_name = (String) reqHashMap.get("qr_name");
	    	String qr_company = (String) reqHashMap.get("qr_company");
	    	String qr_title = (String) reqHashMap.get("qr_title");
	    	String qr_url = (String) reqHashMap.get("qr_url");
	    	String qr_size = "M";
	    	
//	    	System.out.println("qr_name >>"+qr_name);
//	    	System.out.println("qr_company >>"+qr_company);
//	    	System.out.println("qr_title >>"+qr_title);
//	    	System.out.println("qr_url >>"+qr_url);
	    		    	
	    	Map<String, Object> jsonQRObject = new HashMap<String, Object>();
	    	jsonQRObject.put("qr_name", qr_name);
	    	jsonQRObject.put("qr_company", qr_company);
	    	jsonQRObject.put("qr_title", qr_title);
	    	jsonQRObject.put("qr_url", qr_url);
		    
		    log.debug("jsonQRObject >>> "+jsonQRObject.toString());
	    	
	    	String vcard = new String(jsonQRObject.toString().getBytes("UTF-8"), "ISO-8859-1");
	    	QRCodeWriter qrWriter = new QRCodeWriter();
	    	int height = 350;
	    	int width = 350;
	    	
	    	if("L".equals(qr_size)) {
	    		height = 350;
	    		width = 350;
	    	} else if("M".equals(qr_size)) {
	    		height = 230;
	    		width = 230;
	    	} else {
	    		height = 120;
	    		width = 120;
	    	}
	    	
	    	String YYYYMMDD = DateUtil.getDateFormat(System.currentTimeMillis(),"yyyy/MM/dd");
	    	String Path = BaseConstants.isReal?BaseConstants.UPLOAD_PATH_REAL:BaseConstants.UPLOAD_PATH_LOCAL;
	    	Path += "/QR/"+YYYYMMDD;
	    	
	    	File dir = new File(Path);
			if (!dir.exists())
			{
				if ( dir.mkdirs() == false ) throw new Exception();
			}

	    	FileOutputStream fos = new FileOutputStream(new File(Path + "/" + uuid + ".png"));
	    	
	    	BitMatrix bitMatrix = qrWriter.encode(vcard, BarcodeFormat.QR_CODE, width, height);
	    	MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
	    	fos.close();
	    	
			rt_qrImg = "/upload_file/QR/" + YYYYMMDD + "/" + uuid + ".png";
	    	rt_code = "0000";
    		rt_msg = "정상처리 하였습니다. ";
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		rt_code = "9999";
    		rt_msg = "QR 코드 생성에 실패하였습니다.";
    	}
    	
    	Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    jsonObject.put("rt_qrImg", rt_qrImg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
    	
    	
    	return jsonObject;
    }
    
    @RequestMapping(value = "/qrWrite.do")
    public String qrSave(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	log.debug(">>> qrWrite.do");
    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
		model.addAttribute("boardRecentList", boardService.getBoardRecentList(reqHashMap));
		model.addAttribute("categoryList", boardService.getCategoryList(reqHashMap));
    	
    	return "etc/qrWrite";
    }
    
    
    @RequestMapping(value="/download.do" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<InputStreamResource> download(@RequestParam("filename") String filename) throws IOException {
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	responseHeaders.set("Content-Disposition", "attachment;filename=\"" + filename + "\";");
    	responseHeaders.set("Content-Transfer-Encoding", "binary");
    	return new ResponseEntity<InputStreamResource>(getFileContent(filename), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value="/fileupload.do" , method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Map<String, Object> boardInsert(
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	System.out.println(">>>>>>>>>> fileuplodad.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	String filename = "";
    	String filepath = "";
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	//System.out.println("multipartRequest>>"+ multipartRequest.getParameter("folder_id"));
    	
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
					System.out.println("entry getKey :"+ entry.getKey());
					System.out.println("entry getValue :"+ entry.getValue());
					
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
		
    	Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    return jsonObject;
    }
    
    private InputStreamResource getFileContent(String filename) throws IOException
    {
    	
//    	String path = String.format("%s%s", fsResource.getPath(), filename);
//    	String prefix =  getServletContext().getRealPath("/");
//    	String path = new File(".").getCanonicalPath();
//      String webAppPath = getServletContext().getRealPath("/");
//      File directory = new File (".");
        
        File directory = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        System.out.println ("Current directory's canonical path: " +     directory.getCanonicalPath());
        System.out.println ("Current directory's absolute  path: " + directory.getAbsolutePath());
        String path = directory.getCanonicalPath();
        
    	FileSystemResource resource = new FileSystemResource(path);
    	return new InputStreamResource(resource.getInputStream());
    }
    
    @RequestMapping(value = "/chatView.do")
    public String chat(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	log.debug(">>> chatView.do");
    	return "/chat/chat";
    	//return "redirect:/boardList.do";
    }
    
    @RequestMapping(value = "/chatBoard.do")
    public String chatBoard(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);
		model.addAttribute("boardRecentList", boardService.getBoardRecentList(reqHashMap));
		model.addAttribute("categoryList", boardService.getCategoryList(reqHashMap));
		
    	log.debug(">>> chatBoard.do");
    	return "/chat/chatBoard";
    	//return "redirect:/boardList.do";
    }
    
    
    
	
    
}
