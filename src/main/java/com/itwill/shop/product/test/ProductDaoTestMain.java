package com.itwill.shop.product.test;

import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductDao;

public class ProductDaoTestMain {

	public static void main(String[] args)throws Exception  {
		
		ProductDao productDao=new ProductDao();
		
		System.out.println(productDao.selectAll());
		
		System.out.println(productDao.insert(new Product(10,"shoes10",100000,"shoes10.jpg","기타등등",0,1)));

		System.out.println(productDao.selectByNo(1));
		
		System.out.println(productDao.update(new Product(10,"shoes22",2000000,"shoes11.jpg","기타등등등",0,2)));
		
		System.out.println(productDao.delete("shoes2"));
		
		System.out.println(productDao.selectByCategory(1));
		
		System.out.println(productDao.priceSortAsc());
		
		System.out.println(productDao.priceSortDesc());
		
		System.out.println(productDao.clickSortAsc());
		
		System.out.println(productDao.clickSortDesc());
		
	}

}
