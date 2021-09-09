<%@page import="model.bean.comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
          <div class="container mt-2">
            <h2>Bình luận của độc giả</h2>
              
            <%
            String msg = request.getParameter("msg");
            if("1".equals(msg)){out.print("<p style=\"color:green\">Xóa thành công</p>");}
            String err = request.getParameter("err");
            if("1".equals(err)){out.print("<p style=\"color:red\">Xóa thất bại</p>");}
            user who = (user)request.getAttribute("who"); 
            ArrayList<comment> ListItems = (ArrayList<comment>)request.getAttribute("ListItems");
            if(ListItems != null){
            %>
            <table class="table table-bordered" id="myTable">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tên tài khoản</th>
                  <th>Tin tức</th>   
                  <th>Nội dung bình luận</th>
                  <th>Thời gian</th>
                  <th>Chức năng</th>
                </tr>
              </thead>
              
              <tbody id="myTable">
               <%for(comment obj : ListItems){%>
                <tr>
                  <td><%=obj.getId()%></td>
                  <td><%=obj.getReader().getName()%></td>
                  <td><%=obj.getNews().getName()%></td>
                  <td><%=obj.getContent()%></td>
                  <td><%=obj.getTime()%></td>
                  <td>
                  <%if(who.getRole().getId()==1){%>
                    <a name="" id="" class="btn btn-danger border-dark"  href="<%=request.getContextPath()%>/admin/comment/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">Xóa</a>
                 <%}%>
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
          <script type="text/javascript">
          document.getElementById("comment").classList.add('active');
</script>
        </div>
      </div>
      <footer>
      <div class="container-fluid py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
</body>
</html>