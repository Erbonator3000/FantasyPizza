package com.eeroprittinen.fantasypizza;

public class Topping {

	private Integer menuImage;
	private Integer pizzaImage;
	private String name;
	private float extraPrice=0;
	
	
	public Topping(String name, Integer menuimage, Integer pizzaimage){
		this.name=name;
		this.menuImage=menuimage;
		this.pizzaImage=pizzaimage;
	};
	public Topping(String name, Integer menuimage){
		this.name=name;
		this.menuImage=menuimage;
		this.pizzaImage=R.drawable.ic_launcher;//generic pizza topping image or something
	};
	
	public String getName(){
		return this.name;
	}
	public Integer getImage(){
		return this.menuImage;
	}
	
	
	
}
