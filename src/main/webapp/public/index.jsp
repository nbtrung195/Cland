<%@page import="model.dao.newsDAO"%>
<%@page import="model.bean.news"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/inc/PublicHeader.jsp" %>
    <br>
    
     <div class="container">
     <%
    String msg = request.getParameter("msg");
    String err = request.getParameter("err");
    if("1".equals(msg)){out.print("<h5 style=\"color:green\">Thêm tài khoản thành công</h5>");}
    if("1".equals(err)){out.print("<h5 style=\"color:red\">Thêm tài khoản thất bại</h5>");}
    %>
      <div class="row">
        <div class="col-9">
          <div class="row">
          <%
          news LastestItem = (news)request.getAttribute("LastestItem");
          %>
            <div class="col-8">
              <a href="<%=request.getContextPath()%>/detail?id=<%=LastestItem.getId()%>">
                <img class="new-hot_img" src="<%=request.getContextPath()%>/files/<%=LastestItem.getPicture()%>" alt="">
              </a>
            </div>
            <div class="col-4">
              <a class="title" href="<%=request.getContextPath()%>/detail?id=<%=LastestItem.getId()%>">
                <h4>
                  <%=LastestItem.getName()%>
                </h4>
              </a>
              <p>
                <%=LastestItem.getDetail().substring(0,320)%>...
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="container">
      <div class="row">
      <%
      newsDAO NewsDAO = new newsDAO();
      ArrayList<news> ListItem1 = (ArrayList<news>)request.getAttribute("ListItem1");
      for(news obj : ListItem1){
      %>
        <div class="col-3">
          <a class="title" href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
            <h5>
              <%=obj.getName()%>
            </h5>
          </a>
          <p>
            <%=obj.getDetail().substring(0,115)%> ...
          </p>
        </div>
        <%}%>
      </div>
      <hr>
    </div>

    <div class="container">

      <div class="float-left col pl-lg-0 col-4 border-right">
      <%
      ArrayList<news> ListItem2 = (ArrayList<news>)request.getAttribute("ListItem2");
      for(news obj : ListItem2){
      %>
        <div class="">
          <a class="title" href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
            <h5>
              <%=obj.getName()%>
            </h5>
          </a>
          <div class="d-flex">
          <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
            <img class="mr-2 pt-2" src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" height="90px"
              width="120px" alt="" ></a>
            <p class="card-text mb-auto">
              <%=obj.getDetail().substring(0,112)%> ...
            </p>
          </div>
          <hr>
        </div>
        <%}%>
      </div>
      <div class="float-right col-8">
        <div class="mb-3">
        <%
        ArrayList<category> ListCat = (ArrayList<category>)request.getAttribute("ListCategory");
        for(category obj : ListCat){
        %>
          <div class="col d-flex">
            <h4 class="text-danger title_category border-bottom shadow-sm"><%=obj.getName()%></h4>
            <ul style="list-style: none;" class="flex-row d-flex mt-1">
            <%
            category_childDAO Cat_childDAO = new category_childDAO();
            ArrayList<category_child> ListCatChild = Cat_childDAO.getItemss(obj.getId());
            for(category_child obt : ListCatChild){
            %>
              <li class="mr-4"><a href="<%=request.getContextPath()%>/category?id=<%=obt.getId()%>" class="title" ><%=obt.getName()%></a></li>
              <%}%>
            </ul>
          </div>
          <div class="row border-bottom ml-1">
           <%
            ArrayList<news> ListNews = NewsDAO.getItemsOfCat(obj.getId());
            for(news obk : ListNews){
            %>
            <div class="col d-flex">        
              <a><img class="row-6 mr-2 mt-2" src="<%=request.getContextPath()%>/files/<%=obk.getPicture()%>"
               height="125px" width="160px"  alt=""></a>
              <div class="row-6">
                <h6 class="text-success"><a href="<%=request.getContextPath()%>/detail?id=<%=obk.getId()%>" class="title" ><%=obk.getName()%></a></h6>
                <p><%=obk.getDetail().substring(0, 62)%>...</p>
              </div>
              
            </div> 
            <%}%>        
          </div>
          <%}%>
        </div>
        
      </div>
      <div class="clearfix"></div>
    </div>
    <footer>
      <div class="container py-3 bg-dark">
        <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
      </div>
    </footer>
  </div>
</body>

</html>