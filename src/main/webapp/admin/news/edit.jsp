<%@page import="model.bean.news"%>
<%@page import="model.bean.category_child"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Sửa tin tức</h2>
                    <%
                    String err = request.getParameter("err");
                    if("1".equals(err)){out.print("<p style=\"color:red\">Sửa thất bại</p>");}		
                    ArrayList<category_child> ListItem = (ArrayList<category_child>)request.getAttribute("ListItem");
                    news News = (news)request.getAttribute("News");
                    if(News != null){
                    %>
                    <form action=""  method="post" enctype="multipart/form-data" role="form"  id="form">
                      <div class="form-group">
                        <label for="news">Tên Tin tức</label>
                        <input type="text" class="form-control" id="news" placeholder="Nhập tên tin tức" name="news" value="<%=News.getName()%>" required="required">
                      </div>
                      <div class="form-group">
                        <label for="category_new">Danh mục</label>
                        <select id="category"  name="category_new"  class="form-control" >
                        <%                    
                        if(ListItem != null){		
                        	for(category_child obj : ListItem){
                        %>
                            <option <%if(News.getCat().getId() == obj.getId()){out.print(" selected");}%> value="<%=obj.getId()%>"><%=obj.getName()%></option>
                           <%}}%>
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="picture">Hình ảnh</label>
                        <input type="file" name="picture" id="picture" value="<%=News.getPicture()%>"/>
                      </div>
                      <div class="form-group">
                        <label for="detail">Chi tiết</label>
                        <br>
                        <textarea name="detail" id="detail" cols="151" rows="10" placeholder="Nhập chi tiết tin"  required="required"><%=News.getDetail()%></textarea>
                      </div>                     
                      <button type="submit" name="submit"  class="btn btn-primary">Sửa bài</button>
                    </form>
                    <%}%>
                  </div>
            </div>
        </div>
        <br>
        <script type="text/javascript">
          document.getElementById("news").classList.add('active');
</script>
<script type="text/javascript">
	var ckeditor = CKEDITOR.replace('detail');
</script>
<footer>
      <div class="container-fluid py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
</body>
</html>