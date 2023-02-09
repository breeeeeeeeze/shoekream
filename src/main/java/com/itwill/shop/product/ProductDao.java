package com.itwill.shop.product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class ProductDao {
	private DataSource dataSource;
	public ProductDao() throws Exception {
			Properties properties = new Properties();
			properties.load(this.getClass().getResourceAsStream("/jdbc.properties"));
			/*** Apache DataSource ***/
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(properties.getProperty("driverClassName"));
			basicDataSource.setUrl(properties.getProperty("url"));
			basicDataSource.setUsername(properties.getProperty("username"));
			basicDataSource.setPassword(properties.getProperty("password"));
			dataSource = basicDataSource;
	}
	/*
	 * 카테고리별 상품 검색
	 */
	public List<Product> selectByCategory(int category_no) throws Exception {
		List<Product> productList=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY);
			pstmt.setInt(1, category_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getInt("p_no"), rs.getString("p_name"), rs.getInt("p_price"), 
						  			rs.getString("p_image"), rs.getString("p_desc"), 
						  			rs.getInt("p_click_count"), rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 상품 추가
	 */
	public int insert(Product product) throws Exception {
		Connection con = null;
		PreparedStatement pstmt =null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt= con.prepareStatement(ProductSQL.PRODUCT_INSERT);
			pstmt.setInt(1, product.getP_no());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setString(4, product.getP_image());
			pstmt.setString(5, product.getP_desc());
			pstmt.setInt(6, product.getP_click_count());
			pstmt.setInt(7, product.getCategory_no());
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	/*
	 * 상품 update
	 */
	public int update(Product product) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt= con.prepareStatement(ProductSQL.PRODUCT_UPDATE);
			pstmt.setString(1, product.getP_name());
			pstmt.setInt(2, product.getP_price());
			pstmt.setString(3, product.getP_image());
			pstmt.setString(4, product.getP_desc());
			pstmt.setInt(5, product.getP_click_count());
			pstmt.setInt(6, product.getCategory_no());
			pstmt.setInt(7,product.getP_no());
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	/*
	 * 상품 이름으로 삭제
	 */
	public int delete(String p_name )throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_DELETE);
			pstmt.setString(1, p_name);
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return rowCount;
	}
	/*
	 * 상품 번호 검색
	 */
	public Product selectByNo(int p_no) throws Exception{
		Product product=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
			pstmt.setInt(1, p_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product = new Product(rs.getInt("p_no"),
									  rs.getString("p_name"),
									  rs.getInt("P_price"),
									  rs.getString("p_image"),
									  rs.getString("p_desc"),
									  rs.getInt("p_click_count"),
									  rs.getInt("category_no"));
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return product;
	}
	/*
	 * 상품 전체 검색
	 */
	public List<Product> selectAll() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 상품 조회수를 1 증가.
	 */
	public void increaseClickCount(int number) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ProductSQL.PRODUCT_INCREASE_CLICK_COUNT);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
	}

	/*
	 * 상품 총 건수를 조회, 반환
	 */
	public int getProductCount() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ProductSQL.PRODUCT_COUNT_SELECT);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
		return count;
	}
	/*
	 * 가격 내림차순
	 */
	public List<Product> priceSortDesc() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_DESC_PRICE);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 가격 오름차순
	 */
	public List<Product> priceSortAsc() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_ASC_PRICE);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 조회수 내림차순
	 */
	public List<Product> clickSortDesc() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_DESC_CLICK);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 조회수 오름차순
	 */
	public List<Product> clickSortAsc() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_ASC_CLICK);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
}
