package com.ekart.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.exception.CustomerException;
import com.ekart.model.Customer;
import com.ekart.model.Order;
import com.ekart.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer c=cs.registerCustomer(customer);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") int customerId) throws CustomerException {
		Customer c=cs.viewCustomer(customerId);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @RequestParam(required = false) String key ) throws CustomerException {
		Customer c=cs.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int customerId, @RequestParam(required = false) String key) throws CustomerException {
		Customer c=cs.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewOrders/{id}")
	public ResponseEntity<List<Order>> viewOrders(@PathVariable("id") int customerId, @RequestParam(required = false) String key) throws CustomerException {
		List<Order>list=cs.viewOrders(customerId, key);
		return new ResponseEntity<List<Order>>(list,HttpStatus.ACCEPTED);
	}

}
