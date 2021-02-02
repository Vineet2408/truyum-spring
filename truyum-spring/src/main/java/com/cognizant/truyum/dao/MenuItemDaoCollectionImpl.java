package com.cognizant.truyum.dao;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
@Component("menuItemDao")
public class MenuItemDaoCollectionImpl implements MenuItemDao
{

private  List<MenuItem>menuItemList;
	
	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return this.menuItemList ;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemListCustomer=new ArrayList<>();
		Date date =new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String ds=simpleDateFormat.format(date);
		Date ds1=DateUtil.convertToDate(ds);
		
		List<MenuItem> list=this.menuItemList;
		for(MenuItem m:list)
		{
			Date d1=m.getDateOfLaunch();
			if((d1.before(ds1)||d1.equals(ds1))&& m.isActive())
			{
				menuItemListCustomer.add(m);
			}
		}
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) 
	{
		// TODO Auto-generated method stub
		List<MenuItem> list=this.menuItemList;
		for(MenuItem m:list)
		{
			if(m.getId()==menuItem.getId())
			{
				m.setName(menuItem.getName());
				m.setPrice(menuItem.getPrice());
				m.setCategory(menuItem.getCategory());
				m.setDateOfLaunch(menuItem.getDateOfLaunch());
				m.setFreeDelivery(menuItem.isFreeDelivery());
				m.setActive(menuItem.isActive());
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) 
	{
		// TODO Auto-generated method stub
		List<MenuItem> list=this.menuItemList;
		for(MenuItem m:list)
		{
			if(m.getId()==menuItemId)
			{
				return m;
			}
		}
		return null;
		
	}

}
