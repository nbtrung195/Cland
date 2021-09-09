<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/inc/PublicHeader.jsp" %>
        <br>
         <div class="container">
         <%String err = request.getParameter("err");%>
            <form id="myform" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="username" name="username" class="form-control" id="username" placeholder="Nhập Tên đăng nhập">
                    <%if("1".equals(err)){out.print("<p style=\"color:red\">Tên đăng nhập đã tồn tại</p>");}%>
                </div>
                <div class="form-group ">
                    <label for="password">Mật khẩu</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Nhập Mật khẩu">
                </div>
                <div class="form-group">
                    <label for="repassword">Nhập lại mật khẩu</label>
                    <input type="password" name="repassword" class="form-control" id="repassword" placeholder="Nhập lại mật khẩu">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" name="email" class="form-control" id="email" placeholder="Nhập Email">
                </div>
                <div class="form-group ">
                    <label for="date">Ngày sinh</label>
                    <input type="date" name="date" class="form-control" id="date">
                </div>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>
        </div>
        <br>
        <footer>
            <div class="container py-3 bg-dark">
                <h2 class="text-center text-light">Copyright 2021 © Vinaenter</h2>
            </div>
        </footer>
    </div>
    <script>
$( "#myform" ).validate({
  rules: {
	  username: {
      required: true,
    },
    password:{
    	required: true,
    	minlength: 6,
    	maxlength: 15
    },
    repassword:{
    	required: true,
    	equalTo: "#password"
    },
    email:{
    	required: true,
    	email: true
    },
    date:{
    	required: true,
        date: true
    }
  },
  messages: {
	  username: {
      required: "Nhập tên đăng nhập",
    },
    password:{
    	required: "Nhập mật khẩu",
    	minlength: "Mật khẩu tối thiểu 6 ký tự",
    	maxlength: "Mật khẩu tối đa 15 ký tự"
    },
    repassword:{
    	required: "Nhập lại mật khẩu",
    	equalTo: "Mật khẩu không khớp"
    },
    email:{
    	required: "Nhập Email",
    	email: "Email không hợp lệ"
    },
    date:{
    	required: "Nhập ngày sinh",
        date: "Ngày sinh không hợp lệ"
    }
  }
});
</script>
<style>.error{color:red}</style>
</body>

</html>