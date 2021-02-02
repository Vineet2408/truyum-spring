package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

@Component("cartDao")
public class CartDaoCollectionImpl implements CartDao {

	private  HashMap<Long, Cart>userCarts;
	
	public HashMap<Long,Cart> getUserCarts() {
		return this.userCarts;
	}

	public  void setUserCarts(HashMap<Long, Cart> userCarts) {
		this.userCarts = userCarts;
	}


	@SuppressWarnings("null")
	@Override
	public void addCartItem(long userId, long menuItemId) {
		Cart cart=this.userCarts.get(userId);
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		
		if(cart==null)
		{	
			List<MenuItem> list=new ArrayList<>();
					list.add(menuItemDao.getMenuItem(menuItemId));
			double total=menuItemDao.getMenuItem(menuItemId).getPrice();
			cart=new Cart(list,total);
			
			this.userCarts.put(userId,cart);
		}
		else
		{
			List<MenuItem> list=cart.getMenuItemList();
			list.add(menuItemDao.getMenuItem(menuItemId));
			double total=cart.getTotal()+menuItemDao.getMenuItem(menuItemId).getPrice();
			cart.setTotal(total);
			cart.setMenuItemList(list);
			this.userCarts.put(userId, cart);
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		
		if(this.userCarts.isEmpty())
		{
			throw new CartEmptyException("Empty List found exception");
		}
		return this.userCarts.get(userId).getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId)
	{
		Cart cart=this.userCarts.get(userId);
		if(cart== null)
		{
			try {
				throw new CartEmptyException("No user found");
			} catch (CartEmptyException e) {
				System.out.println(e.toString());
			}
		}
		List<MenuItem> list=cart.getMenuItemList();
		int i=0;
		for(MenuItem m:list)
		{
			if(m.getId()== menuItemId)
			{
				double total=cart.getTotal()-(m.getPrice());
				cart.setTotal(total);
				list.remove(i);
				break;
			}
			i++;
		}
		cart.setMenuItemList(list);
		this.userCarts.put(userId, cart);	
		
	}

}
