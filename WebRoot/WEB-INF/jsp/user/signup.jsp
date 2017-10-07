<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>新增用户</title>
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
				confirm("没有选择相应的角色哦，你确定提交吗");
			} else {
				$("#roleValueId").val(selVals);
				document.forms[0].submit();
			}
		});
	});
</script>
</head>
<body>
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/user.png"/>" /> 用户管理</h1>
<div class="bloc">
    <div class="title">新增用户</div>
    	<div class="content">
 				<form:form modelAttribute="signupCommand">
                	<form:errors path="*" element="div" cssClass="errors"/>
                	<div class="input">
		        		<label for="name">用户名</label>
		        		<form:input path="account"/>
                	</div>
                	<div class="input">
		        		<label for="name">真实姓名</label>
		        		<form:input path="realName"/>
                	</div>
                	<div class="input">
		        		<label for="name">密 码</label>
		        		<form:password path="password"/>
                	</div>
                	<div class="input">
    					<label for="name">所属机构</label>
    					<form:select path="belongOrg" items="${organizationCommandList}" itemLabel="name"
    						itemValue="id"></form:select>
    				</div>
    				<!-- 
                	<div class="input">
		        		<label for="name">附件</label>
		        		<input type="hidden" id="filepathId" name="filepath" readonly="readonly"/><input type="button" onclick="rapidsh.popUpload('<c:url value="/s/fileupload/upload"/>')" value="上传附件"/>
                	</div>
                	-->
                	<div>
		        		<label for="name">选择用户所属角色</label>
		        		<input type="hidden" id="roleValueId" name="roleId"/>
		            	<div class="left2right2left">
							<div class="select_side">
								<p>可选角色列表</p>
								<select id="selectL" name="selectL" multiple="multiple">
					    			<c:forEach items="${roleCommandList}" var="roleCommand">
										<option value="${roleCommand.id}" title="${roleCommand.description}">${roleCommand.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="select_opt">
								<p id="toright" title="添加">&gt;</p>
								<p id="toleft" title="移除">&lt;</p>
							</div>
							<div class="select_side">
								<p>已选角色列表</p>
								<select id="selectR" name="selectR" multiple="multiple">
								</select>
							</div>
				    	</div>
                	</div>
                	<div style="clear: both;text-align: center;">
                		<input type="button" value="提 交" class="button" id="sub"/>&nbsp;&nbsp;
                		<input type="button" class="button" onclick="document.location.href='<c:url value="/s/user/manageUsers"/>'" value="取 消"/>
                	</div>
            	</form:form>
    	</div>
	</div>
</div>
</body>
</html>
