<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>查看角色</title>
    <jsp:include page="/inc/head.jsp"></jsp:include>  
    <script type="text/javascript" src="<c:url value="/scripts/otree/common4otree.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/scripts/otree/otree.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/styles/otree/otree.css"/>"/>
</head>
<body>
<div id="content" class="white">
<div class="bloc">
<div class="title">查看角色</div>
<div class="content">
<div id="box">
     <div class="content" id="contentId">
       <div class="input">
          <label for="name">角色名称</label>
          ${roleCommand.name}
       </div>
       <div class="input medium">
          <label for="description">角色描述</label>
          ${roleCommand.description}
       </div>
       <div class="input textarea">
          <label for="textarea1">拥有权限</label>
       </div>
     </div>
     <div>
       <input type="button" class="button" onclick="document.location.href='<c:url value="/s/role/manageRoles"/>'" value="返 回"/>
    </div>
</div>
<div style="height: 100px;"></div>
</div>
</div>
</div>
<script type=text/javascript>
chinaAreas = ([
	{'pid':'-1','text':'权限树','id':'0','checked':0},
	${permissionTree}
]);

	var l0 = new Date().getTime();
	var otree = new OTree({
		panel 	: document.getElementById("contentId"),
		data	: chinaAreas,
		icoPath : '<%=request.getContextPath()%>/styles/otree/img/'
	});
	otree.paint();
	function fChecked(){
		document.getElementById("permissions").value=otree.getCheckeds('input');
		document.forms[0].submit();
	}
</script>
</body>
</html>
