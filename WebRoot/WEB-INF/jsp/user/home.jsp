<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>标注系统管理页面</title>
<c:set var="checkAll" scope="session">
    <input type="checkbox" name="allbox" onclick="rapidsh.checkAllBox(this.form)" style="margin: 0 0 0 4px" />
</c:set>
<%
String ctx =  request.getContextPath();
%>
<!-- jQuery AND jQueryUI -->
<script type="text/javascript" src="<%=ctx %>/scripts/libs/jquery/1.6/jquery.min.js"/></script>
<script type="text/javascript" src="<%=ctx %>/scripts/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
<link rel="stylesheet" href="<%=ctx %>/styles/content/settings/style.css" />
<link rel="stylesheet" href="<%=ctx %>/styles/css/min.css" />
<script type="text/javascript" src="<%=ctx %>/scripts/min.js"></script>
<script type="text/javascript" src="<%=ctx %>/styles/content/settings/main.js"></script>

<link rel="stylesheet" href="<%=ctx %>/styles/displaytag/css/screen.css" type="text/css"/>
<link rel="stylesheet" href="<%=ctx %>/styles/common.css" type="text/css"/>
<script type="text/javascript" src="<%=ctx %>/scripts/common.js" ></script>
<script type="text/javascript" src="<%=ctx %>/scripts/ready.js" ></script>
</head>
<body>
<div id="head">
    <div class="left">
        <a href="<c:url value="/s/user/changePwd"/>" class="button profile"><img src="<c:url value="/styles/img/icons/top/huser.png"/>" /></a>
        Hi,
        <a href="#">${currentUser.realName}</a>医生
        |
        <a href="<c:url value="/s/logout"/>" class="logout">注销</a>
    </div>
    <div class="right">
        <form action="#" id="search" class="search placeholder">
            <label>Looking for something ?</label>
            <input type="text" value="" name="q" class="text" />
            <input type="submit" value="rechercher" class="submit" />
        </form>
    </div>
</div>
                
<div id="sidebar">
    <ul>
        <li>
            <a href="#">
                <img src="<c:url value="/styles/img/icons/menu/inbox.png"/>" />
               	 主面板
            </a>
        </li>
        <shiro:hasPermission name="system:-">
        <li class="current"><a href="#"><img src="<c:url value="/styles/img/icons/menu/layout.png"/>"/>系统管理</a>
            <ul>
                <li><a href="<c:url value="/s/user/manageUsers"/>" target="contentFrame">用户管理</a></li>
                <li><a href="<c:url value="/s/role/manageRoles"/>" target="contentFrame">角色管理</a></li>
                <li><a href="<c:url value="/s/org/manageOrgs"/>" target="contentFrame">机构管理</a></li>
                
            </ul>
        </li>
        <li class=""><a href="#"><img src="<c:url value="/styles/img/icons/menu/layout.png"/>"/>任务管理</a>
            <ul>
                <li><a href="<c:url value="/s/assignment/manageAssign"/>" target="contentFrame">分配工作</a></li>
                <li><a href="<c:url value="/s/assignment/manageCancel"/>" target="contentFrame">撤销任务</a></li>
                
            </ul>
        </li>
        <li class=""><a href="#"><img src="<c:url value="/styles/img/icons/menu/layout.png"/>"/>标注</a>
            <ul>
                <li><a href="<c:url value="/s/annotation/annotation"/>" target="contentFrame">前去标注</a></li>
                
            </ul>
        </li>
       </shiro:hasPermission> 
        
    </ul>
</div>

<iframe src="" name="contentFrame" id="contentFrameId" width="100%" frameborder="no" border="0" scrolling="no" allowtransparency="yes"></iframe>
        
</body>
</html>