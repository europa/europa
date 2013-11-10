package com.europa.store.fragment;

import com.europa.store.R;
import com.europa.store.activity.RegistActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends BaseFragment {

	View view;
	Button loginBtn,toRegistBtn;
	public View findView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_login, null);
		loginBtn=(Button) view.findViewById(R.id.loginBtn);
		toRegistBtn=(Button) view.findViewById(R.id.toRegistBtn);
		loginBtn.setOnClickListener(this);
		toRegistBtn.setOnClickListener(this);
		return view;
	}
	@Override
	public void handle() {
		
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.loginBtn:
			break;
		case R.id.toRegistBtn:
			hostActivity.replaceFragment(new RegistFragment());
			break;
		default:
			break;
		}
	}
}
