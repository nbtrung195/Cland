<%@page import="java.sql.Date"%>
<%@page import="model.bean.user"%>
<%@page import="model.bean.role"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/AdminHeader.jsp" %>
                  <div class="container mt-3">
                    <h2>Sửa người dùng</h2>
                    <%
                    String err = request.getParameter("err");
                    if("1".equals(err)){
                    	out.print("<p style =\"color:red\">Sửa thất bại</p>");
                    }
                    String name = request.getParameter("name");
                    String password = request.getParameter("password");
                    String repassword = request.getParameter("repassword");
                    String email = request.getParameter("email");
                    String date =  request.getParameter("date");                 
                    String Role = request.getParameter("role");
                    user Item = (user)request.getAttribute("Item");
                    if(Item != null){
                    	name = Item.getName();
                    	email = Item.getEmail();                   	
                    	Date dateObj = Item.getBirth();
                        date = dateObj.toString();
                        Role = String.valueOf(Item.getRole().getId());
                    }
                    user who = (user)request.getAttribute("who");
                    ArrayList<role> ListItems = (ArrayList<role>)request.getAttribute("ListItems");
                    %>
                    <form action=""  id="myform" method="post" >
                      <div class="form-group">
                        <label for="name">Tên tài khoản</label>
                        <input type="text" class="form-control" id="name" value="<%if(name != null){out.print(name);}%>" placeholder="Nhập tên tài khoản" name="name"  readonly="readonly">
                      </div>
                      <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" name="password"  value="<%if(password != null){out.print(password);}%>" id="password" class="form-control" placeholder="Nhập mật khẩu">
                      </div>
                      <div class="form-group">
                        <label for="repassword">Nhập lại Mật khẩu</label>
                        <input type="password" name="repassword" value="<%if(repassword != null){out.print(repassword);}%>" id="repassword" class="form-control" placeholder="Nhập lại mật khẩu">
                      </div>
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" value="<%if(email != null){out.print(email);}%>" id="email" placeholder="Nhập email" name="email">
                      </div>
                      <div class="form-group ">
                    <label for="date">Ngày sinh</label>
                    <input type="date" name="date"  value="<%if(date != null){out.print(date);}%>" class="form-control" id="date" >
                </div>
                      <div class="form-group">
                        <label for="role">Phân quyền</label>
                        <%if(who.getRole().getId()==2||who.getRole().getId()==3){%>
                        <select name="role" id="role">
                        <%                   		
                    	 if(ListItems != null){
                    		 for(role obj : ListItems){
                    	 %>
                            <option <%if(Role.equals(String.valueOf(obj.getId()))){out.print("selected");}else{out.print("disabled=\"disabled\"");}%> value="<%=obj.getId()%>"><%=obj.getName()%></option>
                            
                        <%}}}else{%>
                         <select name="role" id="role" >
                         <%                  		
                    	 if(ListItems != null){
                    		 for(role obj : ListItems){
                    	 %>
                            <option <%if(Role.equals(String.valueOf(obj.getId()))){out.print("selected");}%> value="<%=obj.getId()%>"><%=obj.getName()%></option>
                            <%}}}%>
                        </select>
                      </div>
                      <button type="submit" class="btn btn-primary">Sửa người dùng</button>
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