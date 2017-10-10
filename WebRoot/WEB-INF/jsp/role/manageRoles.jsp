<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<jsp:include page="/inc/head.jsp"></jsp:include>  
</head>
<body>
<!-- CONTENT  BEGIN--> 
<div id="content" class="white">
<h1 style="color:white"><img src="<c:url value="/styles/img/icons/users.png"/>" /> 角色管理</h1>
<c:if test="${Notification!=null}">
<div class="notif ${Notification.classType}" >
   <strong>${Notification.title} :</strong> ${Notification.message}. 
   <a href="#" class="close"></a>
</div>
</c:if>
<div class="bloc">
<div class="title">角色列表</div>
    <div class="content">
		<div class="pageActionBar" id="pageActionBarId">
			<div class="fl">
			<img src="<c:url value="/styles/images/fold.png"/>" title="打开搜索栏" id="openAdvanceSearchImgId" border="0" style="cursor: pointer;" /> 
			</div>
			<div class="fr" id="actionBtnId">
			<shiro:hasPermission name="role:add">
            <a href="<c:url value="/s/role/showAddRoleForm"/>" class="white addButton">增加</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="role:delete">
            <a href="#" class="white delButton" onclick="rapidsh.actionPerformance('roleListFormId','<c:url value="/s/role/deleteRole"/>','del');">删除</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="role:edit">
            <a href="#" class="white editButton" onclick="rapidsh.actionPerformance('roleListFormId','<c:url value="/s/role/editRole"/>','edit');">修改</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="role:view">
            <a href="#" class="white viewButton" onclick="rapidsh.actionPerformance('roleListFormId','<c:url value="/s/role/viewRole"/>','view');">查看</a>
            </shiro:hasPermission>
				每页显示
				<input type="text" title="最多每页可显示200条" id="pageSizeId" size="3" maxlength="3" onchange="rapidsh.setCookies('rapidsh_cookie_page_size',this.value,100)"/>
				条记录
				<a href="<c:url value="/s/role/manageRoles"/>" class="white reloadButton">刷新</a>
			</div>
<div class="clear"></div>
			<div id="pageBarSearchDivId" style="display:none;">
			<form action="<c:url value="/s/role/searchRoles"/>" method="post">
				<div class="fl">
				角色名称：<input type="text" name="name"/>
				</div>
				<div class="fl">
				角色描述：<input type="text" name="description"/>
				</div>
				<div class="fr submit">
				<input type="submit" value="搜索" class="button"/>
				</div>
			</form>
			</div>
		</div>
<div class="clear"></div>
		<form name="roleListFormId" action="" id="roleListFormId">
		  <display:table name="Page.resultList" htmlId="userManageListId" requestURI="/s/role/manageRoles" id="Role" pagesize="${Page.pageSize}" partialList="true" size="Page.resultTotalSize" export="false">
		    <display:column style="width: 5%" title="${checkAll}">
		    <input type="checkbox" name="roleId" value="<c:out value="${Role.id}"/>"/>
		  </display:column>
		    <display:column property="id" title="ID" sortable="true" headerClass="sortable" />
		    <display:column property="name" title="角色名称" sortable="true" headerClass="sortable" />
		    <display:column property="description" title="角色描述" sortable="true" headerClass="sortable" />
		  </display:table>
		</form>
	</div>        
</div> 
<!-- CONTENT  END--> 
</div>
</body>
</html>