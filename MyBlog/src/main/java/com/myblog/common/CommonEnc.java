package com.myblog.common;

import com.myblog.encrypt.AES128;
import com.myblog.encrypt.AES256;
import com.myblog.encrypt.TripleDES;

public class CommonEnc {

	//타입 별 선언
	public static String TYPE_AES128 = "AES128";
	public static String TYPE_AES256 = "AES256";
	public static String TYPE_TRIPLEDES = "TRIPLEDES";
	
	/**
	 * 암호화 함수 
	 * @param enc_type (AES128, AES256, TRIPLEDES)
	 * @param data
	 * @return 암호화된 데이터
	 */
	public static String encrypt(String enc_type, String data){
		
		try{
			if(enc_type.equals("AES128")){
				data = AES128.encrypt(data);
			} else if(enc_type.equals("AES256")){
				data = AES256.Encrypt(data);
			} else if(enc_type.equals("TRIPLEDES")){
				data = TripleDES.Encrypt(data);
			} 
		} catch(Exception e){
			//System.out.println("ENC ERROR : "+enc_type);
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 복호화 함수 
	 * @param enc_type (AES128, AES256, TRIPLEDES)
	 * @param data 
	 * @return 복호화된 데이터
	 */
	public static String decrypt(String enc_type, String data){	
		try{
			if(enc_type.equals("AES128")){
				data = AES128.decrypt(data);
			} else if(enc_type.equals("AES256")){
				data = AES256.Decrypt(data);
			} else if(enc_type.equals("TRIPLEDES")){
				data = TripleDES.Decrypt(data);
			}
		} catch(Exception e){
			//System.out.println("DEC ERROR : "+enc_type);
			e.printStackTrace();
		}
		return data;
	}
}
