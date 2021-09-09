<!DOCTYPE html>
<%@page import="model.bean.user"%>
<%@page import="model.bean.category_child"%>
<%@page import="model.dao.category_childDAO"%>
<%@page import="model.dao.categoryDAO"%>
<%@page import="model.bean.category"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
    
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div class="wrapper">
        <div style="padding-top: 20px;" class="container">
            <div class="row">
                <div class="col-3">
                    <a href="<%=request.getContextPath()%>/home"><img width="200px" src="<%=request.getContextPath()%>/picture/itvinaentersite.png" alt=""></a>
                </div>
                <div class="col-9">
                	<%
                	user Login2 = (user)session.getAttribute("Login2");
                	if(Login2 != null){
                	%>
                    <h4>Chào mừng <%=Login2.getName()%> đến với trang tin tức chúng tôi, chúc một ngày vui vẻ</h4>
                    <%}else{%>
                    <h4>Chào mừng bạn đến với trang tin tức chúng tôi, chúc một ngày vui vẻ</h4>
                    <%}%>
                </div>
            </div>
        </div>
        <div class="sticky-top">
            <nav id="nav" class="container navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Trang chủ</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                    <%
                    categoryDAO CategoryDAO = new categoryDAO();
                    ArrayList<category> ListCategory = CategoryDAO.getItems();
                    for(category obj : ListCategory){
                    %>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <%=obj.getName()%>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <%
                            category_childDAO Cat_childDAO = new category_childDAO();
                            ArrayList<category_child> ListCatChild = Cat_childDAO.getItems(obj.getId());
                            for(category_child obk : ListCatChild){
                            %>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/category?id=<%=obk.getId()%>"><%=obk.getName()%></a>
                               <%}%>
                            </div>
                        </li>
                        <%}if(session.getAttribute("Login2") == null){%>                      
                        <li class="nav-item dropdown">
                            <a href="<%=request.getContextPath()%>/signinPublic" class="btn btn-warning">Đăng nhập</a>
                        </li>
                        <%}else{%>
                        <li class="nav-item dropdown">
                            <a href="<%=request.getContextPath()%>/logoutPublic" class="btn btn-warning">Đăng xuất</a>
                        </li>
                        <%}%>
                        <li style="padding-left: 10px;" class="nav-item">
                            <a href="<%=request.getContextPath()%>/createAcc" class="btn btn-primary">Đăng ký</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" method="post" action="<%=request.getContextPath()%>/search">
                    	<%String search = request.getParameter("search");%>
                        <input name="search" class="form-control mr-sm-2" value="<%if(search != null){out.print(search);}%>" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
        <div class="clearfix"></div>