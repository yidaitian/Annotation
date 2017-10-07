<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>  
<!-- <html ng-app="myApp"> -->
<html lang="en">
<head>  
    <meta charset="utf-8">
    <!-- <meta name="viewport" content="width = device-width, initial-sacle=1"> -->
    <!-- <script data-require="angular.js@1.3.0" data-semver="1.3.0" src="https://code.angularjs.org/1.3.0/angular.js"></script> -->
    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <!-- <link data-require="bootstrap-css@3.3.7" data-semver="3.3.7" rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" /> -->
    <!-- <script data-require="bootstrap@3.3.7" data-semver="3.3.7" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
    <title>登录页面</title>  
  
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/test/login.css"/>"/>  
    <script type="text/javascript" src="<c:url value="/static/test/login.js"/>"></script>  
</head>  
  
<body>  
<div id="login_frame">  
  
    <p id="image_logo">插入公司logo</p>  
  
    <form:form modelAttribute="loginCommand">
    <form method="post" action="login.js">  
  
        <p><label class="label_input">用户名</label><form:input path="username"/></p>  
        <p><label class="label_input">密码</label><form:password path="password"/></p>  
  
        <div id="login_control">  
        	<!-- 
            <input type="button" id="btn_login" value="登录" onclick="login();"/>  
             -->
             <input type="submit" value="登 录"/>
            <a id="forget_pwd" href="forget_pwd.html">忘记密码？</a>  
        </div>  
    </form>  
    </form:form>
</div>  
  
</body>  
</html>  