package com.cognizant.truyum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

public class CartService 
{
	@Autowired
	@Qualifier("cartDao")
	private CartDao cartDao; 
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException
	{
		List<MenuItem> list =new ArrayList<MenuItem>();
		
		return list;
	}
	public void setCartDao(CartDao cartDao )
	{
		this.cartDao=cartDao;
	}
	public void addCartItem(long userId ,long menuItemId)
	{
		
	}
	public void removeCartItem(long userId,long menuItemId)
	{
		
	}

}
