package com.cognizant.truyum.model;

import java.util.Date;

public class MenuItem {
	
	public MenuItem()
	{
		
	}

	private long id;
	private String name;
	private float price;
	private boolean active;
	private Date dateOfLaunch;
	private String category;
	private boolean freeDelivery;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}
	public void setCategory(String category) {
		this.category = category;
	}
		public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	
	public String getName() {
		return name;
	}
	public float getPrice() {
		return price;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}
	public String getCategory() {
		return category;
	}


	public boolean isFreeDelivery() {
		return freeDelivery;
	}



	
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", price=" + price + ", active=" + active + ", dateOfLaunch="
				+ dateOfLaunch + ", category=" + category + ", freeDelivery=" + freeDelivery + "]";
	}

	public boolean equals(long id) {
		return (this.id==id ? true : false);
	}
	
	public MenuItem(long id, String name, float price, boolean active, Date dateOfLaunch, String category,
			boolean freeDelivery) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
		this.dateOfLaunch = dateOfLaunch;
		this.category = category;
		this.freeDelivery = freeDelivery;
	}
	

}
