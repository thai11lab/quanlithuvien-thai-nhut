<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<%@include file="/decorator/admin/header.jsp" %>
</head>
	<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

   		
		<%@include file="/decorator/admin/navbar.jsp" %>
	    <!-- Main Sidebar Container -->
	    <%@include file="/decorator/admin/slidebar.jsp" %>
		<jsp:include page="${view}"></jsp:include>
	   
    <%@include file="/decorator/admin/footer.jsp" %>
	</body>
</html>

</html>