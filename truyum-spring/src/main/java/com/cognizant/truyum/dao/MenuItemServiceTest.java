package com.cognizant.truyum.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;
import com.cognizant.truyum.util.DateUtil;
public class MenuItemServiceTest
{
	@Autowired
	private  MenuItemService service;
	public void test()
	{
		
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		testGetMenuItem();
	}
	
	
	public  void testGetMenuItemListAdmin()
	{
		List<MenuItem> menuItemList=this.service.getMenuItemListAdmin();
		System.out.println("Id \t Name \t Price \t Active \t Category \t Date Of Launch \t Free Delivery ");
		for(MenuItem m:menuItemList)
		{
			System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getPrice()+"\t"+m.isActive()+"\t"
		+m.getCategory()+"\t"+m.getDateOfLaunch()+"\t"+m.isFreeDelivery());
		}
	}
	
	public  void testGetMenuItemListCustomer()
	{
		List<MenuItem> list=this.service.getMenuItemListCustomer();
		System.out.println("Id \t Name \t Price \t Category \t Date Of Launch \\t Free Delivery ");
		
		for(MenuItem menuItem: list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+menuItem.getDateOfLaunch());
		}
		
	}
	public  void testModifyMenuItem()
	{
		MenuItem menuItem=new MenuItem(1,"Sandwich", (float) 199.0, true, DateUtil.convertToDate("16/07/2018"), "Main Course", true);
		this.service.modifyMenuItem(menuItem);
		List<MenuItem> list=this.service.getMenuItemListCustomer();
		System.out.println();
		System.out.println("Data after modification");
		System.out.println();
		for(MenuItem menuItems: list)
		{
			System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
			+" "+menuItems.getDateOfLaunch());
		}
		
	}
	public  void testGetMenuItem()
	{
		MenuItem menuItems=this.service.getMenuItem(1);
		System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
		+" "+menuItems.getDateOfLaunch());
	}

}
