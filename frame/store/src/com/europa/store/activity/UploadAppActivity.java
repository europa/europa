package com.europa.store.activity;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.UploadAppFragment;

public class UploadAppActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new UploadAppFragment();
		return subFragment;
	}
	
}
