package com.europa.filehelper.ui.activity;

import android.app.DialogFragment;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import com.europa.filehelper.R;
import com.europa.filehelper.Tool.Brain;
import com.europa.filehelper.ui.fragment.BaseFragment;
import com.europa.filehelper.ui.fragment.FileDialogFragment;
import com.europa.filehelper.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

	Brain brain=Brain.newInstance();
	@Override
	public void onBackPressed() {
		if(brain.getCurrentFile().equals(Environment.getExternalStorageDirectory())){
			brain=null;
		}else{
			brain.setCurrentFile(brain.getCurrentFile().getParentFile());
		}
		super.onBackPressed();
	}

	DialogFragment fragment;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public BaseFragment newFragment() {
		return new MainFragment();
	}
	
	public void clickPositiveBtn(){
		((MainFragment)subFragment).handleDelete();
	}
	
	public void clickNegativeBtn(){
		fragment.dismiss();
	}
	
	public void handleDelete(){
		fragment=FileDialogFragment.newInstance("是否删除文件？");
		fragment.show(fragmentManager,null);
	}
	
	
}
