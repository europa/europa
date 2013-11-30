package com.europa.store.fragment;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
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
		AVQuery<AVObject> query=new AVQuery<AVObject>("app");
		query.findInBackground(new FindCallback<AVObject>() {
			@Override
			public void done(List<AVObject> arg0, AVException arg1) {
			}
		});
	}
}
