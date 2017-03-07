package com.eeroprittinen.fantasypizza;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Pizza {
	
	private ArrayList<Topping> toppings;
	
	public Pizza(){
		toppings=new ArrayList<Topping>();	
	}
	
	public void addTopping(Topping topping){
		toppings.add(topping);
	}
	public void removeTopping(Topping topping){
		toppings.remove(topping);
	}
	
	public void removeTopping(int position){
		if(!toppings.isEmpty()){
			toppings.remove(position);}
	}
	
	public int getToppingPosition(Topping topping){
		return this.toppings.indexOf(topping);
	}
	
	public Topping getTopping(int position){
		if( !(this.toppings==null) && !this.toppings.isEmpty() ){
			return this.toppings.get(position);
		}else return null;
	}
	
	public ArrayList<Topping> getToppings(){
		return this.toppings;
	}

	public ArrayList<String> getToppingsNames(){
		if( this.toppings!=null && !this.toppings.isEmpty() ){
		ArrayList<String> toppingNames = new ArrayList<String>();
		for(Topping value: toppings){
			toppingNames.add(value.getName());
		}
		return toppingNames;
		}else return null;
	}
	
	public ArrayList<Integer> getToppingsImages(){
		if( !(this.toppings==null) && !this.toppings.isEmpty() ){
		ArrayList<Integer> toppingImages = new ArrayList<Integer>();
		for(Topping value: toppings){
			toppingImages.add(value.getImage());
		}
		return toppingImages;
		}else return null;
	}
	
	public ArrayList<Drawable> getToppingsDrawables(Context c){
		
		ArrayList<Drawable> toppingDrawables = new ArrayList<Drawable>();
		toppingDrawables.add(c.getResources().getDrawable(R.drawable.pizza_bottom));
		try{
			for(Topping value: this.toppings){
			toppingDrawables.add(c.getResources().getDrawable(value.getImage()));
			}
		}catch(Exception e){			
		
		}
		
		return toppingDrawables;
	
	}
	
	
	
	
}
