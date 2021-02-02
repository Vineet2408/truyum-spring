package com.cognizant.truyum.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.cognizant.truyum.model.MenuItem;


public class MenuItemDaoSqlImpl
{
	static Connection conn;
	public MenuItemDaoSqlImpl()
	{
		try {
			conn=ConnectionHandler.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<MenuItem> getMenuItemListAdmin()
	{
		@SuppressWarnings("unused")
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

		List<MenuItem> list=new ArrayList<MenuItem>();
		String query="select * from menu_item";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(query);
			rs = ps.executeQuery();
		
		while(rs.next())
		{
			int id=rs.getInt("product_id");
			String name=rs.getString("name");
			float price=(float) rs.getDouble("price");
			boolean active=((rs.getString("active")).equalsIgnoreCase("yes"))?true:false;
			Date date_of_launch=new Date(rs.getDate("date_of_launch").getTime());
			String category=rs.getString("category");
			boolean freeDelivery=((rs.getString("free_delivery")).equalsIgnoreCase("yes"))?true:false;
			//Date date=(Date) DateUtil.convertToDate(date_of_launch);
			MenuItem m=new MenuItem((long)id,name,price,active,date_of_launch,category,freeDelivery);
			list.add(m);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<MenuItem> getMenuItemListCustomer()
	{
		List<MenuItem> list=new ArrayList<MenuItem>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String query="select * from menu_item where active=? and date_of_launch < curdate()";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(query);
		
		ps.setString(1,"Yes");
		
		rs=ps.executeQuery();
		while(rs.next())
		{
			int id=rs.getInt("product_id");
			String name=rs.getString("name");
			float price=(float) rs.getDouble("price");
			boolean active=((rs.getString("active")).equalsIgnoreCase("yes"))?true:false;
			Date date=new Date(rs.getDate("date_of_launch").getTime());
			String category=rs.getString("category");
			boolean freeDelivery=((rs.getString("free_delivery")).equalsIgnoreCase("yes"))?true:false;
			
			MenuItem m=new MenuItem((long)id,name,price,active,date,category,freeDelivery);
			list.add(m);
		} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public MenuItem getMenuItem(long menuItemId)
	{
		MenuItem item=null;
		String query="select * from menu_item where product_id=?";
		PreparedStatement ps;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		try {
			ps = conn.prepareStatement(query);
		
		ps.setLong(1,menuItemId);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int id=rs.getInt("product_id");
			String name=rs.getString("name");
			float price=(float) rs.getDouble("price");
			boolean active=((rs.getString("active")).equalsIgnoreCase("yes"))?true:false;
			Date date=new Date(rs.getDate("date_of_launch").getTime());
			String category=rs.getString("category");
			boolean freeDelivery=((rs.getString("free_delivery")).equalsIgnoreCase("yes"))?true:false;

			MenuItem m=new MenuItem((long)id,name,price,active,date,category,freeDelivery);
			item=m;
		
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	public void editMenuItem(MenuItem menuItem)
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Connection con=null;
		try {
			con = ConnectionHandler.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query="update menu_item set price=?,active=?,date_of_launch=?,category=?,free_delivery=?,name=? where product_id=?";
		
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, (int)menuItem.getPrice());
			preparedStatement.setString(2, menuItem.isActive()?"Yes":"No");
			java.sql.Date sqlDate = java.sql.Date.valueOf( simpleDateFormat.format(menuItem.getDateOfLaunch()) );
			preparedStatement.setDate(3,sqlDate);
			preparedStatement.setString(4, menuItem.getCategory());
			preparedStatement.setString(5, menuItem.isFreeDelivery()?"Yes":"No");
			preparedStatement.setString(6, menuItem.getName());
			preparedStatement.setLong(7, menuItem.getId());
			
			int rs=preparedStatement.executeUpdate();
			if(rs >0)
			{
				System.out.println("Data Updated Successfully");
			}
			else
			{
				System.out.println("Error Occured");
			}
		con.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}