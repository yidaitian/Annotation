/**
 * @(#)Page.java Copyright 2017-2018 carson. All rights reserved.
 */
package org.intellifai.annotation.web.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 类信息描述
 * 
 * @author Yunzhou.Wang
 * @date 2017-9-8 上午11:29:30
 * @version V2.0
 */
public class Page<T> {

	private int pageSize;  

    private int resultTotalSize;  

    private List<T> resultList = new ArrayList<T>();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getResultTotalSize() {
		return resultTotalSize;
	}

	public void setResultTotalSize(int resultTotalSize) {
		this.resultTotalSize = resultTotalSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<T> list) {
		this.resultList = (List<T>) list;
	}  
	
	public static int getCurrentPageNoForPopWindow(HttpServletRequest request){
		String pageNo = request.getParameter("p");
		int page= 1;
		if( pageNo!=null ){
			page = Integer.parseInt(pageNo);;
		}
		return page >0 ? page : 1;
	}
	
	public static int getCurrentPage(HttpServletRequest request){
      int page=0;
        Enumeration<?> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = (String)paramNames.nextElement();
            if (name != null && name.startsWith("d-") && name.endsWith("-p")) {
                String pageValue = request.getParameter(name);
                if (pageValue != null) {
                    page = Integer.parseInt(pageValue)-1;
                }else{
                	page = 1;
                }
            }
        }
        return page;
	}
}

