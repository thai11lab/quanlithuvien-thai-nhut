<%@page import="quanlithuvien.entity.Book"%>
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
	        <div class="container-fluid">
	          <div class="row mb-2">
	            <div class="col-sm-6">
	              <h1 class="m-0 text-dark">Danh sách tài liệu</h1>
	              
	              <!-- SEARCH FORM -->
			     
	            </div><!-- /.col -->
	            <div class="col-sm-6">
	              <ol class="breadcrumb float-sm-right">
	                <li class="breadcrumb-item"><a href="#">pk</a></li>
	              </ol>
	            </div><!-- /.col -->
	          </div><!-- /.row -->
	        </div><!-- /.container-fluid -->
	      </div>
	      
	      <div class="card">
              <div class="card-header" >
                <h3 class="card-title">
                	<a href="/books?action=ADD" class="btn btn-block btn-success " style="width: 200px">
	              	<i class="fas fa-plus-square"></i>	
	              	Thêm mới tài liệu
	              	
	              </a>
                </h3>
                
                 <form class="form-inline ml-3" style="float: right;" id="form-search" action="/books" method="get">
			        <div class="input-group input-group-sm">
			        <input class="form-control form-control-navbar" type="hidden" placeholder="Search" aria-label="Search" name="action" value="LIST">
			          <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search" name="key">
			          <div class="input-group-append">
			            <button class="btn btn-navbar" type="submit" style="background: green">
			              <i class="fas fa-search"></i>
			            </button>
			          </div>
			        </div>
			      </form>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã tài liệu</th>
                    <th>Tên tài liệu</th>
                    <th>Nhà xuất bản</th>
                    <th>Hình ảnh</th>
                    <th>Số lượng</th>                  
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  
                  <tbody>
                  <% 
                  	List<Book> books = (List<Book>) request.getAttribute("listBook");
                  	for(Book item : books){                 	
                  %>
                  <tr>
                    <td><%=item.getCode() %></td>
                    <td>
                    	<%=item.getName() %>
                    </td>
                    <td><%=item.getCompany() %></td>
                    <td><img > </td>
                    <td><%=item.getTotalBook() %></td>
                    <td style="display: flex;">
                    	<a class="s" style="width: 20px;height: 20px" href="/books?action=EDIT&id=<%=item.getId()%>">
                  			
                  			<i class="fas fa-pen"></i>
                    </a>|           
                            	<!-- href="/books?action=DELETE&id=<%=item.getId()%>" -->
                    	<btn  id="btn-delete" class="btn-delete">
                        	<input type="hidden" value="<%=item.getId()%>" id="id-delete">
                  			<i class="fas fa-trash-alt" style="width: 50%"></i>
                		</btn>|
                		<a class="">
                  			<i class="fas fa-info-circle"></i>
                		</a>
                		
                    </td>
                  </tr>
                  <%
                  	}
                  %>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
    </div>
    <%@include file="/decorator/admin/footer.jsp" %>
    <script type="text/javascript">
    	$(".btn-delete").click(function (e) { 
	        e.preventDefault();
	        debugger;
	        var id = $("#id-delete").val();
	        swal({
      		  title: "Bạn có chắc muốn xóa?",
      		  text: "",
      		  type: "warning",
      		  showCancelButton: true,
      		  confirmButtonClass: "btn-danger",
      		  confirmButtonText: "Yes",
      		  cancelButtonText: "No",
      		  closeOnConfirm: false,
      		  closeOnCancel: false
      		}).then(function(isConfirm){
      			if (isConfirm.value) {
      				window.location.href="/books?action=DELETE&id="+id;
                    swal("Good job!", "Xóa thành công!", "success");                    		
                    
				}                
          });
    	}); 	
    </script>
</body>

</html>