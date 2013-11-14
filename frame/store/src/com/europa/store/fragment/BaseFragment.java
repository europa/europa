package com.europa.store.fragment;

import com.europa.store.activity.BaseActivity;
import com.europa.store.tool.GlobalValue;
import com.europa.store.tool.DataTool.UserHelper;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * the fragment's common viriables and methods are here
 * 
 * TAG:log's name hostActivity:the host activity of the fragment
 * 
 * @author europa
 * 
 */
public abstract class BaseFragment extends Fragment implements OnClickListener{
	String TAG = this.getClass().getSimpleName();
	public BaseActivity hostActivity;
	public UserHelper userHelper;

	public void onCreate(Bundle savedInstanceState) {
		hostActivity = (BaseActivity)getActivity();
		userHelper=new UserHelper(hostActivity);
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =findView(inflater);
		handle();
		return view;
	}
	public abstract View findView(LayoutInflater inflater);
	public abstract void handle();
	
	public void saveUserName(String username){
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(hostActivity).edit();
		editor.putString(GlobalValue.SHARE_USERNAME,username);
		editor.commit();
	}
	
	public String getUsername(){
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(hostActivity);
		return sharedPreferences.getString(GlobalValue.SHARE_USERNAME,null);
	}
}
