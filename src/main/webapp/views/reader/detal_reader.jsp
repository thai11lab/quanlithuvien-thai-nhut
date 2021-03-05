<%@page import="quanlithuvien.entity.Book"%>
<%@page import="quanlithuvien.entity.Reader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết người đọc</title>

</head>
<%@include file="/decorator/admin/header.jsp" %>
<%List<Book> listObject = (List<Book>) request.getAttribute("listBookObject");
Reader reader =(Reader) request.getAttribute("ReaderObject");
%>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<%@include file="/decorator/admin/navbar.jsp" %>
	    <!-- Main Sidebar Container -->
	    <%@include file="/decorator/admin/slidebar.jsp" %>
	    <div class="content-wrapper">
	      <!-- Content Header (Page header) -->
	      <div class="content-header">
	        <div class="container-fluid">
	          <div class="row mb-2">
	            
	          </div><!-- /.row -->
	        </div><!-- /.container-fluid -->
	      </div>
	      <!-- Default box -->
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Họ tên : <%=reader.getName()%></h3>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
              <i class="fas fa-minus"></i>
            </button>
            <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
          <div class="row">
            <div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">
              <div class="row">
                <div class="col-12 col-sm-4">
                  <div class="info-box bg-light">
                    <div class="info-box-content">
                      <span class="info-box-text text-center text-muted">Số sách mượnt</span>
                      <span class="info-box-number text-center text-muted mb-0">2300</span>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-sm-4">
                  <div class="info-box bg-light">
                    <div class="info-box-content">
                      <span class="info-box-text text-center text-muted">Total amount spent</span>
                      <span class="info-box-number text-center text-muted mb-0">2000</span>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-sm-4">
                  <div class="info-box bg-light">
                    <div class="info-box-content">
                      <span class="info-box-text text-center text-muted">Estimated project duration</span>
                      <span class="info-box-number text-center text-muted mb-0">20</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
              <c:forEach var="itemDetailProduct" items="${listBookObject}">
                <div class="col-12">
                  <h4>Tên sách :${itemDetailProduct.name}</h4>
                    <div class="post">
                      <p>Nhà xuất bản :${itemDetailProduct.company}</p>
                      <!-- /.user-block -->
                      <p>
                        <a href="#" class="link-black text-sm"><i class="fas fa-link mr-1"></i> Demo File 1 v2</a>
                      </p>
                    </div>                  
                </div>
                </c:forEach>
              </div>
            </div>
            
          </div>
        </div>
        <!-- /.card-body -->
      </div>
      <!-- /.card -->
	      
    </div>
    <%@include file="/decorator/admin/footer.jsp" %>
</body>
</html>