<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>智成标注系统</title>
<jsp:include page="/inc/head.jsp"></jsp:include>
<script type="text/javascript">
if(top!=this) {
   top.location=this.location;
}
</script>
</head>
<body>
<div id="content" class="login">
<h1><img src="<c:url value="/styles/img/icons/lock-closed.png"/>" />用户登录</h1>
	<form:form modelAttribute="loginCommand">
	<div><form:errors path="*" element="div" cssClass="notif tip close"/></div>
          <div class="input placeholder">
		      <label for="login">用户名</label>
		      <form:input path="username" value="admin"/>
		  </div>
		  <div class="input placeholder">
		      <label for="pass">密 码</label>
		      <form:password path="password" value="1"/>
	      </div>
          <div class="checkbox">
		      <form:checkbox path="rememberMe"/>
		      <label class="inline" for="remember">记住我</label>
		  </div>
          <div class="submit">
	      	<input type="submit" value="登 录"/>
		  </div>
   </form:form>
</div>         
</body>
</html>