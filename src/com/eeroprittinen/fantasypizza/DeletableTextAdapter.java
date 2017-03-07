package com.eeroprittinen.fantasypizza;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeletableTextAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<String> textItems;
	
    public DeletableTextAdapter(Context c, String[] texts) {
    	mContext = c;
        textItems = new ArrayList<String>();
        for(String value: texts){
        	textItems.add(value);
        }
    }	
    public DeletableTextAdapter(Context c, ArrayList<String> texts) {
        mContext = c;
        if(texts!=null)
        	textItems = texts;
        else textItems=null;
    }		
    
	@Override
	public int getCount() {
		if(textItems!=null)
			return textItems.size();
		else return 0;
	}

	@Override
	public Object getItem(int position) {
		if(textItems!=null)
			return textItems.get(position);
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.deletable_text, parent, false);
		}else rowView = (View) convertView;
		
		TextView text = (TextView) rowView.findViewById(R.id.toppings_list_text);
		text.setText(textItems.get(position));
		
		ImageView image = (ImageView) rowView.findViewById(R.id.toppings_list_delete);
		//image.setImageResource(R.drawable.delete_button);
		
		return rowView;
	}
	
	public void updateData(ArrayList<String> updatedData) {
		textItems = updatedData;
	    this.notifyDataSetChanged();
	}
}
