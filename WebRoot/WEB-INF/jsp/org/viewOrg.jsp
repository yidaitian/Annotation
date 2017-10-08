<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>查看角色</title>
    <jsp:include page="/inc/head.jsp"></jsp:include>  
</head>
<body>
<div id="content" class="white">
<div class="bloc">
<div class="title">查看机构</div>
<div class="content">
<div id="box">
     <div class="content" id="contentId">
       <div class="input">
          <label for="name">机构名称</label>
          ${OrganizationCommand.name}
       </div>
       <div class="input medium">
          <label for="address">详细地址</label>
          ${OrganizationCommand.address}
       </div>
     </div>
     <div>
       <input type="button" class="button" onclick="document.location.href='<c:url value="/s/org/manageOrgs"/>'" value="返 回"/>
    </div>
</div>
<div style="height: 100px;"></div>
</div>
</div>
</div>
</body>
</html>
