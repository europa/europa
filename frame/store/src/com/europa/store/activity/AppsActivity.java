package com.europa.store.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.europa.store.R;
import com.europa.store.fragment.AppsFragment;
import com.europa.store.fragment.BaseFragment;
import com.europa.store.fragment.UploadAppFragment;

public class AppsActivity extends BaseActivity {

	@Override
	public BaseFragment newFragment() {
		subFragment=new AppsFragment();
		return subFragment;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			subFragment.saveUserName(null);
			startActivity(new Intent(this,MainActivity.class));
			break;
		case R.id.upload:
			startActivity(new Intent(this,UploadAppActivity.class));
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}
