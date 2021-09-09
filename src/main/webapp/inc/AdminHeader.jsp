<%@page import="model.bean.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"></html>
    <head>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <!-- Latest compiled and minified CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4-4.6.0/dt-1.11.0/af-2.3.7/datatables.min.css"/>
	 

      <!-- jQuery library -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
      <!-- Popper JS -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="//cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
      <!-- Latest compiled JavaScript -->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="<%=request.getContextPath()%>/css/sidebars.js"></script>
      	<script type="text/javascript" src="https://cdn.datatables.net/v/bs4-4.6.0/dt-1.11.0/af-2.3.7/datatables.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
  
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
<body>
    <header class="bg-info" style="margin-bottom: 40px;">
        <img style="padding: 10px 10px;" width="400px" src="<%=request.getContextPath()%>/picture/itvinaentersite.png" alt="">
        <a name="" id="" class="btn btn-warning border-dark" style="float: right;;margin: 20px" href="<%=request.getContextPath()%>/logout" role="button">Đăng xuất</a>
        <%
        user User = (user)session.getAttribute("Login");
        if(User != null){
        %>
        <h5 style="float: right;margin: 25px;">Chào mừng <span style="color: aliceblue;"><%=User.getName()%>(<%=User.getRole().getName()%>)</span> đến với trang quản lý của chúng tôi</h5>
       <%}%>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <ul class="nav nav-pills flex-column mb-auto border">
                      <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/admin/index" class="nav-link text-black " aria-current="page" id="home">
                          <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="" />
                          </svg>
                          Trang chủ
                        </a>
                      </li>
                      <li>
                        <a href="<%=request.getContextPath()%>/admin/news" class="nav-link text-black " id="news">
                          <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                          </svg>
                          Quản lý Tin tức
                        </a>
                      </li>
                      <li>
                        <a href="<%=request.getContextPath()%>/admin/user" class="nav-link text-black " id="user">
                          <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                          </svg>
                          Quản lý người dùng
                        </a>
                      </li>
                      <li>
                        <a href="<%=request.getContextPath()%>/admin/category" class="nav-link text-black " id="category">
                          <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                          </svg>
                          Quản lý danh mục
                        </a>
                      </li>
                      <li>
                        <a href="<%=request.getContextPath()%>/admin/comment" class="nav-link text-black " id="comment">
                          <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                          </svg>
                          Bình Luận của dộc giả
                        </a>
                      </li>
                    </ul>
                  </div>