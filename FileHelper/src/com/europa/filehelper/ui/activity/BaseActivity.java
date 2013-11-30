package com.europa.filehelper.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.europa.filehelper.R;
import com.europa.filehelper.ui.fragment.BaseFragment;


public abstract class BaseActivity extends Activity {

	/**
	 * must be valued in SubActivity
	 */
	public String TAG=this.getClass().getSimpleName();
	public BaseFragment subFragment;
	public FragmentManager fragmentManager;
	public LinearLayout directoryLayout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fillFragment(savedInstanceState,R.id.container,newFragment());
		directoryLayout=(LinearLayout) findViewById(R.id.directoryLayout);
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
	
	public void addDirectory(String directory){
		TextView textView=new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		textView.setText(directory);
		directoryLayout.addView(textView);
		ImageView imageView=new ImageView(this);
		imageView.setBackgroundResource(R.drawable.slash);
		imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		directoryLayout.addView(imageView);
	}
}
