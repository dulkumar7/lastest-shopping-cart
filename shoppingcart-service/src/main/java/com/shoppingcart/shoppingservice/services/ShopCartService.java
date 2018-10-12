package com.shoppingcart.shoppingservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.model.customer.Customer;
import com.shoppingcart.shoppingservice.entities.ShoppingCart;
import com.shoppingcart.shoppingservice.entities.ShoppingCartItem;
import com.shoppingcart.shoppingservice.repository.ShoppingCartRepository;
import com.shoppingcart.shoppingservice.support.entities.ShoppingCartResponse;

@Service
public class ShopCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartResponse getById(int merchantId, String id) {
		if (StringUtils.isNotBlank(id)) {
			return parseShoppingCartResp(shoppingCartRepository.findById(merchantId, Long.valueOf(id)));
		}
		return null;
	}

	public ShoppingCartResponse getShoppingcartByCustomer(String id) {
		if (StringUtils.isNotBlank(id)) {
			return parseShoppingCartResp(shoppingCartRepository.findByCustomer(Long.valueOf(id)));
		}
		return null;
	}

	public ShoppingCartResponse getShoppingcartByCode(String code) {
		return parseShoppingCartResp(shoppingCartRepository.findByCode(code));
	}

	public ShoppingCartResponse getShoppingcartByOne(String id) {
		if (StringUtils.isNotBlank(id)) {
			return parseShoppingCartResp(shoppingCartRepository.findOne(Long.valueOf(id)));
		}

		return null;
	}

	public ShoppingCartResponse getShoppingcartByCode(int id, String code) {
		return parseShoppingCartResp(shoppingCartRepository.findByCode(id, code));
	}

	private ShoppingCartResponse parseShoppingCartResp(ShoppingCart cart) {
		ShoppingCartResponse response = null;
		Set<ShoppingCartItem> lineItems = new HashSet<ShoppingCartItem>();
		if (null != cart) {
			response = new ShoppingCartResponse();
			response.setId(cart.getId());

			response.setAuditSection(cart.getAuditSection());
			response.setCustomerId(cart.getCustomerId());
			lineItems = cart.getLineItems();
			response.setLineItems(lineItems);
			response.setMerchantStore(cart.getMerchantStore());
			response.setShoppingCartCode(cart.getShoppingCartCode());
		}
		return response;
	}

}
