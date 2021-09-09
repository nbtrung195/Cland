<%@page import="model.bean.comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/inc/PublicHeader.jsp" %>
        <br>
        <div class="container">
            <div class="row">
            <%
            news News = (news)request.getAttribute("News");
            int NumberOfComment = (Integer)request.getAttribute("NumberOfComment");
            ArrayList<comment> ListComment = (ArrayList<comment>)request.getAttribute("ListComment");
            %>
                <div class="col-8">
                    <h1><%=News.getName()%></h1>
        <p>Lượt đọc: <%=News.getView()%> || Bình luận: <%=NumberOfComment%> || Ngày đăng: <%=News.getDate_create()%></p>
                    <img src="<%=request.getContextPath()%>/files/<%=News.getPicture()%>" width="720px" alt="">
                    <p><%=News.getDetail()%></p>
                </div>
                <%@ include file="/inc/PublicSidebar.jsp" %>
            </div>
            <h3>Bình Luận</h3>
            <%
            user reader = (user)session.getAttribute("Login2");
            if(reader != null){
            %>
            <label for="comment"><%=reader.getName()%>:</label>
            <input id="cmt"  type="text" name="comment" value="" placeholder="Nhập bình luận" required>
        <a name="" id="" class="btn btn-primary border-dark" href="javascript:void(0)" role="button" onclick="comment()">Bình luận</a>
            <%}else{%>
            <a href="<%=request.getContextPath()%>/signinPublic">Đăng nhập để bình luận</a>
            <%}%>
            <%
            if(ListComment != null){
            	for(comment obj : ListComment){	
            %>
<p><span style="font-weight:800 ;"><%=obj.getReader().getName()%> </span><%="("+obj.getTime()+"): "+obj.getContent()%></p>
                <%}}%>
                <div class = "binhluan"></div>
            <h4>Tin liên quan</h4>
            <ul>
             <%
             ArrayList<news> RelateNews = (ArrayList<news>)request.getAttribute("RelateNews");
             for(news obj : RelateNews){
             %>
                <li>
                    <h4>
                        <a class="title"  href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>"><%=obj.getName()%></a>
                    </h4>
                </li>
			<%}%>              
            </ul>
        </div>
        <br>

        
        <footer>
            <div class="container py-3 bg-dark">
                <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
            </div>
        </footer>
    </div>
    <script type="text/javascript">
		function comment(){
			var id = <%=News.getId()%>;
			var cmt = $("#cmt").val();
			$.ajax({
				url : "<%=request.getContextPath()%>/detail",
				type : "POST",
				cache : false,
				data : {
						cmt : cmt,
						id :id
				},
				success : function(data){
					$(".binhluan").html(data)
				},
				error : function(){
					alert("Có lỗi xảy ra")
				}
			})	;
			return false;
		}
	</script>
</body>

</html>