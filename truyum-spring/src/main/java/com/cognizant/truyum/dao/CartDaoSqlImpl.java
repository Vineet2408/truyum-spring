package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
public class CartDaoSqlImpl 
{
	Connection conn;
	public CartDaoSqlImpl()
	{
		try {
			conn=ConnectionHandler.getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCartItem(long userId,long menuItemId)
	{
		
		MenuItemDao menuItemDao=new MenuItemDaoCollectionImpl();
		MenuItem menuItem=menuItemDao.getMenuItem(menuItemId);
		long product_id=menuItem.getId();
		String name=menuItem.getName();
		float price=menuItem.getPrice();
		String category=menuItem.getCategory();
		String free_delivery=(menuItem.isFreeDelivery())?"Yes":"No";
		String query="insert into cart (user_id,product_id,name,price,category,free_delivery) "
				+ "values(?,?,?,?,?,?)";
		
		try {
			Connection con=ConnectionHandler.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setLong(1, userId);
			ps.setLong(2, product_id);
			ps.setString(3,name);
			ps.setFloat(4, price);
			ps.setString(5, category);
			ps.setString(6,free_delivery);
			int r=ps.executeUpdate();
			if(r >0)
			{
				System.out.println("Insertion Successfully");
			}
			else
			{
				System.out.println("Insertion failed!");
			}
			con.close();
			
		} 
		catch (SQLException | ClassNotFoundException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public List<MenuItem> getAllCartItems(long userId)
	{
		List<MenuItem> list=new ArrayList<>();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Connection con=ConnectionHandler.getConnection();
			Cart cart=new Cart(list,0);
			String query="select m.product_id,m.name,m.price,m.active,m.date_of_launch,m.category,m.free_delivery from menu_item as m join cart as c on m.product_id=c.product_id where user_id=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setLong(1, userId);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				long id=(long)rs.getInt("product_id");
				String name=rs.getString("name");
				float price=rs.getFloat("price");
				String category=rs.getString("category");
				
				String a=rs.getString("active");
				boolean active=(a.equalsIgnoreCase("yes"))?true:false;
				
				String date_of_launch=sdf.format(rs.getDate("date_of_launch"));
				Date date=DateUtil.convertToDate(date_of_launch);
				
				String d=rs.getString("free_delivery");
				boolean freeDelivery=(d.equalsIgnoreCase("yes"))?true:false;
				
				MenuItem m=new MenuItem(id,name,price,active,date,category,freeDelivery);
				list.add(m);
			}
			query="select sum(c.price) as total from cart as c inner join menu_item as m on m.product_id=c.product_id where user_id=? group by user_id";
			ps=con.prepareStatement(query);
			ps.setLong(1, userId);
			rs=ps.executeQuery();
			 if(rs.next())
			 {
				double total=Double.parseDouble(rs.getString("total"));
				cart.setTotal(total);
				cart.setMenuItemList(list);
				System.out.println("Total Bill for user  "+userId+" is "+rs.getString("total"));
			 }
			 else
			 {
				 System.out.println("Cart is Empty");
			 }
			 con.close();
		}
		catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public void removeCartItem(long userId,long menuItemId)
	{
		
		String query="delete from cart where user_id=? and product_id=? limit 1";
		try {
			Connection con=ConnectionHandler.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setLong(1,userId);
			ps.setLong(2,menuItemId);
			
			int rs=ps.executeUpdate();
			if(rs >0)
			{
				System.out.println("Deleted Successfully");
			}
			else
			{
				System.out.println("Deletion failed!");
			}
			con.close();
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
