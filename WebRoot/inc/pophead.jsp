<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="<%=ctx %>/styles/css/popmin.css" />
<script type="text/javascript" src="<%=ctx %>/scripts/min.js"></script>
<script type="text/javascript" src="<%=ctx %>/styles/content/settings/main.js"></script>

<link rel="stylesheet" href="<%=ctx %>/styles/displaytag/css/screen.css" type="text/css"/>
<link rel="stylesheet" href="<%=ctx %>/styles/common.css" type="text/css"/>
<script type="text/javascript" src="<%=ctx %>/scripts/common.js" ></script>
<script type="text/javascript" src="<%=ctx %>/scripts/ready.js" ></script>