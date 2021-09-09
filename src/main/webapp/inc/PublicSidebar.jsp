<%@page import="model.bean.news"%>
<%@page import="model.dao.newsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="col-4">
                    <div class="row">
                        <h3 style="padding-left: 40px;">Những tin tức mới nhất</h3>
                        <div class="list-group">
                        <%
                        newsDAO NewsDAO = new newsDAO();
                        ArrayList<news> ListLastestNews = NewsDAO.getItems(0, 5);
                        for(news obj : ListLastestNews){
                        %>
                            <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>" class="list-group-item list-group-item-action"><%=obj.getName()%></a>
                            <%}%>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <h3 style="padding-left: 40px;">Tin tức xem nhiều nhất</h3>
                        <div class="list-group">
                        <%
                        ArrayList<news> trendding = NewsDAO.getTrendding();
                        for(news obj : trendding){
                        %>
                            <a href="<%=request.getContextPath()%>/detail?id=<%=obj.getId()%>" class="list-group-item list-group-item-action"><%=obj.getName()%></a>
                           <%}%>
                        </div>
                    </div>
                </div>