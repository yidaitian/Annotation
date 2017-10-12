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
<h1 style="color:white"><img src="<c:url value="/styles/img/icons/users.png"/>" /> 机构管理</h1>
<c:if test="${Notification!=null}">
<div class="notif ${Notification.classType}" >
   <strong>${Notification.title} :</strong> ${Notification.message}. 
   <a href="#" class="close"></a>
</div>
</c:if>
<div class="bloc">
<div class="title">机构列表</div>
    <div class="content">
		<div class="pageActionBar" id="pageActionBarId">
			<div class="fl">
			<img src="<c:url value="/styles/images/fold.png"/>" title="打开搜索栏" id="openAdvanceSearchImgId" border="0" style="cursor: pointer;" /> 
			</div>
			<div class="fr" id="actionBtnId">
			<shiro:hasPermission name="org:add">
            <a href="<c:url value="/s/org/addOrg"/>" class="white addButton">增加</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="org:delete">
            <a href="#" class="white delButton" onclick="rapidsh.actionPerformance('orgListFormId','<c:url value="/s/org/deleteOrg"/>','del');">删除</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="org:edit">
            <a href="#" class="white editButton" onclick="rapidsh.actionPerformance('orgListFormId','<c:url value="/s/org/editOrg"/>','edit');">修改</a>
            </shiro:hasPermission>
			<shiro:hasPermission name="org:view">
            <a href="#" class="white viewButton" onclick="rapidsh.actionPerformance('orgListFormId','<c:url value="/s/org/viewOrg"/>','view');">查看</a>
            </shiro:hasPermission>
				每页显示
				<input type="text" title="最多每页可显示200条" id="pageSizeId" size="3" maxlength="3" onchange="rapidsh.setCookies('rapidsh_cookie_page_size',this.value,100)"/>
				条记录
				<a href="<c:url value="/s/org/manageOrgs"/>" class="white reloadButton">刷新</a>
			</div>
			<div class="clear"></div>
			<div id="pageBarSearchDivId" style="display:none;">
			<form action="<c:url value="/s/org/searchOrgs"/>" method="post">
				<div class="fl">
				机构名称：<input type="text" name="name"/>
				</div>
				<div class="fl">
				详细地址：<input type="text" name="address"/>
				</div>
				<div class="fr submit">
				<input type="submit" value="搜索" class="button"/>
				</div>
			</form>
			</div>
		</div>
<div class="clear"></div>
		<form name="orgListFormId" action="" id="orgListFormId">
		  <display:table name="Page.resultList" htmlId="userManageListId" requestURI="/s/org/manageOrgs" id="Organization" pagesize="${Page.pageSize}" partialList="true" size="Page.resultTotalSize" export="false">
		    <display:column style="width: 5%" title="${checkAll}">
		    <input type="checkbox" name="orgId" value="<c:out value="${Organization.id}"/>"/>
		  </display:column>
		    <display:column property="id" title="ID" sortable="true" headerClass="sortable" />
		    <display:column property="name" title="机构名称" sortable="true" headerClass="sortable" />
		    <display:column property="address" title="详细地址" sortable="true" headerClass="sortable" />
		  </display:table>
		</form>
	</div>        
</div> 
<!-- CONTENT  END--> 
</div>
</body>

<!-- <script type=text/javascript>
rapidsh.actionPerformance = function(formId, url, type) {
	
	if( type =="del" || type =="send"){
		 if( $("input:checked").length >= 1 ){
			 $('#'+formId+'').attr("action", url);
			 $('#'+formId+'').attr("method", "post");
			 $('#'+formId+'').submit();
		 }else{
			 alert("请至少勾选一条记录!!");
			 return;
		 }
	 }
	 if( type =="edit" || type =="view"){	
		 if( $("input:checked").length==1 ){
			 $('#'+formId+'').attr("action", url);
			 $('#'+formId+'').attr("method", "get");
			 $('#'+formId+'').submit();
		 }else{
			 //alert("请勾选一条记录!!");
			 if($("input:checked").length < 1)
				 alert("请勾选一条记录!!");
			 else
				 alert("[修改]/[查看]最多只能选中一条记录!!!");
			 return;
		 }
	 }
};
</script> -->

</html>