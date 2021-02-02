package com.cognizant.truyum.dao;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionHandler {
	private static Connection conn=null;
	private static Properties props=new Properties();
	
	public static Connection getConnection()throws SQLException,FileNotFoundException,ClassNotFoundException,IOException
	{
		FileInputStream fis=new FileInputStream("src/connection.properties");
		props.load(fis);
		Class.forName(props.getProperty("driver"));
		conn=DriverManager.getConnection(props.getProperty("connection-url"),props.getProperty("user"),props.getProperty("password"));
		
		return conn;
	}
}
