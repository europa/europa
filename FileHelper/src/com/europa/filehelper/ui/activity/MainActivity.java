package com.europa.filehelper.ui.activity;

import com.europa.filehelper.ui.fragment.BaseFragment;
import com.europa.filehelper.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		return new MainFragment();
	}
	
}
