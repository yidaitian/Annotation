/**
 * @(#)InterfaceTest.java Copyright 2017-2018 Magingunion, Inc. All rights reserved.
 */
package org.intellifai.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  



import com.alibaba.fastjson.JSON;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-7 下午1:41:50
 * @version V2.0
 */
public class InterfaceTest {

	private static final Logger logger = LoggerFactory.getLogger(InterfaceTest.class);
	
	/**
	 * 发送POST请求
	 *
	 * @param url         请求地址url
	 * @param params      需要发送的请求参数字符串
	 * @param connTimeout 连接超时时间（毫秒），如果为null则默认为180秒
	 * @param readTimeout 读取超时时间（毫秒），如果为null则默认为180秒
	 * @return 请求响应内容，如果为null则表示请求异常
	 */
	public static String doPost(String url, String params, String contentType)
	{
	    PrintWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    try
	    {
	        URL realUrl = new URL(url);
	        // 打开和URL之间的连接,根据url
	        URLConnection conn = realUrl.openConnection();
	        // 设置通用的请求属性
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        conn.setRequestProperty("Content-Type", contentType == null? "application/json" : contentType);
	        // 发送POST请求必须设置如下两行
	        conn.setDoOutput(true);
	        conn.setDoInput(true);

	        // 设置请求超时时间和读取超时时间
//	        conn.setConnectTimeout(connTimeout == null ? 180 : connTimeout);
//	        conn.setReadTimeout(readTimeout == null ? 180 : readTimeout);

	        // 获取URLConnection对象对应的输出流，设置utf-8编码
	        out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
	        // 发送请求参数
	        out.print(params);
	        // flush输出流的缓冲
	        out.flush();
	        // 定义BufferedReader输入流来读取URL的响应,设置utf-8编码
	        /*in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	        String line;
	        while ((line = in.readLine()) != null)
	            result += line;*/
	        InputStream instream = conn.getInputStream();
	        result = IOUtils.toString(instream, "UTF-8");   //  .toString(in, "UTF-8");
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        result = null;
	    }
	    //使用finally块来关闭输出流、输入流
	    finally
	    {
	        try
	        {
	            if (out != null)
	            {
	                out.close();
	            }
	            if (in != null)
	            {
	                in.close();
	            }
	        }
	        catch (IOException ex)
	        {
	            ex.printStackTrace();
	        }
	    }
	    return result;
	}
	
	public static void main(String[] args) {
		String urlPrefix = "http://192.168.20.149:8000";
		String url = urlPrefix + "/SendProcessImages";
		//List<NameValuePair> params = new ArrayList<NameValuePair>();
		SendProcessParam param = new SendProcessParam();
		param.setCaseUID("1.2.3.4.5.6.7.8.9");
		param.setStudyInstanceUID("1.2.840.111111");
		param.setFileType("dcm");
		param.setImagesPath("home/test");
		String jsonParam = JSON.toJSONString(param);
		
		String url2 = urlPrefix + "/GetProcessResult";
		GetResultParam param2 = new GetResultParam();
		param2.setCaseUID("1.2.3.4.5.6.7.8.9");
		String jsonParam2 = JSON.toJSONString(param);
		try {
			//System.out.print(getReturnData(url));
			System.out.print(doPost(url,jsonParam,"UTF-8"));
			//System.out.print(doPost(url2,jsonParam2,"UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("error");
			e.printStackTrace();
		}
	}
}

