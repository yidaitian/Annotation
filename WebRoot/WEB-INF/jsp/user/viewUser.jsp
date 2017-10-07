<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>查看用户</title>
    <jsp:include page="/inc/head.jsp"></jsp:include>  
</head>
<body>
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/user.png"/>" /> 用户管理</h1>
<div class="bloc">
    <div class="title">查看用户</div>
    <div class="content">
                <div class="input">
		        <label for="name">用户名</label>
		        ${editUserCommand.account}
                </div>
                <div class="input">
		        <label for="name">真实姓名</label>
		        ${editUserCommand.realName}
                </div>
                <div class="input">
		        <label for="name">所属机构</label>
		        ${editUserCommand.belongOrg}
                </div>
                <!-- 
                <div class="input">
		        <label for="name">附件</label>
		        <c:forEach items="${AttachmentList}" var="attachment">
	            ${attachment.attachmentName}
	            <a href="<c:url value="/File/FileDownload"/>?path=${attachment.attachmentPath}" target="_blank">下载附件</a><hr/>
	            </c:forEach>
                </div>
                 -->
                <div>
		        <label for="name">用户所属角色</label>
		        <c:forEach items="${originalCommandList}" var="roleCommand">
					<span>${roleCommand.name }</span>
				</c:forEach>
                </div>
                <div style="clear: both;text-align: center;">
                <input type="button" class="button" onclick="document.location.href='<c:url value="/s/user/manageUsers"/>'" value="返 回"/>
                </div>
    </div>
</div>
</div>
</body>
</html>