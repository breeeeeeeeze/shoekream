<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
boolean isLogin = false;
String sUserId=(String)session.getAttribute("s_u_id");
if (session.getAttribute("s_u_id") != null) {
	isLogin = true;
}
String category_noStr=request.getParameter("category_no");
if(category_noStr==null)category_noStr="0";

ProductService productService = new ProductService();
List<Product> productList = null;
if(category_noStr.equals("0")){
	productList=productService.productList();
}else{
	productList=productService.productCategory(Integer.parseInt(category_noStr));
}
%>

<!DOCTYPE html>
<html>
<head>
<title>쇼핑몰</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
<script type="text/javascript">
function add_cart_popup_window(f){
	if (<%=!isLogin%>) {
		alert('로그인 하세요');
		location.href = 'user_login_form.jsp';
	} else {
		let left = Math.ceil(( window.screen.width)/5);
		let top = Math.ceil(( window.screen.height)/3); 
		let cartWin = window.open("about:blank","cartForm","width=260,height=130,top="+top+",left="+left+",location=no, directories=no, status=no, menubar=no, scrollbars=no,copyhistory=no");
		f.action = 'cart_add_action_popup_window.jsp';
		f.target = 'cartForm';
		f.method = 'POST';
		f.submit();
	}
}

function product_sort(){
	document.product_alignment_action_form.method='POST';
	document.product_alignment_action_form.action='product_alignment_action.jsp';
	document.product_alignment_action_form.submit();
}
</script> 
<style type="text/css" media="screen">
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp" />
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
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
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>쇼핑몰 -
											상품리스트</b></td>
								</tr>
							</table>
							<form name = "product_alignment_action_form" action="product_alignment_action.jsp" method="get">
							<br><b>정렬</b>&nbsp;
							<select name="sort_option" onchange="product_alignment_action_form.submit();">
								<option value="select">선택
								<option value="price_sort_asc">가격 오름차순
								<option value="price_sort_desc">가격 내림차순
						   <!-- <option value="click_sort_asc">조회수 오름차순
								<option value="click_sort_desc">조회수 내림차순 -->
							</select> <br><br> 
							</form>
							<div id="f">
								<table width="100%" align="center" border="0" cellpadding="10"
									cellspacing="1" bgcolor="BBBBBB">
									<%
									int product_size=productList.size();
									int product_column_size=4;
									int product_line_count = 1;
									
									
									for (int i=0;i<productList.size();i++) {
											Product product=productList.get(i);
									%>
									<!--상품시작 -->
									<%
									 if(i%product_column_size==0){
									%>
									<tr>
									<%} %>
										<td align="center" width="25%"  bgcolor="ffffff">
										<a href="product_detail.jsp?p_no=<%=product.getP_no()%>">
										<img width="88px" height="65px" src="image/product_image/<%=product.getP_image()%>" border="0">
										</a><br />
												
											<br /> <b><%=product.getP_name()%></b>
											<form style="display: inline;">
												<input type="hidden" name="p_no" value="<%=product.getP_no()%>">
												<input type="hidden" name="cart_qty" value="1">
												<img src='image/cart20.png' style="cursor:pointer;" onclick="add_cart_popup_window(this.parentElement);" align="top"/>
											</form><br> <font
											color="#FF0000">가격:<%=new DecimalFormat("#,##0").format(product.getP_price())%>원
										</font></td>
									<%if(i%product_column_size==3){%>
									</tr>
									<%} %>	
									
								   <!--상품 끝 -->
								   <%}%>	
								</table>
							</div> <br /></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>