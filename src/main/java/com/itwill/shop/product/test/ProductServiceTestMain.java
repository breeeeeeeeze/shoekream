package com.itwill.shop.product.test;

import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

public class ProductServiceTestMain {

	public static void main(String[] args) throws Exception {
		ProductService productService = new ProductService();
		/*
		 * 전체 상품 보기
		 */
		System.out.println(productService.productList());
		/*
		 * 상품 상세 보기
		 */
		System.out.println(productService.productDetail(1));
		/*
		 * 상품 정보 수정
		 */
		System.out.println(productService.productUpdate(new Product(1,"shoes2",2000000,"shoes1.jpg","기타등등등",0,1)));
		/*
		 * 상품 정보 삭제
		 */
		System.out.println(productService.productDelete("shoes22"));
		/*
		 * 상품 정보 추가
		 */
		System.out.println(productService.productInsert(new Product(10,"shoes2",2000000,"shoes1.jpg","기타등등등",0,1)));
//		/*
//		 * 상품 조회수 증가
//		 */
//		System.out.println(productService.updateClickCount(productService.productDetail(1).getP_click_count()));
	}

}
