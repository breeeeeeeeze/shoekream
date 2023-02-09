package com.itwill.shop.product;

public class ProductSQL {
	public final static String PRODUCT_INSERT = "insert into product values(?,?,?,?,?,?,?)";
	public final static String PRODUCT_UPDATE = "update product set p_name=?,p_price=?,p_image=?,p_desc=?,P_click_count=?,category_no=?where p_no =?";
	public final static String PRODUCT_DELETE = "delete from product where p_name = ?";
	public final static String PRODUCT_SELECT_BY_NO = "select * from product where p_no = ?";
	public final static String PRODUCT_SELECT_ALL = "select * from product";
	public final static String PRODUCT_SELECT_BY_CATEGORY = "select * from product where category_no=?";
	public final static String PRODUCT_INCREASE_CLICKCOUNT = "UPDATE product SET p_click_count = p_click_count + 1 WHERE product_no = ?";
	public final static String PRODUCT_COUNT_SELECT = "select count(*) cnt from product";

}
