package com.ekart.service;

import java.util.List;

import com.ekart.exception.CustomerException;
import com.ekart.model.Customer;
import com.ekart.model.Order;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer)throws CustomerException;
	
	public Customer viewCustomer(int customerId)throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String key)throws CustomerException;
	
	public Customer deleteCustomer(int customerId, String key)throws CustomerException;
	
	public List<Order> viewOrders(int customerId, String key)throws CustomerException;

}
