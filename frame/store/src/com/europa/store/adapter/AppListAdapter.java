package com.europa.store.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.europa.store.bean.App;
import com.europa.tool.ToolAdapter;

public class AppListAdapter extends ToolAdapter<App> {

	public AppListAdapter(List<App> list, Activity activity) {
		super(list, activity);
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}

}
