package com.europa.store.activity;

import android.view.WindowManager;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.InitFragment;
import com.europa.store.tool.GlobalValue;

public class InitActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		setWidthHeight();
		subFragment=new InitFragment();
		return subFragment;
	}
	
	private void setWidthHeight(){
		WindowManager manager=getWindowManager();
		GlobalValue.WINDOW_WIDTH=manager.getDefaultDisplay().getWidth();
		GlobalValue.WINDOW_HEIGHT=manager.getDefaultDisplay().getHeight();
	}

}
