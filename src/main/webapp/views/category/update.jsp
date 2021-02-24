<%@page import="quanlithuvien.entity.Book"%>
<%@page import="quanlithuvien.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/decorator/admin/header.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<%@include file="/decorator/admin/navbar.jsp" %>
	    <!-- Main Sidebar Container -->
	    <%@include file="/decorator/admin/slidebar.jsp" %>
	    <div class="content-wrapper">
	      <!-- Content Header (Page header) -->
	      <div class="content-header">
	        
	      </div>
	      <%
	      	Category categoryUpdate = (Category) request.getAttribute("categoryUpdate");
	      %>
	      <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title"><%=request.getParameter("action").toString().equals("EDIT")?"Cập nhật tài liệu":"Thêm mới tài liệu"%></h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form method="post" action="/category?action=UPDATE_SUCCESS">
                <div class="card-body">
                  <div class="form-group">
                    <label for="code">Mã sách</label>
                    <input type="text" class="form-control" id="code" placeholder="Mã sách" name="code" value="<%=categoryUpdate.getCode()%>">
                  </div>
                  <div class="form-group">
                    <label for="name">Tên sách</label>
                    <input type="text" class="form-control" id="name" placeholder="name" name="name" value="<%=categoryUpdate.getName()%>">
                  </div>
                
                  
              
                </div>
                <!-- /.card-body -->
					<input type="hidden" class="form-check-input" name="id" value="<%=categoryUpdate.getId()%>">
                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
	      
    </div>
    <%@include file="/decorator/admin/footer.jsp" %>
</body>
</html>