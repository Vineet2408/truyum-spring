package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void main(String[] args) {
		
		testAddCartItem();
		 try {
			testGetAllCartItems();
		} catch (CartEmptyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		testRemoveCartItem(); 
			
		try {
			testGetAllCartItems();
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
	public static void testAddCartItem()
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		cartDaoSqlImpl.addCartItem(1, 2);
		cartDaoSqlImpl.addCartItem(1, 3);
		cartDaoSqlImpl.addCartItem(1, 4);
		cartDaoSqlImpl.addCartItem(1, 1);
		
	}
	public static void testGetAllCartItems() throws CartEmptyException
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
	
		List<MenuItem> list=cartDaoSqlImpl.getAllCartItems(1);
		for(MenuItem menuItem:list)
		{
			
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
		menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());
		}
		
	}
	public static void testRemoveCartItem()
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		cartDaoSqlImpl.removeCartItem(1,2);
	}

}
