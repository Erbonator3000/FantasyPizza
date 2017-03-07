package com.eeroprittinen.fantasypizza;

import java.util.ArrayList;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	protected ArrayList<Integer> ImagesIDs;
	
	
    public ImageAdapter(Context c, Integer[] imagesList) {
        mContext = c;
        ImagesIDs = new ArrayList<Integer>();
        for(Integer value: imagesList){
        	ImagesIDs.add(value);
        }
    }
    public ImageAdapter(Context c, ArrayList<Integer> imagesList) {
        mContext = c;
        if(imagesList!=null){
        	ImagesIDs=imagesList;
    
        }else ImagesIDs = new ArrayList<Integer>();
    }

	
	@Override
	public int getCount() {
		if (ImagesIDs!=null)
		return ImagesIDs.size();
		else return 0;
	}

	@Override
	public Object getItem(int position) {
		if(ImagesIDs.get(position)!=null)
			return ImagesIDs.get(position);
		else return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView == null){
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		}
		else imageView = (ImageView) convertView;
			
		imageView.setImageResource(ImagesIDs.get(position));
		return imageView;
	}

}
