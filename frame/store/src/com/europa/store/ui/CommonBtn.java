package com.europa.store.ui;

import com.europa.store.activity.BaseActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CommonBtn extends Button {

	public CommonBtn(Context context) {
		super(context);
		setListener(context);
	}

	public CommonBtn(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setListener(context);
	}

	public CommonBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		setListener(context);
	}
	
	private void setListener(Context context) {
		if (context instanceof BaseActivity) {
			setOnClickListener(((BaseActivity) context).subFragment);
		} else {
			setOnClickListener((OnClickListener) context);
		}
	}
}
