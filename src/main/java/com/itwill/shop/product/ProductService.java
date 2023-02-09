package com.itwill.shop.product;

import java.util.List;

public class ProductService {
	private ProductDao productDao;
	public ProductService() throws Exception {
		productDao = new ProductDao();
	}
	/*
	 * 전체 상품 보기
	 */
	public List<Product> productList() throws Exception{
		return productDao.selectAll();
	}
	
	/*
	 * 상품 상세 보기
	 */
	public Product productDetail(int p_no) throws Exception {
		return productDao.selectByNo(p_no);
	}
	/*
	 * 카테고리별 상품 보기
	 */
	public List<Product> productCategory(int category_no) throws Exception {
		return productDao.selectByCategory(category_no);
	}
	/*
	 * 상품 정보 수정
	 */
	public int productUpdate(Product product) throws Exception {
		return productDao.update(product);
	}
	/*
	 *  상품 정보 삭제
	 */
	 public int productDelete(String P_name) throws Exception{
		 return productDao.delete(P_name);
	 }
	 /*
	  * 상품 정보 추가
	  */
	 public int productInsert(Product product) throws Exception{
		 return productDao.insert(product);
	 }
	 /*
	  * 
	  */
	 public void updateClickCount(int p_click_count) throws Exception{
		productDao.increaseClickCount(p_click_count);
	}

}
