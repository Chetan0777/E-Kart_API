package com.ekart.service;

import java.util.List;

import com.ekart.exception.CategoryException;
import com.ekart.exception.ProductException;
import com.ekart.model.Product;

public interface ProductService {
	
	public Product addProduct(Product product, int categoryId, String key)throws ProductException,CategoryException;
	
	public Product viewProduct(int productId)throws ProductException;
	
	public List<Product> allProduct()throws ProductException;
	
	public Product removeProduct(int productId, String key)throws ProductException;
	
	public Product updateProduct(Product product, String key)throws ProductException;
	
	public List<Product> productByName(String name)throws ProductException;
	
	public List<Product> productByNameLike(String name)throws ProductException;
	
	public List<Product> top5()throws ProductException;
	
}
