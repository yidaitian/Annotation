<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>  
<!-- <html ng-app="myApp"> -->
<html lang="en">
<head>  
    <meta charset="utf-8">
    <!-- <meta name="viewport" content="width = device-width, initial-sacle=1"> -->
    <!-- <script data-require="angular.js@1.3.0" data-semver="1.3.0" src="https://code.angularjs.org/1.3.0/angular.js"></script> -->
    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <!-- <link data-require="bootstrap-css@3.3.7" data-semver="3.3.7" rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" /> -->
    <!-- <script data-require="bootstrap@3.3.7" data-semver="3.3.7" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
    <title>登录页面</title>  
  
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/test/login.css"/>"/>  
    <script type="text/javascript" src="<c:url value="/static/test/login.js"/>"></script>  
    <script type="text/javascript">
      $(document).ready(function() {
        ajaxRequest();
      });

      function ajaxRequest() {
        $.ajax({
          url: "http://localhost:8080/sshdemo/hello/ajax",
          type: "POST",
          dataType: "json",
          data: {
            "a": 1,
            "b": 2,
            "c": 3
          },
          async: false,
          success: function(data) {
            alert("success");
            $.each(data, function(index, element) {
              alert(element.a);
              alert(element.b);
              alert(element.c);
            });
          },
          error: function() {
            alert("error");
          }
        });
      }
    </script>
</head>  
  
<body>  
	<div id="main">  
        <div class="pin">  
            <div class="box">  
              <img id='bomimg' alt="" > 
				$("#bomimg").attr("src", basepath+"system/home/toLookImage" + node.id);
			  </img>
            </div>  
        </div>  
    </div>
  
</body>  
</html>  