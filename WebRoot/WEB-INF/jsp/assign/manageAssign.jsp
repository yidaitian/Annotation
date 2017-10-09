<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<jsp:include page="/inc/head.jsp"></jsp:include>
<style type="text/css">
.left2right2left {
	width: 850px !important;
}
.select_side {
	float: left;
	width: 300px
}
select {
	width: 280px;
	height: 120px
}
.select_opt {
	float: left;
	width: 40px;
	height: 100%;
	margin-top: 36px
}
.select_opt p {
	width: 32px;
	height: 32px;
	margin-top: 15px;
	cursor: pointer;
	text-indent: -999em;
}
.select_opt p#toright {
	background: url('<c:url value="/styles/img/icons/arrow-right.png"/>') no-repeat;
}
.select_opt p#toleft {
	background: url('<c:url value="/styles/img/icons/arrow-left.png"/>') no-repeat;
}
</style>    
<script type="text/javascript">
	$(function() {
		var leftSel = $("#selectL");
		var rightSel = $("#selectR");
		$("#toright").bind("click", function() {
			leftSel.find("option:selected").each(function() {
				$(this).remove().appendTo(rightSel);
			});
		});
		$("#toleft").bind("click", function() {
			rightSel.find("option:selected").each(function() {
				$(this).remove().appendTo(leftSel);
			});
		});
		leftSel.dblclick(function() {
			$(this).find("option:selected").each(function() {
				$(this).remove().appendTo(rightSel);
			});
		});
		rightSel.dblclick(function() {
			$(this).find("option:selected").each(function() {
				$(this).remove().appendTo(leftSel);
			});
		});
		$("#sub").click(function() {
			var selVal = [];
			rightSel.find("option").each(function() {
				selVal.push(this.value);
			});
			selVals = selVal.join(",");
			//selVals = rightSel.val();
			if (selVals == "") {
				confirm("没有选择指定的医生，你确定提交吗");
			} else {
				$("#roleValueId").val(selVals);
				document.forms[0].submit();
			}
		});
	});
</script>
</head>
<body>
<!-- CONTENT  BEGIN--> 
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/users.png"/>" /> 用户管理</h1>
<c:if test="${Notification!=null}">
<div class="notif ${Notification.classType}">
   <strong>${Notification.title} :</strong> ${Notification.message}.
   <a href="#" class="close"></a>
</div>
</c:if>
<div class="bloc">
<div class="title">用户列表</div>
    <div class="content">
		<div class="pageActionBar" id="pageActionBarId">
			<div class="fl">
			<img src="<c:url value="/styles/images/fold.png"/>" title="打开搜索栏" id="openAdvanceSearchImgId" border="0" style="cursor: pointer;" /> 
			</div>
			<%-- <div class="fr" id="actionBtnId">
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
				<a href="<c:url value="/s/study/manageStudy"/>" class="white reloadButton">刷新</a>
			</div> --%>
<div class="clear"></div>
			<div id="pageBarSearchDivId" style="display:none;">
			<form action="<c:url value="/s/assign/searchStudy"/>" method="post">
				<div class="fl">
				UID：<input type="text" name="StudyInstanceUID"/>
				</div>
				<div class="fl">
				性别：<input type="text" name="patientSex"/>
				</div>
				<div class="fr submit">
				<input type="submit" value="搜索" class="button"/>
				</div>
			</form>
			</div>
		</div>
<%-- <div class="clear"></div>
		<form name="studyListFormId" action="" id="studyListFormId">
		  <display:table name="Page.resultList" htmlId="userManageListId" requestURI="/s/study/manageStudy" id="Study" pagesize="${Page.pageSize}" partialList="true" size="Page.resultTotalSize" export="false">
		    <display:column style="width: 5%" title="${checkAll}">
		    	<input type="checkbox" name="studyTID" value="<c:out value="${Study.id}"/>"/>
		  	</display:column>
		    <display:column property="StudyInstanceUID" title="ID" sortable="true" headerClass="sortable" />
		    <display:column property="account" title="登录名" sortable="true" headerClass="sortable" />
		    <display:column property="realName" title="真实姓名" sortable="true" headerClass="sortable" autolink="true"/>
		    <display:column property="belongOrg.name" title="所属机构" sortable="true" headerClass="sortable" />
		    <display:column title="所属角色">
			  <c:forEach items="${User.roles}" var="role">
				 <span><c:out value="${role.name}"/></span>
			   </c:forEach>
		    </display:column>
		  </display:table>
		</form>
	</div>        
</div>  --%>
<!-- CONTENT  END--> 
</div>
</body>
</html>