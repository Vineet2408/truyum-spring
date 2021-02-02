package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;
@Service
public class MenuItemService 
{
	@Autowired
	@Qualifier("menuItemDao")
	private MenuItemDao menuItemDao;
	
	public void setMenuItemDao(MenuItemDao menuItemDao) {
		// TODO Auto-generated method stub
		this.menuItemDao=menuItemDao;
		
	}
	public void editMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}

	
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
