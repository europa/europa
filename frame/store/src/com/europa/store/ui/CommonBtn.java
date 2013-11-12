package com.europa.store.ui;

import com.europa.store.activity.BaseActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CommonBtn extends Button {

	public CommonBtn(Context context) {
		super(context);
		setOnClickListener(((BaseActivity)context).subFragment);
	}

	public CommonBtn(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnClickListener(((BaseActivity)context).subFragment);
	}

	public CommonBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(((BaseActivity)context).subFragment);
	}
	
}
