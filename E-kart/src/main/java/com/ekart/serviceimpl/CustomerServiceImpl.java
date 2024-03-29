package com.ekart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.exception.CustomerException;
import com.ekart.model.Cart;
import com.ekart.model.CurrentUserSession;
import com.ekart.model.Customer;
import com.ekart.model.Order;
import com.ekart.repository.CartRepo;
import com.ekart.repository.CustomerRepo;
import com.ekart.repository.UserSessionRepo;
import com.ekart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private UserSessionRepo usRepo;
	
	@Autowired
	private CartRepo cartR;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Customer saved=cr.save(customer);
		Cart cart=new Cart();
		cart.setCustomer(saved);
		cartR.save(cart);
		customer.setCart(cart);
		return saved;
		
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerException {
		Optional<Customer> c=cr.findById(customerId);
		
		if(c.isPresent()) {
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customer.getCustomerId())
			throw new CustomerException("invalid customer detail, please login first");
		
		Optional<Customer> c=cr.findById(customer.getCustomerId());
		if(c.isPresent()) {
			return cr.save(customer);
		}
		throw new CustomerException("user not found with id : "+customer.getCustomerId());
	}

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id please fill valid id");
		
		
		Optional<Customer> c=cr.findById(customerId);
		if(c.isPresent()) {
			cr.delete(c.get());
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public List<Order> viewOrders(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id please fill valid id");
		
		Optional<Customer> c=cr.findById(customerId);
		if(c.isPresent()) {
			List<Order>list=c.get().getOrders();
			if(list.size()==0) {
				throw new CustomerException("order list is empty");
			}
			return list;
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

}
