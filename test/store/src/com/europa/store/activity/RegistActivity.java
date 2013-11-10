package com.europa.store.activity;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.LoginFragment;
import com.europa.store.fragment.RegistFragment;

public class RegistActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new RegistFragment();
		return subFragment;
	}

}
