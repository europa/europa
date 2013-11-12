package com.europa.store.fragment;

import com.europa.store.activity.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

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
	public abstract View findView(LayoutInflater inflater);
	public abstract void handle();
	
}
