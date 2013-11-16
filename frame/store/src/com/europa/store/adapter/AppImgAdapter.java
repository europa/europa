package com.europa.store.adapter;

import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.europa.store.R;
import com.europa.tool.ToolAdapter;

public class AppImgAdapter extends ToolAdapter<Uri> {

	LayoutInflater inflater;
	public AppImgAdapter(List<Uri> list, Activity activity) {
		super(list, activity);
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if(inflater==null){
			inflater=LayoutInflater.from(activity);
		}
		if(arg1==null){
			arg1=inflater.inflate(R.layout.item_appimg, null);
			((ImageView)arg1).setImageURI(getItem(arg0));
		}
		return arg1;
	}

}
