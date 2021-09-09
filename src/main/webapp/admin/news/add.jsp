<%@page import="model.bean.category_child"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Thêm tin tức</h2>
                    <form action="" method="post" enctype="multipart/form-data">
                      <div class="form-group">
                        <label for="news">Tên Tin tức</label>
                        <input type="text" class="form-control" id="news" placeholder="Nhập tên tin tức" name="news" required="required">
                      </div>
                      <div class="form-group">
                        <label for="">Danh mục</label>
                        <select name="category" id="category">
                        <%
                        ArrayList<category_child> ListItem = (ArrayList<category_child>)request.getAttribute("ListItem");
                        if(ListItem != null){
                        	for(category_child obj : ListItem){
                        %>                    
                            <option value="<%=obj.getId()%>"><%=obj.getName()%></option>
                           <%}}%>
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="picture">Hình ảnh</label>
                        <input type="file" name="picture" id="picture" required="required">
                      </div>
                      <div class="form-group">
                        <label for="detail">Chi tiết</label>
                        <br>
                        <textarea name="detail" id="detail" cols="151" rows="10" placeholder="Nhập chi tiết tin" required="required"></textarea>
                      </div>
                      <div class="form-group form-check">
                        
                      </div>
                      <button type="submit" class="btn btn-primary">Đăng bài</button>
                    </form>
                  </div>
            </div>
        </div>
        <script type="text/javascript">
          document.getElementById("news").classList.add('active');
</script>
<script type="text/javascript">
	var ckeditor = CKEDITOR.replace('detail');
</script>
</body>
</html>