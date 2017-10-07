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
<h1><img src="<c:url value="/styles/img/icons/smiley-sad.png"/>" /> 警告！！！</h1>
<div class="bloc">
<div class="title">用户未授权</div>
    <div class="content">
    	<div class="notif warning">
		   <strong>未授权 :</strong> 你所属的用户未授予相应的功能权限，请换其他用户试试，更多信息请咨询管理员. 
		   <a href="#" class="close"></a>
		</div>
	</div>        
</div> 
<!-- CONTENT  END--> 
</div>
</body>
</html>