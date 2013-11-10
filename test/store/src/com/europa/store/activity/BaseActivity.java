package com.europa.store.activity;

import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.LoginFragment;

import android.R;
import android.os.Bundle;
import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public abstract class BaseActivity extends FragmentActivity {

	/**
	 * must be valued in SubActivity
	 */
	public BaseFragment subFragment;
	public FragmentManager fragmentManager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fillFragment(savedInstanceState, android.R.id.content,newFragment());
	}
	public void fillFragment(Bundle bundle,int id,BaseFragment baseFragment){
		if(bundle==null){
			if(fragmentManager==null){
				fragmentManager=getSupportFragmentManager();
			}
			fragmentManager.beginTransaction().add(id, baseFragment).commit();
		}
	}
	/**
	 * subFragment must be valued in this method
	 * @return 
	 */
	public abstract BaseFragment newFragment();
	
	public void replaceFragment(BaseFragment fragment){
		FragmentTransaction transaction=fragmentManager.beginTransaction();
		transaction.replace(android.R.id.content, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
