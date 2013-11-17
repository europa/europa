package com.europa.store.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.europa.store.R;
import com.europa.store.tool.ImageTool;
import com.europa.tool.ToolAdapter;

public class AppImgAdapter extends ToolAdapter<String> {

	LayoutInflater inflater;

	public AppImgAdapter(List<String> list, Activity activity) {
		super(list, activity);
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (inflater == null) {
			inflater = LayoutInflater.from(activity);
		}
		arg1 = inflater.inflate(R.layout.item_appimg, null);
		((ImageView) arg1).setBackgroundDrawable(new BitmapDrawable(ImageTool
				.readBitMap(activity, getItem(arg0))));
		return arg1;
	}
}
