package com.myblog.encrypt;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class AES128 {
	
	private static String KEY = "myblog";
	
	public static String encrypt(String s) throws Exception {
        String encrypted = null;
        
        try {
        	s = URLEncoder.encode(s, "UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
             
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            
            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(KEY.getBytes("UTF-8")));
             
            return new String(Base64.encodeBase64(cipher.doFinal(s.getBytes("UTF-8"))),"UTF8").replace("+","%2B");
        } catch (Exception e) {
            throw e;
        }
    }
     
    public static String decrypt(String s) throws Exception {
        String decrypted = null;
        
        try {        	
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
             
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(KEY.getBytes("UTF-8")));
                        
            return s = URLDecoder.decode(new String(cipher.doFinal(Base64.decodeBase64(s.replace("%2B","+").getBytes("UTF-8")))), "UTF-8");
        } catch (Exception e) {
            throw e;
        }
    }
     
    public static byte[] hexToByteArray(String s) {
        byte[] retValue = null;
        if (s != null && s.length() != 0) {
            retValue = new byte[s.length() / 2];
            for (int i = 0; i < retValue.length; i++) {
                retValue[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
            }
        }
        return retValue;
    }
     
    public static String byteArrayToHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
         
        return strbuf.toString();
    }
}
