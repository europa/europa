package com.europa.store.fragment;

import com.europa.store.R;
import com.europa.store.ui.CommonBtn;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistFragment extends BaseFragment {
	
	EditText unameEdit,pwdEdit;
	Button registBtn;
	@Override
	public void onClick(View arg0) {
		
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view =inflater.inflate(R.layout.fragment_regist, null);
		unameEdit=(EditText) view.findViewById(R.id.unameEdit);
		pwdEdit=(EditText) view.findViewById(R.id.pwdEdit);
		registBtn=(Button) view.findViewById(R.id.registBtn);
		return view;
	}

	@Override
	public void handle() {
		
	}
}
