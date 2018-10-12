package com.shoppingcart.shoppingservice.restcontrollers;

import java.util.List;

import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.salesmanager.core.model.customer.Customer;
import com.shoppingcart.shoppingservice.entities.ShoppingCart;
import com.shoppingcart.shoppingservice.services.ShopCartService;
import com.shoppingcart.shoppingservice.support.entities.ShoppingCartResponse;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController("/shoppingcart")
public class ShoppingcartRestController {
	
	@Autowired 
	ObjectMapper objectMapper;
	
	@Autowired
	private ShopCartService shopCartService;
	
	@RequestMapping(value="/shoppingcart/customer-id/{customerId}", method=RequestMethod.GET)
	public ShoppingCartResponse getShoppingcartByCustomer(@PathVariable(value ="customerId") String customerId) {
		return shopCartService.getShoppingcartByCustomer(customerId);
	}
	
	@RequestMapping(value="/shoppingcart/code/{code}", method=RequestMethod.GET)
	public ShoppingCartResponse getShoppingcartByCode(@PathVariable(value ="code") String code) {
		return shopCartService.getShoppingcartByCode(code);
	}
	@RequestMapping(value="/shoppingcart/merchant-id/{merchantId}/cart-id/{cartId}", method=RequestMethod.GET)
	public ShoppingCartResponse getById(@PathVariable(value ="merchantId") int merchantId, @PathVariable(value ="cartId") String cartId) {
		return shopCartService.getById(merchantId, cartId);
	}
	@RequestMapping(value="/shoppingcart/cart-id/{cartId}", method=RequestMethod.GET)
	public ShoppingCartResponse getShoppingcartByOne(@PathVariable(value ="cartId") String cartId) {
		return shopCartService.getShoppingcartByOne(cartId);
	}
	
	
	
	@RequestMapping(value="/shoppingcart/{id}/{code}", method=RequestMethod.GET)
	public ShoppingCartResponse getShoppingCartByCode(@PathVariable(value ="id") int id, @PathVariable(value ="code") String code) {
		return shopCartService.getShoppingcartByCode(id, code);
	}
	
	

	
	
}