<%@page import="model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Thêm Danh mục</h2>
                    <%
                    String cat_child = request.getParameter("cat_child");
                    String err =request.getParameter("err");
                    if("1".equals(err)){
                    	%>
                    	<p style="color:red">Thêm thất bại</p>
                    	<%
                    }
                    ArrayList<category> ListItems = (ArrayList<category>)request.getAttribute("ListItems");
                    if(ListItems != null){
                    %>
                    <form action=""  method="post" id="myform">
                      <div class="form-group">
                        <label for="cat_child">Tên danh mục con</label>
                        <input type="text" class="form-control" id="cat_child"  value="<%if(cat_child != null){out.print(cat_child);}%>" placeholder="Nhập tên danh mục con" name="cat_child"  required="required">
                      </div>
                      <div class="form-group">
                        <label for="">Chọn Tên danh mục cha</label>
                        <select name="category" id="category">
                        <%for(category obj : ListItems){%>
                            <option value="<%=obj.getId()%>"><%=obj.getName()%></option>
 						<%}}%>                     
                        </select>
                      </div>
                      <button type="submit" class="btn btn-primary">Thêm Danh mục</button>
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