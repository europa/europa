package com.europa.filehelper.ui.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.europa.filehelper.R;
import com.europa.filehelper.Tool.FileMIMEType;
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
	private TextView emptyFolderText;

	@Override
	public void onClick(View v) {

	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			currentFile = brain.getCurrentFile();
			hostActivity.addDirectorys(currentFile);
			fileItemList.clear();
			for (File file : currentFile.listFiles()) {
				FileItem item = new FileItem();
				item.setFile(file);
				item.setType(getFileType(item));
				fileItemList.add(item);
			}
		}
		fileListView = ViewUtil.findListView(view, R.id.fileListView);
		emptyFolderText = (TextView) view.findViewById(R.id.emptyfolderText);
		fileListAdapter = new FileApdater(fileItemList, hostActivity);
		fileListView.setAdapter(fileListAdapter);
		fileListView.setEmptyView(emptyFolderText);
		fileListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		fileListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				FileItem item = fileItemList.get(arg2);
				if (mActionMode == null && item.getFile().isDirectory()) {
					brain.setCurrentFile(item.getFile());
					replaceFragment(new MainFragment());
				} else {
					handleFile(item);
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
					// mActionMode = arg0;
					((MainActivity) hostActivity).handleDelete();
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
				File file = item.getFile();
				String path = file.getPath();
				if (file.delete()) {
					Log.i(TAG, "delete " + path + " successfully");
				} else {
					Log.i(TAG, "delete " + path + " unsuccessfully");
				}
				deletedItems.add(item);
			}
		}
		fileItemList.removeAll(deletedItems);
		multiChoiceModeListener.onDestroyActionMode(mActionMode);
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

	public void search(String key) {
		key = key.trim();
		List<FileItem> items = new ArrayList<FileItem>();
		int type = 0;
		int length = key.length();
		if (length == 0) {
			type = GlobalValue.SEARCH_ALL;
		} else if (length == 1 && key.equals("*")) {
			type = GlobalValue.SEARCH_ALL;
		} else if (key.startsWith("*") && key.endsWith("*")) {
			type = GlobalValue.SERACH_CONTAIN;
			key = key.substring(1, length - 1);
		} else if (key.startsWith("*")) {
			key = key.substring(1, length);
			type = GlobalValue.SEARCH_END;
		} else if (key.endsWith("*")) {
			type = GlobalValue.SERCH_START;
			key = key.substring(0, length - 1);
		} else {
			type = GlobalValue.SERCH_START;
		}
		items = searchByType(type, key);
		fileListAdapter.list = items;
		fileListAdapter.notifyDataSetChanged();

	}

	public List<FileItem> searchByType(int type, String key) {
		List<FileItem> items = new ArrayList<FileItem>();
		for (FileItem item : fileItemList) {
			String name = item.getFile().getName().toLowerCase();
			key = key.toLowerCase();
			if ((type == GlobalValue.SEARCH_END && name.endsWith(key))
					|| (type == GlobalValue.SEARCH_EQUAL
							&& name.equalsIgnoreCase((key))
							|| (type == GlobalValue.SERACH_CONTAIN)
							&& name.contains(key) || (type == GlobalValue.SERCH_START && name
							.startsWith(key)))
					|| type == GlobalValue.SEARCH_ALL) {
				items.add(item);
			}
		}
		return items;
	}

	private void handleFile(FileItem item) {
		AVAnalytics.onEvent(hostActivity, "openFile");
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		String type = getMIMEType(item.getFile());
		if (type.equals("")) {
			showToast("此文件不能打开！");
			return;
		}
		intent.setDataAndType(Uri.fromFile(item.getFile()), type);
		startActivity(intent);
	}

	private String getMIMEType(File file) {
		String type = "";
		String fileName = file.getName();
		if (!fileName.contains(".")) {
			return type;
		}
		String end = fileName.substring(fileName.lastIndexOf("."),
				fileName.length());
		for (int i = 0; i < FileMIMEType.MIME_TYPES.length; i++) {
			if (end.equals(FileMIMEType.MIME_TYPES[i][0])) {
				type = FileMIMEType.MIME_TYPES[i][1];
				break;
			}
		}
		return type;
	}

	private int getFileType(FileItem item) {
		if (item.getFile().isDirectory()) {
			return R.drawable.folder;
		}
		String mimeType = getMIMEType(item.getFile());
		if (mimeType.contains("text")) {
			return R.drawable.txt;
		} else if (mimeType.contains("video")) {
			return R.drawable.video;
		} else if (mimeType.contains("application/vnd.android.package-archive")) {
			return R.drawable.apk;
		} else if (mimeType.contains("image")) {
			return R.drawable.image;
		} else if (mimeType.contains("word")) {
			return R.drawable.word;
		} else if (mimeType.contains("excel") || mimeType.contains("sheet")) {
			return R.drawable.excel;
		} else if (mimeType.contains("audio")) {
			return R.drawable.audio;
		} else if (mimeType.contains("powerpoint")) {
			return R.drawable.ppt;
		} else {
			return R.drawable.file;
		}
	}
}