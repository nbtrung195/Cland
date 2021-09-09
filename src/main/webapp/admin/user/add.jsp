<%@page import="model.bean.role"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Thêm người dùng</h2>
                    <%
                    String name = request.getParameter("name");
                    String password = request.getParameter("password");
                    String repassword = request.getParameter("repassword");
                    String email = request.getParameter("email");
                    String date =  request.getParameter("date");
                    String err = request.getParameter("err");
                    if("1".equals(err)){out.print("<p style=\"color: red\">Thêm thất bại</p>");}
                    if("2".equals(err)){out.print("<p style=\"color: red\">Tài khoản này đã tồn tại</p>");}
                    ArrayList<role> ListItems = ( ArrayList<role>)request.getAttribute("ListItems");
                    
                    %>
                    <form action="" method="post" id="myform"> 
                      <div class="form-group">
                        <label for="name">Tên tài khoản</label>
                        <input type="text" class="form-control" id="name"  value="<%if(name != null){out.print(name);}%>" placeholder="Nhập tên tài khoản" name="name">
                      </div>
                      <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" name="password" id="password"   value="<%if(password != null){out.print(password);}%>"  class="form-control" placeholder="Nhập mật khẩu">
                      </div>
                      <div class="form-group">
                        <label for="repassword">Nhập lại Mật khẩu</label>
                        <input type="password" name="repassword" id="repassword"  value="<%if(repassword != null){out.print(repassword);}%>"  class="form-control" placeholder="Nhập lại mật khẩu">
                      </div>
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text"  class="form-control" id="email"  value="<%if(email != null){out.print(email);}%>"  placeholder="Nhập email" name="email">
                      </div>
                      <div class="form-group ">
                    <label for="date">Ngày sinh</label>
                    <input type="date" name="date"   value="<%if(date != null){out.print(date);}%>"  class="form-control" id="date">
                </div>
                      <div class="form-group">
                        <label for="role">Phân quyền</label>
                        <select name="role" id="role">
                        <%
                        if(ListItems != null){
                        for(role obj : ListItems){
                        %>
                            <option value="<%=obj.getId()%>"><%=obj.getName()%></option>
						<%}} %>           
                        </select>
                      </div>
                      <button type="submit" class="btn btn-primary">Thêm người dùng</button>
                    </form>
                  </div>
            </div>
        </div>
        <script type="text/javascript">
          document.getElementById("user").classList.add('active');
		</script>
		<script>
$( "#myform" ).validate({
  rules: {
    name: {
      required: true
    },
    password: {
       required: true,
       minlength: 6,
       maxlength: 15
    },
    repassword: {
         required: true,
         equalTo: "#password"
    },
    email: {
         required: true,
         email: true
    },
    date: {
        required: true,
        date: true
   }
 },
 messages: {
	    name: {
	      required: "Nhập tên tài khoản"
	    },
	    password: {
	       required: "Nhập mật khẩu",
	       minlength: "Mật khẩu tối thiểu 6 ký tự",
	       maxlength: "Mật khẩu tối đa 15 ký tự"
	    },
	    repassword: {
	         required:  "Nhập lại mật khẩu",
	         equalTo: "Mật khẩu không khớp"
	    },
	    email: {
	         required: "Nhập Email",
	         email: "Email không phù hợp"
	    },
	    date: {
	        required: "Nhập ngày sinh",
	        date: "Ngày sinh không phù hợp"
	   }
	 }
});
</script>
<style>
	.error{color:red}
</style>
</body>
</html>