<%@page import="model.dao.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
<%
int NumNews = (Integer)request.getAttribute("NumNews");
int NumUser = (Integer)request.getAttribute("NumUser");
int NumCat = (Integer)request.getAttribute("NumCat");
int NumCmt = (Integer)request.getAttribute("NumCmt");
%>
      <div class="col-10 ">
        <div class="container-fluid">
          <div class="row row-cols-3">
            <div class="col bg-light shadow-sm admin-index" >
              <svg style="float: left;margin:0px 55px;" xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-newspaper" viewBox="0 0 16 16">
                <path d="M0 2.5A1.5 1.5 0 0 1 1.5 1h11A1.5 1.5 0 0 1 14 2.5v10.528c0 .3-.05.654-.238.972h.738a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 1 1 0v9a1.5 1.5 0 0 1-1.5 1.5H1.497A1.497 1.497 0 0 1 0 13.5v-11zM12 14c.37 0 .654-.211.853-.441.092-.106.147-.279.147-.531V2.5a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0-.5.5v11c0 .278.223.5.497.5H12z"/>
                <path d="M2 3h10v2H2V3zm0 3h4v3H2V6zm0 4h4v1H2v-1zm0 2h4v1H2v-1zm5-6h2v1H7V6zm3 0h2v1h-2V6zM7 8h2v1H7V8zm3 0h2v1h-2V8zm-3 2h2v1H7v-1zm3 0h2v1h-2v-1zm-3 2h2v1H7v-1zm3 0h2v1h-2v-1z"/>
              </svg>
              <h4 ><a href="<%=request.getContextPath()%>/admin/news">Quản lý tin tức</a></h4>
              <p>có <%=NumNews%> tin tức</p>
            </div>
            <div  class="col bg-light shadow-sm admin-index" >
              <svg style="float: left;margin:0px 55px;" xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16">
                <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z"/>
              </svg>
              <h4 ><a href="<%=request.getContextPath()%>/admin/user"> Người dùng</a></h4>
              <p>có <%=NumUser%> người dùng</p>
            </div>
            <div class="col bg-light shadow-sm admin-index" >
              <svg style="float: left;margin:0px 55px;" xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-bookmarks-fill" viewBox="0 0 16 16">
                <path d="M2 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L7 13.101l-4.223 2.815A.5.5 0 0 1 2 15.5V4z"/>
                <path d="M4.268 1A2 2 0 0 1 6 0h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L13 13.768V2a1 1 0 0 0-1-1H4.268z"/>
              </svg>
              <h4><a href="<%=request.getContextPath()%>/admin/category">Danh mục</a></h4>
              <p>có <%=NumCat%> danh mục</p>
            </div>
            <div class="col bg-light shadow-sm admin-index" >
              <svg style="float: left;margin:0px 55px;" xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-chat-left-dots-fill" viewBox="0 0 16 16">
                <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H4.414a1 1 0 0 0-.707.293L.854 15.146A.5.5 0 0 1 0 14.793V2zm5 4a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
              </svg>
              <h4><a href="<%=request.getContextPath()%>/admin/comment">Bình luận</a></h4>
              <p>có <%=NumCmt%> bình luận</p>
            </div>
          </div>
        </div>      
      </div>
    </div>
  </div>
  <script type="text/javascript">
  document.getElementById("home").classList.add('active');
</script>
<%@include file="/inc/AdminFooter.jsp"%>
</body>

</html>