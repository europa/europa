package com.europa.store.activity;

import com.europa.store.fragment.AppsFragment;
import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.LoginFragment;
import com.europa.store.fragment.RegistFragment;

public class AppsActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new AppsFragment();
		return subFragment;
	}

}
