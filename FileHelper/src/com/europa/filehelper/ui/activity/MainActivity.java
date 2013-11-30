package com.europa.filehelper.ui.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.europa.filehelper.R;
import com.europa.filehelper.ui.fragment.BaseFragment;
import com.europa.filehelper.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

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
	
	
}
