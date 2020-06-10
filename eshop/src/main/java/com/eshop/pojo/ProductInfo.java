package com.eshop.pojo;

public class ProductInfo {
	private int id;
	private String name;
	private String brand;
	private String pic;
	private double price;
	private String intro;

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = "http://localhost:8080/eshop/product_images/" + pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
