<%@page import="com.itwill.shop.product.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(request.getMethod().equalsIgnoreCase("GET")){
			response.sendRedirect("product_list.jsp");
			return;
		}
	
	String no = request.getParameter("highlow");
	if(Integer.parseInt(no)==1){
		
	}else if(Integer.parseInt(no)==2){
		
	}
	

	
	response.sendRedirect("product_list.jsp");
	
%>