package com.ekart.service;

import java.time.LocalDate;
import java.util.List;

import com.ekart.exception.CartException;
import com.ekart.exception.OrderException;
import com.ekart.model.Order;

public interface OrderService {
	
	public Order addOrder(Order order, int cartId)throws OrderException,CartException;
	
	public Order viewOrder(int orderId)throws OrderException;
	
	public List<Order> viewOrdersByDate(LocalDate startDate, LocalDate endDate)throws OrderException;
	
	public Order updateOrderStatus(int orderId, String status)throws OrderException;
	
	public Order deleteOrder(int orderId)throws OrderException;
	
}
