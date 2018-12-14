package com.myblog.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

public class PostFile
{

	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		String file_name = "test.pdf";
		String domain = "http://10.10.0.2:8080/fileupload.do";
		
		File file = new File("/Users/cgkang/Downloads/videoSample/2.mp4");
		
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("someFile", new FileBody(file));
		reqEntity.addPart("fileName", new StringBody(file_name));
		
		HttpPost httppost = new HttpPost(domain);
	    httppost.setEntity(reqEntity);
	    
		HttpClient httpclient = new DefaultHttpClient();

	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    if (resEntity != null)
	    {
	    	System.out.println(EntityUtils.toString(resEntity));
	    	resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
	 }

}