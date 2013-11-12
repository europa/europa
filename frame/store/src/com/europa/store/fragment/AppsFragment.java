package com.europa.store.fragment;

import com.europa.store.R;
import com.europa.store.adapter.AppListAdapter;
import com.europa.store.ui.CommonBtn;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AppsFragment extends BaseFragment {
	
	ListView appListView;
	AppListAdapter appListAdapter;
	TextView emptyTextView;
	@Override
	public void onClick(View arg0) {
		
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view =inflater.inflate(R.layout.fragment_apps, null);
		appListView=(ListView) view.findViewById(R.id.appListView);
		emptyTextView=(TextView) view.findViewById(R.id.emailText);
		appListAdapter=new AppListAdapter(null, hostActivity);
		appListView.setAdapter(appListAdapter);
		appListView.setEmptyView(emptyTextView);
		return view;
	}

	@Override
	public void handle() {
		
	}
}
