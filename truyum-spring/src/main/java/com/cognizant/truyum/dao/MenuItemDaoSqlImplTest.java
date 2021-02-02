package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

	public static void main(String[] args) {
			
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		testGetMenuItem();
		
	}
	
	public static void testGetMenuItemListAdmin()
	{
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		List<MenuItem> list=menuItemDaoSqlImpl.getMenuItemListAdmin();
		
		for(MenuItem menuItem:list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
		menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());
		}	
		
	}
	public static void testGetMenuItemListCustomer()
	{
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		List<MenuItem> list=menuItemDaoSqlImpl.getMenuItemListCustomer();
		
		for(MenuItem menuItem:list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
		menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());
		}
	}
	public static void testModifyMenuItem()
	{
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		
		MenuItem m=new MenuItem(2,"Pizza", (float) 189.0, true, DateUtil.convertToDate("23/12/2018"), "Main Course", false);
		menuItemDaoSqlImpl.editMenuItem(m);
		List<MenuItem> list=menuItemDaoSqlImpl.getMenuItemListCustomer();
		
		for(MenuItem menuItem:list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
		menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());
		}
		
	}
	public static void testGetMenuItem()
	{
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		MenuItem menuItem=menuItemDaoSqlImpl.getMenuItem(1);
		System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
				menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());	
	}

}
