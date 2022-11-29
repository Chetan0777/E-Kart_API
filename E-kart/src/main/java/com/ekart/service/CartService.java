package com.ekart.service;

import com.ekart.exception.CartException;
import com.ekart.exception.CustomerException;
import com.ekart.exception.ProductException;
import com.ekart.model.Cart;

public interface CartService {
	
	public Cart addCart(Cart cart,String key)throws CustomerException;
	
	public Cart viewCart(int cartId,String key)throws CartException;
	
	public Cart addItemIntoCart(int productId,String key)throws CartException,ProductException;
	
	public Cart removeItemFromCart(int productId,String key)throws CartException,ProductException;
	
	public Cart increaseQuantity(int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart decreaseQuantity(int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart clearCart(String key)throws CartException;
	
	public Cart deleteCart(int cartId,String key)throws CartException;
	
	public Cart cartByCustomerId(String key)throws CartException;
	
}
