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
	      	Book bookUpdate = (Book) request.getAttribute("bookUpdate");
	      %>
	      <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title"><%=request.getParameter("action").toString().equals("EDIT")?"Cập nhật tài liệu":"Thêm mới tài liệu"%></h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form method="post" action="/books?action=UPDATE_SUCCESS">
                <div class="card-body">
                  <div class="form-group">
                    <label for="code">Mã sách</label>
                    <input type="text" class="form-control" id="code" placeholder="Mã sách" name="code" value="<%=bookUpdate.getCode()%>">
                  </div>
                  <div class="form-group">
                    <label for="name">Tên sách</label>
                    <input type="text" class="form-control" id="name" placeholder="name" name="name" value="<%=bookUpdate.getName()%>">
                  </div>
                  <div class="form-group">
                    <label for="number">Số lượng</label>
                    <input type="number" class="form-control" id="totalBook" placeholder="name" name="totalBook" value="<%=bookUpdate.getTotalBook()%>">
                  </div>
                  <div class="form-group">
                    <label for="name">Nhà xuất bản</label>
                    <input type="text" class="form-control" id="company" placeholder="company" name="company" value="<%=bookUpdate.getCompany()%>">
                  </div>
                  <div class="form-group">
                  	<label>Thể loại</label>
                    <select id="cars" name="category">
                    	<%List<Category> categories =(List<Category>) request.getAttribute("listCT");
                    		for(Category category: categories){                   		
                    	%>
						<option value="<%=category.getId()%>"><%=category.getName()%></option>
						<%} %>
  					</select>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <div class="input-group">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="exampleInputFile">
                        <label class="custom-file-label" for="exampleInputFile">Choose file</label>
                      </div>
                      <div class="input-group-append">
                        <span class="input-group-text">Upload</span>
                      </div>
                    </div>
                  </div>
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                </div>
                <!-- /.card-body -->
					<input type="hidden" class="form-check-input" name="id" value="<%=bookUpdate.getId()%>">
                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
	      
    </div>
    <%@include file="/decorator/admin/footer.jsp" %>
</body>
</html>