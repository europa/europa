package com.europa.store.activity;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.InitFragment;

public class InitActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new InitFragment();
		return subFragment;
	}
}
