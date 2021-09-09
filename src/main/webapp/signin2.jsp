<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Signin Template · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/sign-in/">
    <link rel="stylesheet" href="./css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/signin.css">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
  </head>
  <body class="text-center" style="background-color: aquamarine;">
    
<form action="" method="post" class="form-signin border" style="background-color: azure;">
  <img class="mb-4" src="./picture/itvinaentersite.png" alt="" width="300" height="72">
  <h1 class="h3 mb-3 font-weight-normal">Đăng nhập người đọc</h1>
  <%
  String err = request.getParameter("err");
  if("1".equals(err)){
	  out.print("<p style=\"color:red\">Sai tên đăng nhập hoặc mật khẩu</p>");
  }
  %>
  <label for="inputUsername" class="sr-only">Username</label>
  <input type="text"  name="inputUsername"  id="inputUsername" class="form-control" placeholder="Username"  autofocus>
  <br>
  <label for="inputPassword" class="sr-only">Password</label>
  <input type="password"  name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" >
  <div class="checkbox mb-3">
    <label>
      <a href="<%=request.getContextPath()%>/home">Tiếp tục đọc báo</a>
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Đăng nhập</button>
  <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
</form>   
  </body>
</html>
