package com.europa.filehelper.ui.fragment;

import com.avos.avoscloud.AVAnalytics;
import com.europa.filehelper.R;
import com.europa.tool.ViewUtil;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * the fragment's common viriables and methods are here
 * 
 * TAG:log's name hostActivity:the host activity of the fragment
 * 
 * @author europa
 * 
 */
public class MainFragment extends BaseFragment {
	TextView helloText;
	@Override
	public void onClick(View v) {
		
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_main,null);
		helloText=ViewUtil.findText(view, R.id.helloText);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			helloText.setText(Environment.getExternalStorageDirectory().getAbsolutePath());
		}
		AVAnalytics.trackAppOpened(hostActivity.getIntent());
		return view;
	}

	@Override
	public void handle() {
		
	}
}
