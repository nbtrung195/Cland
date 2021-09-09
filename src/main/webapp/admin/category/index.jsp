<%@page import="model.bean.category_child"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
      <div class="container mt-2">
        <h2>Quản lý danh mục</h2>
        <%
        String err = request.getParameter("err");
        if("1".equals(err)){out.print("<p style=\"color: red\">Xóa thất bại</p>");}
        if("2".equals(err)){out.print("<p style=\"color: red\">Sửa thất bại</p>");}
        String msg = request.getParameter("msg");
        if("1".equals(msg)){out.print("<p style=\"color: green\">Thêm thành công</p>");}
        if("2".equals(msg)){out.print("<p style=\"color: green\">Sửa thành công</p>");}
        if("3".equals(msg)){out.print("<p style=\"color: green\">Xóa thành công</p>");}
        user who = (user)request.getAttribute("who");
        if(who.getRole().getId()==1){
        %>
        <h4>
          <a name="" id="" class="btn btn-success" href="<%=request.getContextPath()%>/admin/category/add" role="button">Thêm</a>
        </h4>
        <%}%>
        <%
        ArrayList<category_child> ListItems = (ArrayList<category_child>)request.getAttribute("ListItems");
        if(ListItems != null){
        %>
        <table class="table table-bordered"  id="myTable">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tên Danh Mục Con</th>
              <th>Tên Danh Mục Cha</th>
              <th>Chức năng</th>
            </tr>
          </thead>

          <tbody >
          <%for(category_child obj : ListItems){%>
            <tr>
              <td><%=obj.getId()%></td>
              <td><%=obj.getName()%></td>
              <td><%=obj.getCategory().getName()%></td>
              <td>
              <%if(who.getRole().getId()==1){%>
              	<a name="" id="" class="btn btn-primary border-dark"  href="<%=request.getContextPath()%>/admin/category/edit?id=<%=obj.getId()%>" role="button">Sửa</a>
                <a name="" id="" class="btn btn-danger border-dark"  href="<%=request.getContextPath()%>/admin/category/delete?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')" role="button">Xóa</a>
             <%}%>
              </td>
            </tr>
            <%}}%>
          </tbody>
        </table>
      </div>
      </nav>
    </div>
  </div>
  <br>
  <script type="text/javascript">
        $(document).ready( function () {
            $('#myTable').DataTable();
        } );
   </script>
  <script type="text/javascript">
          document.getElementById("category").classList.add('active');
</script>
<footer>
      <div class="container-fluid py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
</body>

</html>