<%@page import="quanlithuvien.entity.Reader"%>
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
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<div class="wrapper">
		<%@include file="/decorator/admin/navbar.jsp" %>
	    <!-- Main Sidebar Container -->
	    <%@include file="/decorator/admin/slidebar.jsp" %>
	    <div class="content-wrapper">
	      <!-- Content Header (Page header) -->
	      <div class="content-header">
	        
	      </div>
	      <%
	      	Reader reader = (Reader) request.getAttribute("reader");
	      %>
	      <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title"><%=request.getParameter("action").toString().equals("EDIT")?"Cập nhật":"Thêm mới"%></h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form method="post" action="/reader?action=ADD_SUCCESS">
                <div class="card-body">
                  <div class="form-group">
                    <label for="code">Mã</label>
                    <input type="text" class="form-control" id="code" placeholder="Mã" name="code" value="<%=reader.getCode()%>">
                    <span id="validate-code"></span>
                  </div>
                  <div class="form-group">
                    <label for="name">Tên</label>
                    <input type="text" class="form-control" id="name" placeholder="Tên" name="name" value="<%=reader.getName()%>">
                    <span id="validate-name"></span>
                  </div>
                  <div class="form-group">
                    <label for="name">Địa chỉ</label>
                    <input type="text" class="form-control" id="name" placeholder="Địa chỉ" name="address" value="<%=reader.getAddress()%>">
                    <span id="validate-name"></span>
                  </div>
                  <div class="form-group">
                    <label for="name">Tuổi</label>
                    <input type="number" class="form-control" id="name" placeholder="Tên" name="age" value="<%=reader.getAge()%>">
                    <span id="validate-name"></span>
                  </div>    
                  <div class="form-group">
                    <label for="name">Sách mượn</label>
                    <br></br>
                    <div style="padding-right: 30px">
	                    
	                    <%List<Book> books =(List<Book>) request.getAttribute("listBook");
                    		for(Book book: books){                   		
                    	%>
							<%=book.getName() %><input type="checkbox" class="form-check-input" id="name"  name="product_id" value="<%=book.getId() %>" >
						<%} %>
                    </div>
                    <span id="validate-name"></span>
                  </div>          
                  <input type="hidden" class="form-check-input" name="id" value="">
                </div>
                <!-- /.card-body -->
				
                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
	      
    </div>
    <%@include file="/decorator/admin/footer.jsp" %>
</body>
</html>