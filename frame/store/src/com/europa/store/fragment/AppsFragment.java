package com.europa.store.fragment;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.ParseException;
import com.avos.avoscloud.ParseObject;
import com.avos.avoscloud.ParseQuery;
import com.europa.store.R;
import com.europa.store.adapter.AppListAdapter;

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
		ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("App");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				if(arg1!=null){
					
				}else{
					
				}
			}
		});
	}
}
