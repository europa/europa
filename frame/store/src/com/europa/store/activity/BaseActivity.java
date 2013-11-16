package com.europa.store.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.europa.store.fragment.BaseFragment;

public abstract class BaseActivity extends Activity {

	/**
	 * must be valued in SubActivity
	 */
	public String TAG=this.getClass().getSimpleName();
	public BaseFragment subFragment;
	public FragmentManager fragmentManager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fillFragment(savedInstanceState, android.R.id.content,newFragment());
	}
	public void fillFragment(Bundle bundle,int id,BaseFragment baseFragment){
		if(bundle==null){
			if(fragmentManager==null){
				fragmentManager=getFragmentManager();
			}
			fragmentManager.beginTransaction().add(id, baseFragment).commit();
		}
	}
	/**
	 * subFragment must be valued in this method
	 * @return 
	 */
	public abstract BaseFragment newFragment();
}
