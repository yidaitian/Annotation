<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>新增机构</title>
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
			document.forms[0].submit();
			/* if (selVals == "") {
				confirm("没有选择相应的角色哦，你确定提交吗");
			} else {
				$("#roleValueId").val(selVals);
				document.forms[0].submit();
			} */
		});
	});
</script>
</head>

<body>
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/users.png"/>" /> 机构管理</h1>
<div class="bloc">
    <div class="title">新增机构</div>
    	<div class="content">
 				<form:form modelAttribute="OrganizationCommand">
                	<form:errors path="*" element="div" cssClass="errors"/>
                	<div class="input">
		        		<label for="name">机构名称</label>
		        		<form:input path="name"/>
                	</div>
                	<div class="input">
		        		<label for="name">详细地址</label>
		        		<form:input path="address"/>
                	</div>
                	<div style="clear: both;text-align: center;">
                		<input type="button" value="提 交" class="button" id="sub"/>&nbsp;&nbsp;
                		<input type="button" class="button" onclick="document.location.href='<c:url value="/s/org/manageOrgs"/>'" value="取 消"/>
                	</div>
            	</form:form>
    	</div>
	</div>
</div>
</body>
</html>