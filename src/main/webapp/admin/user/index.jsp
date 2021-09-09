<%@page import="model.bean.user"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
          <div class="container mt-2">
            <h2>Quản lý người dùng</h2>
            <%
            String msg = request.getParameter("msg");     
            if("1".equals(msg)){out.print("<p style=\"color: green\">Thêm thành công</p>");}
            if("2".equals(msg)){out.print("<p style=\"color: green\">Xóa thành công</p>");}
            if("3".equals(msg)){out.print("<p style=\"color: green\">Sửa thành công</p>");}
            String err = request.getParameter("err");
            if("1".equals(err)){out.print("<p style=\"color: red\">Xóa thất bại</p>");}
            if("2".equals(err)){out.print("<p style=\"color: red\">Sửa thất bại</p>");}
            user who = (user)request.getAttribute("who");
            if(who.getRole().getId()==1){
            %>
            <h4>
                <a name="" id="" class="btn btn-success" href="<%=request.getContextPath()%>/admin/user/add" role="button">Thêm</a>
            </h4>
         	<%
            }
         	ArrayList<user> ListItems = (ArrayList<user>)request.getAttribute("ListItems");
         	if(ListItems != null){
         	%>
            <table class="table table-bordered" id="myTable">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tên tài khoản</th>
                  <th>Ngày sinh</th>
                  <th>Email</th>   
                  <th>Phân quyền</th>
                  <th>Chức năng</th>
                </tr>
              </thead>
              
              <tbody id="myTable">
              <%
              for(user obj : ListItems){
              %>
                <tr>
                  <td><%=obj.getId()%></td>
                  <td><%=obj.getName()%></td>
                  <td><%=obj.getBirth()%></td>
                  <td><%=obj.getEmail()%></td>
                  <td><%=obj.getRole().getName()%></td>
                  <td>
                  <% if(who.getRole().getId()==1){%>
                    <a name="" id="" class="btn btn-primary border-dark"  href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>" >Sửa</a>
                	<a name="" id="" class="btn btn-danger border-dark"  href="<%=request.getContextPath()%>/admin/user/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')">Xóa</a>
                  <%}if(who.getRole().getId()==2){
                  			if(obj.getRole().getId()==2||obj.getRole().getId()==3||obj.getRole().getId()==4){
                  %>
                    <a name="" id="" class="btn btn-primary border-dark"  href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>" >Sửa</a>
                	<a name="" id="" class="btn btn-danger border-dark"  href="<%=request.getContextPath()%>/admin/user/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')">Xóa</a>              
                  <%}}if(who.getRole().getId()==3){
                	  			if(who.getId()==obj.getId()){
                	%>
                	<a name="" id="" class="btn btn-primary border-dark"  href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>" >Sửa</a>
                	<a name="" id="" class="btn btn-danger border-dark"  href="<%=request.getContextPath()%>/admin/user/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')">Xóa</a>                            
                	<%  				
                	  			}
                  }
                  %>
                  </td>
                </tr>
                <%}} %>
              </tbody>
            </table>
            <script type="text/javascript">
        $(document).ready( function () {
            $('#myTable').DataTable();
        } );
   </script>
          </div>
        </div>
      </div>
      <script type="text/javascript">
          document.getElementById("user").classList.add('active');
</script>
<footer>
      <div class="container-fluid py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
</body>
</html>