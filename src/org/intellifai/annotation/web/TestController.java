/**
 * @(#)TestController.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.intellifai.annotation.command.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-30 下午2:00:48
 * @version V2.0
 */
@Controller
public class TestController {

	@RequestMapping(value="system/home/waterfullPic",method= RequestMethod.GET)
    public String showWaterfullPic(Model model, @ModelAttribute LoginCommand command) {
        return "system/home/waterfullPic";
    }
	
	@RequestMapping(value="system/home/toLookImage",method= RequestMethod.GET)
    public void showImage(@PathParam("id")int id,HttpServletRequest request,
    		HttpServletResponse response,Model model) {
		
		//HttpSession seesion = request.getSession();  
		
        byte[] data= getBytes("d:/newTest.jpg");  
        response.setContentType("img/jpeg");  
        response.setCharacterEncoding("utf-8");  
        try {  
              
            OutputStream outputStream=response.getOutputStream();  
            InputStream in=new ByteArrayInputStream(data);  
              
            int len=0;  
            byte[]buf=new byte[1024];  
            while((len=in.read(buf,0,1024))!=-1){  
                outputStream.write(buf, 0, len);  
            }  
            in.close();
            outputStream.flush();
            outputStream.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
        //return "system/home/waterfullPic";
    }
	
	/*@RequestMapping(value="system/home/waterfullPic2",method= RequestMethod.GET)
    public String showLoginForm(Model model, @ModelAttribute LoginCommand command) {
        return "system/home/waterfullPic";
    }*/
	@RequestMapping(value="system/home/toLookImage",method= RequestMethod.POST)
    public void showImagePost(@PathParam("id")int id,HttpServletRequest request,
    		HttpServletResponse response,Model model) {
		
		//HttpSession seesion = request.getSession();  
		
        byte[] data= getBytes("d:/newTest.jpg");  
        response.setContentType("img/jpeg");  
        response.setCharacterEncoding("utf-8");  
        try {  
              
            OutputStream outputStream=response.getOutputStream();  
            InputStream in=new ByteArrayInputStream(data);  
              
            int len=0;  
            byte[]buf=new byte[1024];  
            while((len=in.read(buf,0,1024))!=-1){  
                outputStream.write(buf, 0, len);  
            }  
            in.close();
            outputStream.flush();
            outputStream.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
        //return "system/home/waterfullPic";
    }
	
	/** 
     * 获得指定文件的byte数组 
     */  
    private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }  
}

