<%@page import="model.bean.news"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
          <div class="container mt-2">
            <h2>Quản lý tin tức</h2>
            <%
         	String msg = request.getParameter("msg");
         	if("1".equals(msg)){out.print("<p style=\"color:green\">Xóa thành công</p>");}
         	if("2".equals(msg)){out.print("<p style=\"color:green\">Sửa thành công</p>");}		
         	if("3".equals(msg)){out.print("<p style=\"color:green\">Thêm thành công</p>");}		
         	String err = request.getParameter("err");
         	if("1".equals(err)){out.print("<p style=\"color:red\">Xóa thất bại</p>");}
         	if("2".equals(err)){out.print("<p style=\"color:red\">Sửa thất bại</p>");}
         	%>
            <h4>
                <a name="" id="" class="btn btn-success" href="<%=request.getContextPath()%>/admin/news/add" role="button">Thêm</a>
            </h4>
         	
            <table class="table table-bordered" id="myTable">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tên Tin Tức</th>
                  <th>Danh mục</th>
                  <th>Tác giả</th>               
                  <th>Ngày đăng</th>
                  <th>Lượt đọc</th>
                  <th width="95px" >Chức năng</th>
                </tr>
              </thead>
              
              <tbody id="myTable">
              <%
              user who = (user)request.getAttribute("who"); 
              ArrayList<news> ListItems = (ArrayList<news>)request.getAttribute("ListItems");
              if(ListItems != null){
            	  for(news obj : ListItems){
              %>
                <tr>
                  <td><%=obj.getId()%></td>
                  <td><%=obj.getName()%></td>
                  <td><%=obj.getCat().getName()%></td>
                  <td><%=obj.getAuthor().getName()%>(<%=obj.getAuthor().getRole().getName()%>)</td>              
                  <td><%=obj.getDate_create()%></td>
                  <td><%=obj.getView()%></td>
                  <td>
                  <%
                  if(who.getRole().getId()==1){
                  %>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/news/edit?id=<%=obj.getId()%>">Sửa</a>
                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/admin/news/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">Xóa</a>
                   <%
                  }if(who.getRole().getId()==2){
                	  if(obj.getAuthor().getRole().getId()==3||obj.getAuthor().getRole().getId()==2){
                  %>
                  <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/news/edit?id=<%=obj.getId()%>">Sửa</a>
                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/admin/news/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">Xóa</a>
                  <%}}if(who.getRole().getId()==3){
                	  if(who.getId()==obj.getAuthor().getId()){
                  %>
                   <a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/news/edit?id=<%=obj.getId()%>">Sửa</a>
                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/admin/news/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">Xóa</a>
                  <%}}%>
                  </td>             
                </tr>
            <%}}%>
              </tbody>
            </table>
			 <script type="text/javascript">
        $(document).ready( function () {
            $('#myTable').DataTable();
        } );
   </script>
          </div>
          <br>
        </div>
      </div>
      <script type="text/javascript">
          document.getElementById("news").classList.add('active');
</script>
<footer>
      <div class="container-fluid py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
</body>
</html>