<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/inc/PublicHeader.jsp" %>
        <br>
        <div class="container">
        <%
        int number = (Integer)request.getAttribute("NumberOfItems");
 		ArrayList<news> ListItems = (ArrayList<news>)request.getAttribute("ListItems");       
 		if(number != 0){
        %>
            <h1>Có <%=number%> kết quả được tìm thấy</h1>
        <%}else{%>
        	 <h1>Không có kết quả nào được tìm thấy</h1>
        <%}%>    
            <br>
            <div class="row">
                <div class="col-8">
                <%for(news obj : ListItems){%>
                    <div class="row">
                        <div class="col-8">
                            <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
                                <h5><%=obj.getName()%></h5>
                            </a>
                            <p><%=obj.getDetail().substring(0, 180)%>...</p>
                        </div>
                        <div class="col-4">
                            <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>">
                            <img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>"
                             width="200px" height="100%" alt=""></a>
                        </div>
                        <hr>
                    </div>
                    <br>
                   <%}%>
                </div>
               <%@ include file="/inc/PublicSidebar.jsp"%>
            </div>
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