<%@page import="quanlithuvien.entity.Reader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
                	<a href="/reader?action=ADD" class="btn btn-block btn-success " style="width: 200px">
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
			        <div class="input-group input-group-sm">
			        	<select>
			        		
			        	</select>
			        </div>
			      </form>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã bạn đọc</th>
                    <th>Họ tên</th>
                    <th>Địa chỉ</th>   
                    <th>Tuổi</th>              
                    <th>Hành động</th>
                  </tr>
                  </thead>
                  
                  <tbody>
                  <% 
                  	List<Reader> readers = (List<Reader>) request.getAttribute("lisReaders");
                  	for(Reader item : readers){                 	
                  %>
                  <tr>
                    <td><%=item.getCode() %></td>
                    <td>
                    	<%=item.getName() %>
                    </td>
                    <td>
                    	<%=item.getAddress() %>
                    </td>
                    <td>
                    	<%=item.getAge() %>
                    </td>
                    <td style="display: flex;">
                    	<a class="s" style="width: 20px;height: 20px" href="reader?action=EDIT&id=<%=item.getId()%>">
                  			
                  			<i class="fas fa-pen"></i>
                    </a>|           
                            	<!-- href="/books?action=DELETE&id=<%=item.getId()%>" -->
                    	<btn  id="btn-delete" class="btn-delete">
                        	<input type="hidden" value="<%=item.getId()%>" id="id-delete">
                  			<i class="fas fa-trash-alt" style="width: 50%"></i>
                		</btn>|
                		<a class="modalDetail" href="reader?action=detail_reader&id=<%=item.getId()%>">
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
    
    <div class="modal" tabindex="-1" role="dialog" id="modalReader">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Modal body text goes here.</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">Save changes</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
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
      				window.location.href="/category?action=DELETE&id="+id;
                    swal("Good job!", "Xóa thành công!", "success");                    		
                  
				}                
          });
    	});  
    </script>
</body>
</html>