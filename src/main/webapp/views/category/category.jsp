<%@page import="quanlithuvien.entity.Category"%>
<%@page import="quanlithuvien.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

	    <div class="content-wrapper">
	      <!-- Content Header (Page header) -->
	      <div class="content-header">
	        <div class="container-fluid">
	          <div class="row mb-2">
	            <div class="col-sm-6">
	              <h1 class="m-0 text-dark">Thể loại</h1>
	              
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
                	<a href="/category?action=ADD" class="btn btn-block btn-success " style="width: 200px">
	              	<i class="fas fa-plus-square"></i>	
	              	Thêm mới thể loại
	              	
	              </a>
                </h3>
                
                 <form class="form-inline ml-3" style="float: right;" id="form-search" action="/category?action=SEARCH" method="post">
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
                  	<th>STT</th>
                    <th>Mã thể loại</th>
                    <th>Tên thể loại</th>
                                    
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  
                  <tbody>
                  <% 
                  	int stt =1;
                  	List<Category> categories = (List<Category>) request.getAttribute("listCategories");
                  	for(Category item : categories){                 	
                  %>
                  <tr>
                  	<td><%=stt++%></td>
                    <td><%=item.getCode() %></td>
                    <td>
                    	<%=item.getName() %>
                    </td>
                    
                    <td style="display: flex;">
                    	<a class="s" style="width: 20px;height: 20px" href="/category?action=EDIT&id=<%=item.getId()%>">
                  			
                  			<i class="fas fa-pen"></i>
                    </a>|           
                            	<!-- href="/books?action=DELETE&id=<%=item.getId()%>" -->
                    	<btn  id="btn-delete" class="btn-delete" onclick="deleleByID(<%=item.getId()%>)">
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
    	 	
    	
    	function deleleByID(id) {
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
			}).then(function (isConfirm) {
				if (isConfirm.value) {
					window.location.href = "/category?action=DELETE&id="+id;
					swal("Good job!", "Xóa thành công!", "success");

				}
			});
		}
    </script>
</body>

</html>