<%@page import="com.itwill.shop.user.UserService"%>
<%@page import="com.itwill.shop.user.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String sUserId=(String)session.getAttribute("sUserId");
%>	
<jsp:include page="include_common_top_menu.jsp" />
<script type="text/javascript">
	function login_message() {
		alert('로그인하세요');
		location.href = 'user_login_form.jsp';
	}
</script>
<div id=top_navigation>
<h1>
<p>
	<%
		if(sUserId==null){
	%>
			<span><a href=""></a></span>
			<span><a href="javascript:login_message();">장바구니</a></span>
	<%
		}else{ 
			User sUser=new UserService().findUser(sUserId);
		    CartService cartService=new CartService();
		  	int cart_item_count = cartService.viewCartByUserId(sUserId).size();
	%>	
		<span><a href="user_view.jsp"><%=sUser.getUser_name()+"님"%></a></span>
		<span><a href="user_logout_action.jsp">로그아웃</a></span>
		<span></span>
		<span><a href=""></a></li>
		<span><a href="order_list.jsp">주문목록</a></li>
		
	<%} %>
		<span><a href="product_list.jsp?category_no=0">전체 상품</a></span>
		<span><a href="product_list.jsp?category_no=1">운동화</a></span>
		<span><a href="product_list.jsp?category_no=2">구두</a></span>
		<span><a href="product_list.jsp?category_no=3">부츠</a></span>
		<span><a href="product_list.jsp?category_no=4">슬리퍼</a></span>
		
		<span><a href=""></a></span>
		<span><a href="board_list.jsp">게시판리스트</a></span>
		<span><a href="board_write.jsp">게시판쓰기</a></span>
		
</p>
</h1>
</div>