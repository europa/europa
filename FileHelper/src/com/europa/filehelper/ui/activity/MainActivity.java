package com.europa.filehelper.ui.activity;

import android.app.DialogFragment;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

import com.europa.filehelper.R;
import com.europa.filehelper.Tool.Brain;
import com.europa.filehelper.ui.fragment.BaseFragment;
import com.europa.filehelper.ui.fragment.FileDialogFragment;
import com.europa.filehelper.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

	Brain brain = Brain.newInstance();
	Boolean isExit = false;
	// Handler handler=new Handler(){
	// public void handleMessage(){
	// isExit=false;
	// }};

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			isExit = false;
			super.handleMessage(msg);
		}
	};

	@Override
	public void onBackPressed() {
		if (isExit) {
			isExit = false;
		} else {
			if (brain.getCurrentFile().equals(
					Environment.getExternalStorageDirectory())) {
				isExit = true;
				handler.sendEmptyMessageDelayed(0, 2000);
				Toast.makeText(this, "再按一次退出程序", 1000).show();
				return;
			} else {
				brain.setCurrentFile(brain.getCurrentFile().getParentFile());
			}
		}
		super.onBackPressed();
	}

	DialogFragment fragment;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_search);
		final SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(item);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				getFragment().search(searchView.getQuery().toString());
				return false;
			}
		});
		return true;
	}

	@Override
	public BaseFragment newFragment() {
		return new MainFragment();
	}

	public void clickPositiveBtn() {
		getFragment().handleDelete();
	}

	public void clickNegativeBtn() {
		fragment.dismiss();
	}

	public void handleDelete() {
		fragment = FileDialogFragment.newInstance("是否删除文件？");
		fragment.show(fragmentManager, null);
	}

	private MainFragment getFragment() {
		return (MainFragment) fragmentManager.findFragmentById(R.id.container);
	}

}
