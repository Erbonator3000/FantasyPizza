package com.eeroprittinen.fantasypizza;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {


	//private Integer[] ToppingsImagesIds={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	//public ArrayList<String> apliedToppingsNames;
	//public String[] toppingsNames={"parsa","makkara","kinuski"};
	
	DeletableTextAdapter toppingsListAdapter;
	
	private Pizza pizza;
	private Pizza menu;
	
	private Topping draggedTopping;
	
	private ListView toppingsList;
	private GridView toppingsView;
	private ImageView pizzaBottom;
	
	private LayerDrawable pizzaDrawable;
	
	OnClickListener listDeleter;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pizza = new Pizza();
		menu = new Pizza();
		
		
		menu.addTopping(new Topping("Bacon", R.drawable.bacon));
		menu.addTopping(new Topping("Clover", R.drawable.clover));
		menu.addTopping(new Topping("Mushroom", R.drawable.mushroom));
		menu.addTopping(new Topping("Pineapple", R.drawable.pineapple));
		
		toppingsView = (GridView) findViewById(R.id.toppings_menu_list);
		toppingsView.setAdapter( new ImageAdapter(this, menu.getToppingsImages()));
		
		toppingsList=(ListView) findViewById(R.id.toppings_list);
		toppingsListAdapter = new DeletableTextAdapter(this, pizza.getToppingsNames());
		toppingsList.setAdapter(toppingsListAdapter);
		
		toppingsView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				addTopping(menu.getTopping(position));
				
			}
	    });
		toppingsView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				DragShadowBuilder toppingShadowBuilder= new View.DragShadowBuilder(view);
				//toppingShadowBuilder.onProvideShadowMetrics(new android.graphics.Point(10, 10), new android.graphics.Point(100, 500));
				view.startDrag(null, toppingShadowBuilder, view, 0);
				draggedTopping=menu.getTopping(position);
				return false;
			}
		});
		
		pizzaBottom = (ImageView) findViewById(R.id.imageView1);
		pizzaBottom.setOnDragListener(new OnDragListener(){
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch (event.getAction()){
					case DragEvent.ACTION_DRAG_ENTERED:
						//Toast.makeText(v.getContext(), "entered", Toast.LENGTH_SHORT).show();
						//v.setBackgroundResource(R.drawable.delete_button);
						break;
					case DragEvent.ACTION_DRAG_EXITED:
						//Toast.makeText(v.getContext(), "exited", Toast.LENGTH_SHORT).show();
						//v.setBackgroundResource(R.drawable.pizza_bottom);
						break;
					case DragEvent.ACTION_DROP:
						addTopping(draggedTopping);
						toppingsListAdapter.updateData(pizza.getToppingsNames());
					break;}
						return true;
				
			}
			
		});
		
		
		//TODO drawing toppings on pizza
		//Bitmap pizzaD = Bitmap.createBitmap(pizzaBottom.getWidth(), pizzaBottom.getHeight(), Bitmap.Config.RGB_565);
		
		
	}
	
	public void addTopping(Topping topping){
		pizza.addTopping(topping);
		View usedTopping = toppingsView.getChildAt(menu.getToppingPosition(topping));
		usedTopping.setClickable(true);
		usedTopping.setAlpha((float) 0.5);
		
		ArrayList<Drawable> pizzaToppingsDrawables = pizza.getToppingsDrawables(this);
		pizzaDrawable = new LayerDrawable((Drawable[]) pizzaToppingsDrawables.toArray(new Drawable[pizzaToppingsDrawables.size()]));
		this.pizzaBottom.setImageDrawable(pizzaDrawable);
		
		toppingsListAdapter.updateData(pizza.getToppingsNames());
	}
	
	public void deleteClick(View v){
		ListView lv = this.toppingsList;
		int position = lv.getPositionForView(v);
		View usedTopping = toppingsView.getChildAt(menu.getToppingPosition(pizza.getTopping(position)));
		usedTopping.setClickable(false);
		usedTopping.setAlpha(1);
		pizza.removeTopping(position);
		
		ArrayList<Drawable> pizzaToppingsDrawables = pizza.getToppingsDrawables(this);
		pizzaDrawable = new LayerDrawable((Drawable[]) pizzaToppingsDrawables.toArray(new Drawable[pizzaToppingsDrawables.size()]));
		this.pizzaBottom.setImageDrawable(pizzaDrawable);
		
		toppingsListAdapter.updateData(pizza.getToppingsNames());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
