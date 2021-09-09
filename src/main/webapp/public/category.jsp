<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/inc/PublicHeader.jsp" %>
        <br>
        <div class="container">
        <%category_child cat_child = (category_child)request.getAttribute("cat_child");%>
            <h1><%=cat_child.getCategory().getName()%> -> <%=cat_child.getName()%></h1>
            <br>
            <div class="row">          
                <div class="col-8">
                <%              
                ArrayList<news> ListNewsOfCatChild = (ArrayList<news>)request.getAttribute("ListNewsOfCatChild");
                for(news obj : ListNewsOfCatChild){
                %>
                    <div class="row">
                        <div class="col-8">
                            <a class="title"  href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
                                <h5><%=obj.getName()%></h5>
                            </a>
                            <p><%=obj.getDetail().substring(0, 190)%> ...</p>
                        </div>
                        <div class="col-auto">
                            <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>"><img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" width="200px" height="150px" alt=""></a>
                        </div>    
                    </div>
                     <hr>
                    <br>
                    <%}%>
                </div>
                <%@include file="/inc/PublicSidebar.jsp" %>
            </div>
        </div>
        <br>
        <div class="container ">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                <%
                int id = Integer.parseInt(request.getParameter("id"));
                int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
                int currentPage = (Integer)request.getAttribute("currentPage");
                %>
                  <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/category?id=<%=id%>&page=<%=currentPage-1%>">Previous</a></li>
                  <%for(int i=1;i<=NumberOfPages;i++){
                  			if(i == currentPage){
                  %>
                  <li class="page-item "><a class="page-link bg-dark text-light" href="<%=request.getContextPath()%>/category?id=<%=id%>&page=<%=i%>"><%=i%></a></li>
                <%}else{%>
                   <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/category?id=<%=id%>&page=<%=i%>"><%=i%></a></li>               
                 <%}}%>
                  <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/category?id=<%=id%>&page=<%=currentPage+1%>">Next</a></li>
                </ul>
              </nav>
        </div>
          <br>
        <footer>
            <div class="container py-3 bg-dark">
              <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
            </div>
          </footer>
    </div>
</body>

</html>