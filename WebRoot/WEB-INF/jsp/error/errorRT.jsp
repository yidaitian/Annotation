<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<jsp:include page="/inc/head.jsp"></jsp:include>  
</head>
<body>
<!-- CONTENT  BEGIN--> 
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/smiley-sad.png"/>" /> 程序异常！！！</h1>
<div class="bloc">
<div class="title">程序运行异常</div>
    <div class="content">
    	<div class="notif error ">
		   <strong> 程序异常 :</strong>  具体异常信息，请查仔细看下面的异常信息. 
		   <a href="#" class="close"></a>
		</div>
		<div style="color: red;">
		<%Exception ex=(Exception)request.getAttribute("exception");%>
		<H2>Exception:</H2>
		<%ex.printStackTrace(new java.io.PrintWriter(out));%>
		</div>
	</div>        
</div> 
<!-- CONTENT  END--> 
</div>
</body>
</html>