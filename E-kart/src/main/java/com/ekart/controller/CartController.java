package com.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.exception.CartException;
import com.ekart.exception.ProductException;
import com.ekart.model.Cart;
import com.ekart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cs;
	
	@GetMapping("/view/{uuidKey}")
	public ResponseEntity<Cart> viewCart(@PathVariable("uuidKey") String key) throws CartException {
		Cart c=cs.cartByCustomerId(key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/addItemIntoCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> addItemIntoCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.addItemIntoCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/removeItemFromCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.removeItemFromCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/increaseQuantity/{productId}/{quantity}/{uuidKey}")
	public ResponseEntity<Cart> increaseQuantity(@PathVariable("productId") int productId, @PathVariable("quantity") int quantity, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.increaseQuantity(productId, quantity,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/decreaseQuantity/{productId}/{quantity}/{uuidKey}")
	public ResponseEntity<Cart> decreaseQuantity(@PathVariable("productId") int productId, @PathVariable("quantity") int quantity, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.decreaseQuantity(productId, quantity,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/clearCart/{uuidKey}")
	public ResponseEntity<Cart> clearCart(@PathVariable("uuidKey") String key) throws CartException {
		Cart c=cs.clearCart(key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}
}
