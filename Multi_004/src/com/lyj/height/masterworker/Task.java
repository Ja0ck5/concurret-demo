package com.lyj.height.masterworker;

public class Task {
	
	private int id;
	private String name;
	private long price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
