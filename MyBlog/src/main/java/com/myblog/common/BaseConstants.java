package com.myblog.common;

public class BaseConstants 
{
	
	private BaseConstants()	{}
	
	public static boolean isReal = false;
	
	public static final String UPLOAD_PATH_LOCAL = "/Users/cgkang/workspace_sts/MyBlog/src/main/webapp/upload_file";
	public static final String UPLOAD_PATH_REAL = "/home/MyBlog/upload_file";
	
	public static final String IMG_PATH = "/upload_file/";
	
	public static final String SUCCESS_CODE = "00";
	public static final String FAIL_CODE = "99";
	
	public static final String SUCCESS_MSG = "정상처리 하였습니다.";
	public static final String FAIL_MSG = "시스템 오류 발생";

	public static final int PAGE_BLOCK = 5;
	public static final int PAGE_SIZE = 5;
	
	public static final int M_PAGE_SIZE = 15;
	
	public static final boolean AES256_USE = false;
	public static final String AES256_KEY = "aes256-blog-key!!";
	

}
