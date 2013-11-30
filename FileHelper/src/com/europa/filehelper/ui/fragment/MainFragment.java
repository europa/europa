package com.europa.filehelper.ui.fragment;

import java.io.File;

import com.avos.avoscloud.AVAnalytics;
import com.europa.filehelper.R;
import com.europa.tool.ViewUtil;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

	@Override
	public void onClick(View v) {

	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			currentFile = Environment.getExternalStorageDirectory();
			String directory = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			for (String str : directory.split(File.separator)) {
				hostActivity.addDirectory(str);
			}

		}
		fileListView = ViewUtil.findListView(view, R.id.fileListView);
		fileListView.setAdapter(new ArrayAdapter<String>(hostActivity,
				android.R.layout.simple_list_item_1, currentFile.list()));
		return view;
	}

	@Override
	public void handle() {

	}
}
