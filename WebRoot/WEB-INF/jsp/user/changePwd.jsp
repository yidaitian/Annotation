<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>汇医融工远程会诊系统</title>
	<jsp:include page="/inc/head.jsp"></jsp:include>  
	<script type="text/javascript">
		if(top!=this) {
   			top.location=this.location;
		}
		
	</script>
  </head>
  
  <body>
    <div id="content" class="login">
    	<h1><img src="<c:url value="/styles/img/icons/lock-closed.png"/>" />修改密码</h1>
    	<form:form modelAttribute="changePwdCommand">
    		<div><form:errors path="*" element="div" cssClass="notif tip close"/></div>
		  	<div class="input placeholder">
		      <label for="pass">原密 码</label>
		      <form:password path="password"/>
	      	</div>
		  	<div class="input placeholder">
		      <label for="pass">新密 码</label>
		      <form:password path="newPassword"/>
	      	</div>
	      	<div class="input placeholder">
		      <label for="pass">确认密 码</label>
		      <form:password path="confirmPassword"/>
	      	</div>
	      	<div style="clear: both;text-align: center;">
	      		<input type="button" value="提 交" class="button" id="sub"/>&nbsp;&nbsp;
                <input type="button" class="button" onclick="document.location.href='<c:url value="/s/user/home"/>'" value="取 消"/>
		  </div>
    	</form:form>
    </div>
  </body>
</html>
<<script type="text/javascript">
	(function(){
			var sub = document.getElementById("sub");
			//初始化移入移出事件
			if(sub.addEventListener){
				sub.addEventListener("click", check);	
			}else if(sub.attachEvent){
				sub.attachEvent("onClick", check);
			}
		})();

		function check(){
			var password = document.getElementById("newPassword");
			var passwordConfirm = document.getElementById("confirmPassword");
			if(password.value != passwordConfirm.value)
				alert("对不起，您2次输入的密码不一致");
			else{
				document.forms[0].submit();
			}
		}
</script>
