<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itwill.shop.order.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.order.OrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="login_check.jspf"%>
<%
OrderService orderService = new OrderService();
List<Order> orderList = orderService.findByUserId(sUserId);

NumberFormat numberFormat = NumberFormat.getInstance();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>쇼핑몰 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
 
<style type="text/css" media="screen">
</style>
<script type="text/javascript">
	function order_delete_all_action(){
		document.order_delete_all_form.action='order_delete_all_action.jsp';
		document.order_delete_all_form.method='POST';
		document.order_delete_all_form.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
<form name="order_delete_all_form" style="margin: 0">
</form>	
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
		
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp"></jsp:include>
			<!-- include_common_top.jsp end-->

		</div>
		<!-- header end -->
		<!-- navigation start-->
		
			<!-- include_common_left.jsp start-->
			<!-- include_common_left.jsp end-->
			
		<!-- navigation end-->
		<!-- wrapper start -->
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>주문 목록</b></td>
								</tr>
							</table> <!--form-->
							<form name="f" method="post">
								<table align=center width=80%  border="0" cellpadding="0"
									cellspacing="1" bgcolor="BBBBBB">
									<tr>
										<td width=145 height=25 bgcolor="E6ECDE" align=center class=t1><font>주문 번호</font></td>
										<td width=145 height=25 bgcolor="E6ECDE" align=center class=t1><font>주문 이름</font></td>
										<td width=112 height=25 bgcolor="E6ECDE" align=center class=t1><font>주문 날짜</font></td>
										<td width=136 height=25 bgcolor="E6ECDE" align=center class=t1><font>주문 가격</font></td>
										<td width=80 height=25 bgcolor="E6ECDE" align=center class=t1><font>주문 상세</font></td>
									</tr>

									<!-- order start -->
									
									<%
									for(Order order : orderList) { 
									String price_format = numberFormat.format(order.getO_price());
									/*	
									String o_desc = null;
									if(orderList.size() < 2) {
										o_desc =  order.getOrderItemList().get(0).getProduct().getP_name();
									} else {
										o_desc = order.getOrderItemList().get(0).getProduct().getP_name() + "외" + order.getOrderItemList().get(0) + "건";
									}
									*/
									%>
									<tr>
										<td width=145 height=26 align=center bgcolor="ffffff" class=t1><%= order.getO_no() %></td>
										<td width=145 height=26 align=center bgcolor="ffffff" class=t1><%= order.getO_desc() %></td>
										<td width=110 height=26 align=center bgcolor="ffffff" class=t1><%=new SimpleDateFormat("yyyy/MM/dd").format(order.getO_date()) %></td>
										<td width=130 height=26 align=center bgcolor="ffffff" class=t1><%= price_format %>원</td>
										<td width=80 height=26 align=center bgcolor="ffffff" class=t1>
										<a href="order_detail.jsp?o_no=<%= order.getO_no() %>" class=m1>주문상세</a></td>
									</tr>
									<% } %>
									
									<!-- order end -->




								</table>
							</form> <br />
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center>&nbsp;&nbsp;
									<a href=product_list.jsp
										class=m1>계속 구경하기</a>
									<a href='javascript:order_delete_all_action();'
										class=m1>주문전체삭제</a>
									</td>
									
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp"/>
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>