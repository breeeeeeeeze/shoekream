<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sort_option = request.getParameter("sort_option");
	ProductService productService = new ProductService();
	List<Product> productList = new ArrayList<Product>();
	
	if(sort_option.equals("price_sort_asc")){
		productList = productService.priceSortAsc();
		response.sendRedirect("product_list2.jsp");
	}else if(sort_option.equals("price_sort_desc")){
		productList = productService.priceSortDesc();
		response.sendRedirect("product_list2.jsp");
	}
	/*************************************************/
	/*else if(sort_option.equals("click_sort_asc")){
		productList = productService.clickSortAsc();
		response.sendRedirect("product_list.jsp");
	}else if(sort_option.equals("click_sort_desc")){
		productList = productService.clickSortDesc();
		response.sendRedirect("product_list.jsp");
	}*/

	
%>