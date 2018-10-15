package com.shoppingcart.shoppingservice.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.shoppingcart.shoppingservice.entities.ShoppingCart;
import com.shoppingcart.shoppingservice.entities.ShoppingCartItem;
import com.shoppingcart.shoppingservice.repository.ShoppingCartRepository;
import com.shoppingcart.shoppingservice.support.entities.ShoppingCartResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		ShoppingCartResponse shoppingCartRes = null ;
		ShoppingCart shoppingCart = null;
		if (StringUtils.isNotBlank(id)) {
			shoppingCart = shoppingCartRepository.findByCustomer(Long.valueOf(id));
			shoppingCartRes = parseShoppingCartResp(shoppingCart);
		}
		
		if (shoppingCartRes != null && shoppingCartRes.isObsolete()) {
			shoppingCartRepository.delete(shoppingCart);
			return null;
		} else {
			return shoppingCartRes;
		}
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
	
	
	
//	    public ShoppingCartData getShoppingCartData( final String customer, final int merchantId,
//	                                                 final String shoppingCartId, Language language)
//	        throws Exception
//	    {
//
//	        ShoppingCartResponse cart = null;
//	        try
//	        {
//	            if ( customer != null )
//	            {
//
//	                cart = getShoppingcartByCustomer(customer);
//
//	            }
//
//	            else
//	            {
//	                if ( StringUtils.isNotBlank( shoppingCartId ) && cart == null )
//	                {
//	                    cart = getShoppingcartByCode(merchantId, shoppingCartId  );
//	                }
//
//	            }
//	        }
//	        catch ( ServiceException ex )
//	        {
////	            log.error( "Error while retriving cart from customer", ex );
//	        }
//	        catch( NoResultException nre) {
//	        	//nothing
//	        }
//
//	        if ( cart == null )
//	        {
//	            return null;
//	        }
//
////	        log.info( "Cart model found." );
//
////	        ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
////	        shoppingCartDataPopulator.setShoppingCartCalculationService( shoppingCartCalculationService );
////	        shoppingCartDataPopulator.setPricingService( pricingService );
////	        shoppingCartDataPopulator.setimageUtils(imageUtils);
////
////	        //Language language = (Language) getKeyValue( Constants.LANGUAGE );
////	        MerchantStore merchantStore = (MerchantStore) getKeyValue( Constants.MERCHANT_STORE );
////	        
////	        ShoppingCartData shoppingCartData = shoppingCartDataPopulator.populate( cart, merchantStore, language );
//	        
//	/*        List<ShoppingCartItem> unavailables = new ArrayList<ShoppingCartItem>();
//	        List<ShoppingCartItem> availables = new ArrayList<ShoppingCartItem>();
//	        //Take out items no more available
//	        List<ShoppingCartItem> items = shoppingCartData.getShoppingCartItems();
//	        for(ShoppingCartItem item : items) {
//	        	String code = item.getProductCode();
//	        	Product p =productService.getByCode(code, language);
//	        	if(!p.isAvailable()) {
//	        		unavailables.add(item);
//	        	} else {
//	        		availables.add(item);
//	        	}
//	        	
//	        }
//	        shoppingCartData.setShoppingCartItems(availables);
//	        shoppingCartData.setUnavailables(unavailables);*/
//	        
//	        return shoppingCartData;
//
//	    }
	
	
	
	

}
