<%@page import="model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.category_child"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Sửa Danh mục</h2>
                    <%
                    String category_child = request.getParameter("category_child");
                    String err = request.getParameter("err");
                    if("1".equals(err)){
                    	out.print("<p style=\"color:red\">Có lỗi khi sửa</p>");
                    }
                    category_child cat_child = (category_child)request.getAttribute("cat_child");
                    if(cat_child != null ){category_child = cat_child.getName();}
                    %>
                    <form action="" method="post">
                      <div class="form-group">
                        <label for="category_child">Tên danh mục con</label>
                        <input type="category_child" class="form-control" id="category_child"  value="<%if(category_child != null){out.print(category_child);}%>" placeholder="Nhập tên danh mục con" name="category_child" required="required">
                      </div>
                      <div class="form-group">
                        <label for="">Chọn Tên danh mục cha</label>
                        <select name="category" id="category">
                        <%
                        ArrayList<category> ListItems = (ArrayList<category>)request.getAttribute("ListItems");
                        if(ListItems != null){
                        for(category obj : ListItems){
                        %>
                            <option <%if(cat_child.getCategory().getId() == obj.getId()){out.print("selected");}%> value="<%=obj.getId()%>"><%=obj.getName()%></option>
                            <%}}%>
                        </select>
                      </div>
                      <button type="submit" class="btn btn-primary">Sửa Danh mục</button>
                    </form>
                  </div>
            </div>
        </div>
        <script type="text/javascript">
          document.getElementById("category").classList.add('active');
</script>
<%@include file="/inc/AdminFooter.jsp"%>
</body>
</html>