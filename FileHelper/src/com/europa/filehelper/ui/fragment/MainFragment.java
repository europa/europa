package com.europa.filehelper.ui.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.europa.filehelper.R;
import com.europa.filehelper.Tool.GlobalValue;
import com.europa.filehelper.bean.FileItem;
import com.europa.filehelper.ui.activity.MainActivity;
import com.europa.filehelper.ui.adapter.FileApdater;
import com.europa.tool.ViewUtil;

/**
 * the fragment's common viriables and methods are here
 * 
 * TAG:log's name hostActivity:the host activity of the fragment
 * 
 * @author europa
 * 
 */
public class MainFragment extends BaseFragment {
	ListView fileListView;
	File currentFile;
	List<FileItem> fileItemList = new ArrayList<FileItem>();
	MultiChoiceModeListener multiChoiceModeListener;
	FileApdater fileListAdapter;
	ActionMode mActionMode;
	private Boolean allOn = false;

	@Override
	public void onClick(View v) {

	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			currentFile=brain.getCurrentFile();
			hostActivity.addDirectorys(currentFile);
			for (File file : currentFile.listFiles()) {
				FileItem item = new FileItem();
				item.setFile(file);
				item.setType(file.isDirectory() ? GlobalValue.IS_DIRECTORY
						: GlobalValue.IS_FILE);
				fileItemList.add(item);
			}
		}
		fileListView = ViewUtil.findListView(view, R.id.fileListView);
		fileListAdapter = new FileApdater(fileItemList, hostActivity);
		fileListView.setAdapter(fileListAdapter);
		fileListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		fileListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				FileItem item = fileItemList.get(arg2);
				if (mActionMode == null
						&& item.getType() == GlobalValue.IS_DIRECTORY) {
					brain.setCurrentFile(item.getFile());
					replaceFragment(new MainFragment());
				} else {
					showToast("This is a file!");
				}
			}
		});
		multiChoiceModeListener = new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode arg0) {
				/**
				 * can be destroyed by MenuItem or the left original icon,so
				 * need uncheck all checkboxes in the listview
				 */
				setAllChecked(false);
				mActionMode = null;
				hostActivity.edited = false;
				fileListAdapter.notifyDataSetChanged();
			}

			@Override
			public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
				arg0.getMenuInflater().inflate(R.menu.context, arg1);
				mActionMode = arg0;
				hostActivity.edited = mActionMode != null ? true : false;
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
				switch (arg1.getItemId()) {
				case R.id.item_delete:
					((MainActivity) hostActivity).handleDelete();
					onDestroyActionMode(arg0);
					break;
				case R.id.all:
					handleAll(arg1);
					break;

				default:
					break;
				}
				return false;
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				mActionMode = mode;
				FileItem item = fileItemList.get(position);
				item.setChecked(!item.getChecked());
				fileListAdapter.notifyDataSetChanged();
			}
		};
		fileListView.setMultiChoiceModeListener(multiChoiceModeListener);
		fileListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				hostActivity.startActionMode(multiChoiceModeListener);
				return false;
			}
		});
		return view;
	}

	@Override
	public void handle() {

	}

	public void handleDelete() {
		List<FileItem> deletedItems = new ArrayList<FileItem>();
		for (FileItem item : fileItemList) {
			if (item.getChecked()) {
				File file=item.getFile();
				String path=file.getPath();
				if (file.delete()) {
					Log.i(TAG, "delete " + path + " successfully");
				} else {
					Log.i(TAG, "delete " + path + " unsuccessfully");
				}
				deletedItems.add(item);
			}
		}
		fileItemList.removeAll(deletedItems);
		fileListAdapter.notifyDataSetChanged();
	}

	private void handleAll(MenuItem item) {
		allOn = !allOn;
		if (allOn) {
			item.setIcon(android.R.drawable.btn_star_big_on);
		} else {
			item.setIcon(android.R.drawable.btn_star_big_off);
		}
		setAllChecked(allOn);
	}

	private void setAllChecked(Boolean checked) {
		SparseBooleanArray checkedArray = fileListView
				.getCheckedItemPositions();
		for (int i = 0; i < fileListAdapter.getCount(); i++) {
			if (checkedArray.get(i) != checked) {
				fileListView.setItemChecked(i, checked);
			}
		}
	}
	
	public void search(String key){
		Log.i(TAG,key);
	}
}