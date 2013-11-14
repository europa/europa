package com.europa.store.activity;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.LoginFragment;

public class MainActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new LoginFragment();
		return subFragment;
	}
}
