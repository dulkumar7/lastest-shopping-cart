package com.shoppingcart.shoppingservice.requests;

import org.hibernate.validator.constraints.NotBlank;


public class CartRequest {
	
	private int cartId;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


}
