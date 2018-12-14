/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myblog.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @Project		:  
 * @Program		: StringUtil.java
 * @Description	: 프로그램 설명
 * @See    
 * <pre>
 * =================================================================================
 *   DATE      NAME            DESCRIPTION
 * ---------------------------------------------------------------------------------
 * </pre>
 */
public class StringUtil {

	private final static char WHITE_SPACE = ' ';

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isNull
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: null체크
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isNull(String str) {
    	
    	String retStr = str;
    	
        if (str != null) {
        	retStr = str.trim();
        }

        return (retStr == null || "".equals(retStr));
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isAlpha
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자체크
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isAlpha(String str) {

        if (str == null) {
            return false;
        }

        int size = str.length();

        if (size == 0)
            return false;

        for (int i = 0; i < size; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isAlphaNumeric
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: 문자 또는 숫자인지 체크
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isAlphaNumeric(String str) {

        if (str == null) {
            return false;
        }

        int size = str.length();

        if (size == 0)
            return false;

        for (int i = 0; i < size; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: integer2string
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: 숫자를 문자로 변환
    * </PRE>
    *   @return String
    *   @param integer
    *   @return
    ********************************************************************************/
    public static String integer2string(int integer) {
        return ("" + integer);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: long2string
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: long형 숫자를 문자로 변경
    * </PRE>
    *   @return String
    *   @param longdata
    *   @return
    ********************************************************************************/
    public static String long2string(long longdata) {
        return String.valueOf(longdata);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: float2string
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: float형 숫자를 문자로 변경
    * </PRE>
    *   @return String
    *   @param floatdata
    *   @return
    ********************************************************************************/
    public static String float2string(float floatdata) {
        return String.valueOf(floatdata);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: double2string
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: double형 숫자를 문자로 변경
    * </PRE>
    *   @return String
    *   @param doubledata
    *   @return
    ********************************************************************************/
    public static String double2string(double doubledata) {
        return String.valueOf(doubledata);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: null2void
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   		: null을 ''으로 변환
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String null2void(String str) {

    	String retStr = str;
    	
        if (isNull(retStr)) {
        	retStr = "";
        }

        return retStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2integer
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 integer형 숫자로 변환
    * </PRE>
    *   @return int
    *   @param str
    *   @return
    ********************************************************************************/
    public static int string2integer(String str) {

        if (isNull(str)) {
            return 0;
        }

        return Integer.parseInt(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2float
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 float형 숫자로 변환
    * </PRE>
    *   @return float
    *   @param str
    *   @return
    ********************************************************************************/
    public static float string2float(String str) {

        if (isNull(str)) {
            return 0.0F;
        }

        return Float.parseFloat(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2double
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 double형 숫자로 변환
    * </PRE>
    *   @return double
    *   @param str
    *   @return
    ********************************************************************************/
    public static double string2double(String str) {

        if (isNull(str)) {
            return 0.0D;
        }

        return Double.parseDouble(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2long
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 long형 숫자로 변환
    * </PRE>
    *   @return long
    *   @param str
    *   @return
    ********************************************************************************/
    public static long string2long(String str) {

        if (isNull(str)) {
            return 0L;
        }

        return Long.parseLong(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: null2string
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: null인 경우 지정한 값으로 리턴
    * </PRE>
    *   @return String
    *   @param str
    *   @param defaultValue 지정값
    *   @return
    ********************************************************************************/
    public static String null2string(String str, String defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return str;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2integer
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 integer형 숫자로 변환, null인 경우 지정한 값으로 리턴
    * </PRE>
    *   @return int
    *   @param str
    *   @param defaultValue 지정값
    *   @return
    ********************************************************************************/
    public static int string2integer(String str, int defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Integer.parseInt(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2float
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 float형 숫자로 변환, null인 경우 지정한 값으로 리턴
    * </PRE>
    *   @return float
    *   @param str
    *   @param defaultValue 지정값
    *   @return
    ********************************************************************************/
    public static float string2float(String str, float defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Float.parseFloat(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2double
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 double형 숫자로 변환, null인 경우 지정한 값으로 리턴
    * </PRE>
    *   @return double
    *   @param str
    *   @param defaultValue 지정값
    *   @return
    ********************************************************************************/
    public static double string2double(String str, double defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Double.parseDouble(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: string2long
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열을 long형 숫자로 변환, null인 경우 지정한 값으로 리턴
    * </PRE>
    *   @return long
    *   @param str
    *   @param defaultValue 지정값
    *   @return
    ********************************************************************************/
    public static long string2long(String str, long defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Long.parseLong(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: equals
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 값 비교
    * </PRE>
    *   @return boolean
    *   @param source
    *   @param target
    *   @return
    ********************************************************************************/
    public static boolean equals(String source, String target) {

        return null2void(source).equals(null2void(target));

    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: toSubString
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: SubString
    * </PRE>
    *   @return String
    *   @param str
    *   @param beginIndex
    *   @param endIndex
    *   @return
    ********************************************************************************/
    public static String toSubString(String str, int beginIndex, int endIndex) {

        if (equals(str, "")) {
            return str;
        } else if (str.length() < beginIndex) {
            return "";
        } else if (str.length() < endIndex) {
            return str.substring(beginIndex);
        } else {
            return str.substring(beginIndex, endIndex);
        }

    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: toSubString
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: SubString
    * </PRE>
    *   @return String
    *   @param source
    *   @param beginIndex
    *   @return
    ********************************************************************************/
    public static String toSubString(String source, int beginIndex) {

        if (equals(source, "")) {
            return source;
        } else if (source.length() < beginIndex) {
            return "";
        } else {
            return source.substring(beginIndex);
        }

    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: search
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열에서 해당문자 존재 갯수 리턴
    * </PRE>
    *   @return int
    *   @param source 
    *   @param target
    *   @return
    ********************************************************************************/
    public static int search(String source, String target) {
        int result = 0;
        String strCheck = source;
        for (int i = 0; i < source.length();) {
            int loc = strCheck.indexOf(target);
            if (loc == -1) {
                break;
            } else {
                result++;
                i = loc + target.length();
                strCheck = strCheck.substring(i);
            }
        }
        return result;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: trim
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: trim
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String trim(String str) {
        return str.trim();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: ltrim
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: ltrim
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String ltrim(String str) {

    	String retStr = str;
    	
        int index = 0;

        while (' ' == retStr.charAt(index++))
            ;

        if (index > 0)
        	retStr = retStr.substring(index - 1);

        return retStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: rtrim
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: rtrim
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String rtrim(String str) {

    	String retStr = str;
    	
        int index = retStr.length();

        while (' ' == retStr.charAt(--index))
            ;

        if (index < retStr.length())
        	retStr = retStr.substring(0, index + 1);

        return retStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: concat
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: concat
    * </PRE>
    *   @return String
    *   @param str1
    *   @param str2
    *   @return
    ********************************************************************************/
    public static String concat(String str1, String str2) {

        StringBuffer sb = new StringBuffer(str1);
        sb.append(str2);

        return sb.toString();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: lPad
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 왼쪽에 해당 길이만큼 문자를 채운다
    * </PRE>
    *   @return String
    *   @param str
    *   @param len
    *   @param pad
    *   @return
    ********************************************************************************/
    public static String lPad(String str, int len, char pad) {
        return lPad(str, len, pad, false);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: lPad
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: lPad
    * </PRE>
    *   @return String
    *   @param str
    *   @param len
    *   @param pad
    *   @param isTrim
    *   @return
    ********************************************************************************/
    public static String lPad(String str, int len, char pad, boolean isTrim) {

    	String retStr = str;
    	
        if (isNull(retStr)) {
            return null;
        }

        if (isTrim) {
        	retStr = retStr.trim();
        }

        for (int i = retStr.length(); i < len; i++) {
        	retStr = pad + retStr;
        }

        return retStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: rPad
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 오른쪽에 해당 길이만큼 문자를 채운다
    * </PRE>
    *   @return String
    *   @param str
    *   @param len
    *   @param pad
    *   @return
    ********************************************************************************/
    public static String rPad(String str, int len, char pad) {
        return rPad(str, len, pad, false);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: rPad
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: rPad
    * </PRE>
    *   @return String
    *   @param str
    *   @param len
    *   @param pad
    *   @param isTrim
    *   @return
    ********************************************************************************/
    public static String rPad(String str, int len, char pad, boolean isTrim) {

    	String retStr = str;
    	
        if (isNull(retStr)) {
            return null;
        }

        if (isTrim) {
        	retStr = retStr.trim();
        }

        for (int i = retStr.length(); i < len; i++) {
        	retStr = retStr + pad;
        }

        return retStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignLeft
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열의 뒷쪽에 지정한 길이만큼 공백으로 채움
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @return
    ********************************************************************************/
    public static String alignLeft(String str, int length) {
        return alignLeft(str, length, false);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignLeft
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: alignLeft
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @param isEllipsis
    *   @return
    ********************************************************************************/
    public static String alignLeft(String str, int length, boolean isEllipsis) {

        if (str.length() <= length) {

            StringBuffer temp = new StringBuffer(str);
            for (int i = 0; i < (length - str.length()); i++) {
                temp.append(WHITE_SPACE);
            }
            return temp.toString();
        } else {
            if (isEllipsis) {

                StringBuffer temp = new StringBuffer(length);
                temp.append(str.substring(0, length - 3));
                temp.append("...");

                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignRight
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열의 오른쪽에 지정한 길이만큼 공백으로 채움
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @return
    ********************************************************************************/
    public static String alignRight(String str, int length) {

        return alignRight(str, length, false);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignRight
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: alignRight
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @param isEllipsis
    *   @return
    ********************************************************************************/
    public static String alignRight(String str, int length, boolean isEllipsis) {

        if (str.length() <= length) {

            StringBuffer temp = new StringBuffer(length);
            for (int i = 0; i < (length - str.length()); i++) {
                temp.append(WHITE_SPACE);
            }
            temp.append(str);
            return temp.toString();
        } else {
            if (isEllipsis) {

                StringBuffer temp = new StringBuffer(length);
                temp.append(str.substring(0, length - 3));
                temp.append("...");
                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignCenter
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열이 중앙에 위치하도록 왼쪽, 오른쪽에 공백추가
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @return
    ********************************************************************************/
    public static String alignCenter(String str, int length) {
        return alignCenter(str, length, false);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: alignCenter
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: alignCenter
    * </PRE>
    *   @return String
    *   @param str
    *   @param length
    *   @param isEllipsis
    *   @return
    ********************************************************************************/
    public static String alignCenter(String str, int length, boolean isEllipsis) {
        if (str.length() <= length) {

            StringBuffer temp = new StringBuffer(length);
            int leftMargin = (int) (length - str.length()) / 2;

            int rightMargin;
            if ((leftMargin * 2) == (length - str.length())) {
                rightMargin = leftMargin;
            } else {
                rightMargin = leftMargin + 1;
            }

            for (int i = 0; i < leftMargin; i++) {
                temp.append(WHITE_SPACE);
            }

            temp.append(str);

            for (int i = 0; i < rightMargin; i++) {
                temp.append(WHITE_SPACE);
            }

            return temp.toString();
        } else {
            if (isEllipsis) {

                StringBuffer temp = new StringBuffer(length);
                temp.append(str.substring(0, length - 3));
                temp.append("...");
                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }

    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: capitalize
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 대문자 표시
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String capitalize(String str) {
        return !isNull(str) ? str.substring(0, 1).toUpperCase()
            + str.substring(1).toLowerCase() : str;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isPatternMatch
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 패턴을 비교한다
    * </PRE>
    *   @return boolean
    *   @param str
    *   @param pattern
    *   @return
    *   @throws Exception
    ********************************************************************************/
    public static boolean isPatternMatch(String str, String pattern)
            throws Exception {
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        return matcher.matches();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: toEng
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 영문으로 변경
    * </PRE>
    *   @return String
    *   @param kor
    *   @return
    *   @throws UnsupportedEncodingException
    ********************************************************************************/
    public static String toEng(String kor) throws UnsupportedEncodingException {

        if (isNull(kor)) {
            return null;
        }

        return new String(kor.getBytes("KSC5601"), "8859_1");

    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: toKor
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 한글로 변경
    * </PRE>
    *   @return String
    *   @param en
    *   @return
    *   @throws UnsupportedEncodingException
    ********************************************************************************/
    public static String toKor(String en) throws UnsupportedEncodingException {

        if (isNull(en)) {
            return null;
        }

        return new String(en.getBytes("8859_1"), "euc-kr");
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: countOf
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 문자열에서 해당문자 존재 갯수 반환
    * </PRE>
    *   @return int
    *   @param str
    *   @param charToFind
    *   @return
    ********************************************************************************/
    public static int countOf(String str, String charToFind) {
        int findLength = charToFind.length();
        int count = 0;

        for (int idx = str.indexOf(charToFind); idx >= 0; idx =
            str.indexOf(charToFind, idx + findLength)) {
            count++;
        }

        return count;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: encodePassword
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: 비번 encode
    * </PRE>
    *   @return String
    *   @param password
    *   @param algorithm
    *   @return
    ********************************************************************************/
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the
            // provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your
        // data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if (((int) encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

      /********************************************************************************
    * <PRE>
    * 1. MethodName 	: swapFirstLetterCase
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: convert first letter to a big letter or a small letter
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String swapFirstLetterCase(String str) {
        StringBuffer sbuf = new StringBuffer(str);
        sbuf.deleteCharAt(0);
        if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
            sbuf.insert(0, str.substring(0, 1).toUpperCase());
        } else {
            sbuf.insert(0, str.substring(0, 1).toLowerCase());
        }
        return sbuf.toString();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: trim
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: If original String has a specific String, remove specific Strings from original String.
    * </PRE>
    *   @return String
    *   @param origString
    *   @param trimString
    *   @return
    ********************************************************************************/
    public static String trim(String origString, String trimString) {
        int startPosit = origString.indexOf(trimString);
        if (startPosit != -1) {
            int endPosit = trimString.length() + startPosit;
            return origString.substring(0, startPosit)
                + origString.substring(endPosit);
        }
        return origString;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: getLastString
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Break a string into specific tokens and return a String of last location.
    * </PRE>
    *   @return String
    *   @param origStr
    *   @param strToken
    *   @return
    ********************************************************************************/
    public static String getLastString(String origStr, String strToken) {
        StringTokenizer str = new StringTokenizer(origStr, strToken);
        String lastStr = "";
        while (str.hasMoreTokens()) {
            lastStr = str.nextToken();
        }
        return lastStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: getStringArray
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: If original String has token, Break a string into specific tokens and change String Array. If not, return a String Array which has original String as it is.
    * </PRE>
    *   @return String[]
    *   @param str
    *   @param strToken
    *   @return
    ********************************************************************************/
    public static String[] getStringArray(String str, String strToken) {
        if (str.indexOf(strToken) != -1) {
            StringTokenizer st = new StringTokenizer(str, strToken);
            String[] stringArray = new String[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++) {
                stringArray[i] = st.nextToken();
            }
            return stringArray;
        }
        return new String[] {str };
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isNotEmpty
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: If string is null or empty string, return false. If not, return true.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isEmpty
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: If string is null or empty string, return true. If not, return false.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: replace
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: replace replaced string to specific string from original string.
    * </PRE>
    *   @return String
    *   @param str
    *   @param replacedStr
    *   @param replaceStr
    *   @return
    ********************************************************************************/
    public static String replace(String str, String replacedStr,
            String replaceStr) {
        
    	String newStr = "";
        if (str.indexOf(replacedStr) != -1) {
            String s1 = str.substring(0, str.indexOf(replacedStr));
            String s2 = str.substring(str.indexOf(replacedStr) + 1);
            newStr = s1 + replaceStr + s2;
        }
        return newStr;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isPatternMatching
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: It returns true if str matches the pattern string. It performs regular expression pattern matching.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @param pattern
    *   @return
    *   @throws Exception
    ********************************************************************************/
    public static boolean isPatternMatching(String str, String pattern)
            throws Exception {
        // if url has wild key, i.e. "*", convert it to
        // ".*" so that we can
        // perform regex matching

    	String patternStr = pattern;
    	
        if (patternStr.indexOf('*') >= 0) {
        	patternStr = patternStr.replaceAll("\\*", ".*");
        }

        patternStr = "^" + patternStr + "$";

        return Pattern.matches(patternStr, str);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: containsMaxSequence
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: It returns true if string contains a sequence of the same character.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @param maxSeqNumber
    *   @return
    ********************************************************************************/
    public static boolean containsMaxSequence(String str, String maxSeqNumber) {
        int occurence = 1;
        int max = string2integer(maxSeqNumber);
        if (str == null) {
            return false;
        }

        int sz = str.length();
        for (int i = 0; i < (sz - 1); i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                occurence++;

                if (occurence == max)
                    return true;
            } else {
                occurence = 1;
            }
        }
        return false;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: containsInvalidChars
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Checks that the String contains certain characters.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @param invalidChars
    *   @return
    ********************************************************************************/
    public static boolean containsInvalidChars(String str, char[] invalidChars) {
        if (str == null || invalidChars == null) {
            return false;
        }
        int strSize = str.length();
        int validSize = invalidChars.length;
        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < validSize; j++) {
                if (invalidChars[j] == ch) {
                    return true;
                }
            }
        }
        return false;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: containsInvalidChars
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Checks that the String contains certain characters.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @param invalidChars
    *   @return
    ********************************************************************************/
    public static boolean containsInvalidChars(String str, String invalidChars) {
        if (str == null || invalidChars == null) {
            return true;
        }
        return containsInvalidChars(str, invalidChars.toCharArray());
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isNumeric
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Checks if the String contains only unicode digits. A decimal point is not a unicode digit and returns false.
    * </PRE>
    *   @return boolean
    *   @param str
    *   @return
    ********************************************************************************/
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        if (sz == 0)
            return false;
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: reverse
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Reverses a String as per
    * </PRE>
    *   @return String
    *   @param str
    *   @return
    ********************************************************************************/
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuffer(str).reverse().toString();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: fillString
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Make a new String that filled original to a special char as cipers
    * </PRE>
    *   @return String
    *   @param originalStr
    *   @param ch
    *   @param cipers
    *   @return
    ********************************************************************************/
    public static String fillString(String originalStr, char ch, int cipers) {
        int originalStrLength = originalStr.length();

        if (cipers < originalStrLength)
            return null;

        int difference = cipers - originalStrLength;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < difference; i++)
            strBuf.append(ch);

        strBuf.append(originalStr);
        return strBuf.toString();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: isEmptyTrimmed
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Determine whether a (trimmed) string is empty
    * </PRE>
    *   @return boolean
    *   @param foo
    *   @return
    ********************************************************************************/
    public static final boolean isEmptyTrimmed(String foo) {
        return (foo == null || foo.trim().length() == 0);
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: getTokens
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Return token list
    * </PRE>
    *   @return List
    *   @param lst
    *   @param separator
    *   @return
    ********************************************************************************/
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getTokens(String lst, String separator) {
        List tokens = new ArrayList();

        if (lst != null) {
            StringTokenizer st = new StringTokenizer(lst, separator);
            while (st.hasMoreTokens()) {
                try {
                    String en = st.nextToken().trim();
                    tokens.add(en);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return tokens;
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: getTokens
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Return token list which is separated by ","
    * </PRE>
    *   @return List
    *   @param lst
    *   @return
    ********************************************************************************/
    @SuppressWarnings("rawtypes")
	public static List getTokens(String lst) {
        return getTokens(lst, ",");
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: convertToCamelCase
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: This method convert "string_util" to "stringUtil"
    * </PRE>
    *   @return String
    *   @param targetString
    *   @param posChar
    *   @return
    ********************************************************************************/
    public static String convertToCamelCase(String targetString, char posChar) {
        StringBuffer result = new StringBuffer();
        boolean nextUpper = false;
        String allLower = targetString.toLowerCase();

        for (int i = 0; i < allLower.length(); i++) {
            char currentChar = allLower.charAt(i);
            if (currentChar == posChar) {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    currentChar = Character.toUpperCase(currentChar);
                    nextUpper = false;
                }
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: convertToCamelCase
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Convert a string that may contain underscores to camel case.
    * </PRE>
    *   @return String
    *   @param underScore
    *   @return
    ********************************************************************************/
    public static String convertToCamelCase(String underScore) {
        return convertToCamelCase(underScore, '_');
    }

    /********************************************************************************
    * <PRE>
    * 1. MethodName 	: convertToUnderScore
    * 2. ClassName  	: EzStringUtil
    * 3. Comment   	: Convert a camel case string to underscore representation.
    * </PRE>
    *   @return String
    *   @param camelCase
    *   @return
    ********************************************************************************/
    public static String convertToUnderScore(String camelCase) {
        String result = "";
        for (int i = 0; i < camelCase.length(); i++) {
            char currentChar = camelCase.charAt(i);
            // This is starting at 1 so the result does
            // not end up with an
            // underscore at the begin of the value
            if (i > 0 && Character.isUpperCase(currentChar)) {
                result = result.concat("_");
            }
            result =
                result.concat(Character.toString(currentChar).toLowerCase());
        }
        return result;
    }
    
    /**
     * <PRE>
    * 1. MethodName 	: getComma
    * 2. ClassName  	: 
    * 3. Comment   	: String에 comma를 삽입한다. 
    *                 isTruncated가 false이면 소수점 이하를 자르지 않는다.
    * </PRE>
    *   @return String
    *   @param String str
    *   @param boolean isTruncated
     */
    public static String getComma(String str, boolean isTruncated)
    {
        DecimalFormat commaFormat; // comma 삽입을 위한 변수
        boolean bMinus = false;

        if (str==null)
            return "";
        else if (str.trim().equals(""))
            return "";
        else if (str.trim().equals("&nbsp;"))
            return "&nbsp;";
        else {
        		// str에 -가 있으면 음수로 처리한다.
            int pos = str.indexOf("-");
          	if (pos!=-1) {	// 발견됨
          		bMinus = true;
          		str = str.replace('-',' ');
          	}
        		
            //str에 .이 있으면 Float으로 처리한다.
            pos = str.indexOf(".");
            if (pos!=-1) {
                if (!isTruncated) {
                    commaFormat = new DecimalFormat("#,##0.00");
                    if (bMinus) return commaFormat.format(Double.parseDouble("-"+str.trim()));
                    else				return commaFormat.format(Double.parseDouble(str.trim()));
                }
                else {
                    commaFormat = new DecimalFormat("#,##0");
                    if (bMinus) return commaFormat.format(Long.parseLong("-"+str.trim().substring(0,pos)));
                    else				return commaFormat.format(Long.parseLong(str.trim().substring(0,pos)));
                }
            }
            else {
                commaFormat = new DecimalFormat("#,##0");
                if (bMinus) return commaFormat.format(Long.parseLong("-"+str.trim()));
                else				return commaFormat.format(Long.parseLong(str.trim()));
            }
        }
    }
    /**
     * <pre>
     * text를 format에 맞추어 출력한다.
     * getFormatedText("0193372412","???-???-????") --->> 019-337-2412로 출력
     * </pre>
     *
     * @param String text
     * @param String format
     *
     * @return String
     */
    public static String getFormatedText(String text,String format)
    {
        String rtn;
        int start,i,j,len;
        int tCount,fCount;

        tCount = text.length();
        fCount = format.length();

        rtn = "";

        if (text.equals("")) return rtn;
        if (text.equals("&nbsp;")) return "&nbsp;";
        // text가 01252412 이고 format 이 ????-???? 이면 0125-2412로 출력
        //text에서 -를 제거한다.
        for (i=0; i<tCount; ++i) {
            if (!text.substring(i,i+1).equals("-"))
                rtn = rtn + text.substring(i,i+1);
        }

        text = rtn;
        tCount = text.length();

        //포멧에서 ?의  count
        len = 0;
        for (j=0; j<fCount; ++j) {
            if (format.substring(j,j+1).equals("?")) ++len;
        }
        //text의 길이가 len보다 작으면 앞에 0를 붙인다.
        if (tCount<len) {
            for (i=0; i<(len-tCount); ++i) {
                text = '0' + text;
            }
            tCount = len;
        }

        rtn = "";
        start = 0;
        for (i=0; i<tCount; ++i) {
            for (j=start; j<fCount; ++j) {
                if (format.substring(j,j+1).equals("?")) {
                    rtn = rtn + text.substring(i,i+1);
                    start = start + 1;
                    break;
                }
                else {
                    rtn = rtn + format.substring(j,j+1);
                    start = start + 1;
                }
            }
        }
        return rtn+format.substring(start);
    }
    
    
    /********************************************************************************
     * <PRE>
     * 1. MethodName 	: cardMasking
     * 2. ClassName  	: EzStringUtil
     * 3. Comment   	: 문자 또는 숫자인지 체크
     * </PRE>
     *   @return str
     *   @param str
     *   @return
     ********************************************************************************/
     public static String getCardMasking(String str) {
    	 String strReturn = str;
    	 int size = strReturn.length();

         if (str == null) return strReturn;

         if (size < 8) return strReturn;
    	 //Card 번호 앞 4자리 노출 
         strReturn = str.substring(0,4); 

         //카드번호 X 처리
         for(int i = 0; i < size-8; i++) strReturn +="X"; 
         //Card 번호 뒷 4자리 노출 
         strReturn += str.substring(size-4);
         
         return strReturn;
     }
     
     
     /********************************************************************************
      * <PRE>
      * 1. MethodName 	: getSegment
      * 2. ClassName  	: EzStringUtil
      * 3. Comment   	: PUS/ICN,ICN/LHR -> PUS/ICN/LHR 
      *                   PUS/ICN ICN/LHR -> PUS/ICN/LHR
      *                   PUS/ICN PUS/LHR -> PUS/ICN/PUS/LHR
      * </PRE>  
      *   @return str
      *   @param str
      *   @return
      ********************************************************************************/
      public static String getSegmentPattern(String str, String pattern){
     	 String strReturn = "";
     	 String element = "";
     	 String endSeg = "";
     	 int size = str.length();

     	StringTokenizer strToken  = new StringTokenizer(str, pattern); 
     	while(strToken.hasMoreTokens ()){
     		element = strToken.nextToken();
     		element = element.trim();
     		if (element.length() > 3 && endSeg.length() > 2){
	     		//1. 앞에 읽은 값의 end seg 가 이번에 읽은 start seg 와 동일한지 비교 
				//2. 동일하면 출발 seg 를 삭제, 동일하지 않으면 둘다 살려둘것  
     			element = element.replace(endSeg, "");
     		}
			endSeg =  element.substring(element.length() -3 , element.length());
			//구간을 모두 연결 
			if (strReturn.length() == 0)  strReturn = element;
			else strReturn += "/"+ element;
     	}
          
     	strReturn = strReturn.replace("//", "/");
        return strReturn;
      }
      
      
      /********************************************************************************
       * <PRE>
       * 1. MethodName 	: isEmailPattern
       * 2. ClassName  	: EzStringUtil
       * 3. Comment   	: e-mail 형식에 맞으면 true 를  retrun
       * </PRE>
       *   @return str
       *   @param str
       *   @return
       ********************************************************************************/
       public static boolean isEmailPattern(String str) {
    	   String tempStr = str.trim();
    	   boolean blReturn = false;
    	   try {
    		   return isPatternMatch(tempStr, "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
    	   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	   }
    	   return blReturn;
      }
       
       /********************************************************************************
        * <PRE>
        * 1. MethodName 	: isTelNoPattern
        * 2. ClassName  	: EzStringUtil
        * 3. Comment   	: TelNo 형식에 맞으면 true 를  retrun
        * </PRE>
        *   @return str
        *   @param str
        *   @return
        ********************************************************************************/
        public static boolean isTelNoPattern(String str) {
     	  String tempStr = str.trim();
     	  tempStr = tempStr.replaceAll("-", "");
     	  boolean blReturn = false;
     	  try {
     		   return isPatternMatch(tempStr, "^[0-9]*$");
     	  } catch (Exception e) {
 			// TODO Auto-generated catch block
     		  e.printStackTrace();
     	  }
     	  return blReturn;
       }

        /********************************************************************************
         * <PRE>
         * 1. MethodName 	: isTelNoPattern
         * 2. ClassName  	: EzStringUtil
         * 3. Comment   	: 기본 문자열의 길이가 규격화된 길이가 될때까지 특정문자를 추가한다. 
         * </PRE>
         *   @return str
         *   @param str
         *   @return
         ********************************************************************************/
	    public static String charAdd(String oriValue,int tLength,String addChar, boolean fillLeft) throws Exception{
	    	if(oriValue==null)	oriValue	= "";
	    	if(addChar==null || "".equals(addChar))	addChar		= " ";
	    	
	    	int ori_byte_len	= oriValue.getBytes().length;
	    	
	    	if(ori_byte_len>tLength)	throw new Exception("["+oriValue + "] 가 제한길이 "+ tLength +" 보다 큽니다.");

	    	String addStr	= "";
	    	for(int i=ori_byte_len;i<tLength;i++){
	    		addStr	= addStr.concat(addChar);
	    	}
	    	
	    	if(fillLeft)	oriValue	= oriValue.concat(addStr);
	    	else			oriValue	= addStr.concat(oriValue);

	    	return oriValue;
	    }
	    
	    /********************************************************************************
         * <PRE>
         * 1. MethodName 	: getExStr
         * 2. ClassName  	: StringUtil
         * 3. Comment   	: 예외 trace배열값을 문자열로 반환. 
         * </PRE>
         *   @return str
         *   @param str
         *   @return
         ********************************************************************************/
	    public static String getExStr(Throwable e) {
	    	if ( e == null ) return "";
	    	
	    	try {
	    		StackTraceElement[] arr = e.getStackTrace();
	    		StringBuffer sb = new StringBuffer();
	    		sb.append(e.getMessage()+"\n");
	    		for ( int i = 0 ; i < arr.length ; i++ ) {
	    			sb.append(arr[i]+"\n");
	    		}
	    		if ( sb.length() > 0 ) {
	    			return sb.toString();
	    		} else {
	    			return "";
	    		}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return "";
		}
}
