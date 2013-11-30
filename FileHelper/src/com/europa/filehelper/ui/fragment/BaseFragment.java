package com.europa.filehelper.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.avos.avoscloud.AVAnalytics;
import com.europa.filehelper.ui.activity.BaseActivity;

/**
 * the fragment's common viriables and methods are here
 * 
 * TAG:log's name hostActivity:the host activity of the fragment
 * 
 * @author europa
 * 
 */
public abstract class BaseFragment extends Fragment implements OnClickListener{
	String TAG = this.getClass().getSimpleName();
	public BaseActivity hostActivity;

	public void onCreate(Bundle savedInstanceState) {
		hostActivity = (BaseActivity)getActivity();
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =findView(inflater);
		handle();
		return view;
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPause() {
		AVAnalytics.onFragmentEnd(TAG);
		super.onPause();
	}


	@Override
	public void onResume() {
		AVAnalytics.onFragmentStart(TAG);
		super.onResume();
	}


	public abstract View findView(LayoutInflater inflater);
	public abstract void handle();
}
