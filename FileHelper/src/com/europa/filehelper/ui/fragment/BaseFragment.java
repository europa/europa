package com.europa.filehelper.ui.fragment;

import java.io.File;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.europa.filehelper.R;
import com.europa.filehelper.Tool.Brain;
import com.europa.filehelper.ui.activity.BaseActivity;
import com.europa.filehelper.ui.activity.MainActivity;

/**
 * the fragment's common viriables and methods are here
 * 
 * TAG:log's name hostActivity:the host activity of the fragment
 * 
 * @author europa
 * 
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	String TAG = this.getClass().getSimpleName();
	public BaseActivity hostActivity;
	ActionBar actionBar;
	Brain brain = Brain.newInstance();
	File currentFile;

	public void onCreate(Bundle savedInstanceState) {
		hostActivity = (BaseActivity) getActivity();
		actionBar = hostActivity.getActionBar();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = findView(inflater);
		AVAnalytics.trackAppOpened(hostActivity.getIntent());
		handle();
		return view;
	}

	@Override
	public void onClick(View arg0) {

	}

	@Override
	public void onPause() {
		super.onPause();
		AVAnalytics.onFragmentEnd(TAG);
	}

	@Override
	public void onResume() {
		super.onResume();
		AVAnalytics.onFragmentStart(TAG);
		SearchView searchView=((MainActivity)hostActivity).searchView;
		if(searchView!=null){
			searchView.clearFocus();
		}
	}

	public abstract View findView(LayoutInflater inflater);

	public abstract void handle();

	public void replaceFragment(BaseFragment fragment) {
		FragmentTransaction transaction = hostActivity.fragmentManager
				.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.replace(R.id.container, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	public void showToast(String toast) {
		Toast.makeText(hostActivity, toast, Toast.LENGTH_SHORT).show();
	}

}
