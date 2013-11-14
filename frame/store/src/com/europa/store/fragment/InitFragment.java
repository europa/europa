package com.europa.store.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.europa.store.R;
import com.europa.store.activity.AppsActivity;
import com.europa.store.activity.MainActivity;

public class InitFragment extends BaseFragment {

	public View findView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_init, null);
		return view;
	}
	@Override
	public void handle() {
		autoLogin();
	}
	@Override
	public void onClick(View arg0) {
		
	}
	
	public void autoLogin(){
		String username=getUsername();
		if(username!=null){
			if(userHelper.getUserByUsername(username)!=null){
				startActivity(new Intent(hostActivity,AppsActivity.class));
				return;
			}
		}
		startActivity(new Intent(hostActivity,MainActivity.class));
	}
}
