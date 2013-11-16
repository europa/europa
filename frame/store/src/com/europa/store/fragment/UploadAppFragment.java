package com.europa.store.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.europa.store.R;
import com.europa.store.adapter.AppImgAdapter;
import com.europa.store.bean.App;
import com.europa.store.tool.FileTool;
import com.europa.store.tool.GlobalValue;
import com.europa.store.tool.MyLog;
import com.europa.store.tool.ToastTool;
import com.europa.tool.ViewUtil;

public class UploadAppFragment extends BaseFragment {

	App app = new App();
	TextView apkText;
	ImageView logoImg;
	GridView appImgsGrid;
	List<Uri> appImgUriList=new ArrayList<Uri>();
	AppImgAdapter appImgAdapter;

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.uploadBtn:
			showFileChooser(GlobalValue.APK_SELECT_CODE);
			break;
		case R.id.appLogoBtn:
			showFileChooser(GlobalValue.APK_LOGO_SELECT_CODE);
			break;
		case R.id.appImgBtn:
			if (app.getImgsList().size() < 2) {
				showFileChooser(GlobalValue.APK_IMGS_SELECT_CODE);
			} else {
				ToastTool.show(hostActivity, "最多只能上传两张图片。");
			}
			break;
		case R.id.submitBtn:
			break;
		default:
			break;
		}
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_upload_app, null);
		apkText = ViewUtil.findText(view, R.id.apkText);
		logoImg = (ImageView) view.findViewById(R.id.appLogoImg);
		appImgsGrid = (GridView) view.findViewById(R.id.appImgsGrid);
		appImgAdapter=new AppImgAdapter(appImgUriList, hostActivity);
		appImgsGrid.setAdapter(appImgAdapter);
		return view;
	}

	@Override
	public void handle() {
	}

	public void showFileChooser(int type) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(
				Intent.createChooser(intent, "Select a File to upload"), type);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Uri uri = data.getData();
			String path = FileTool.getPath(hostActivity, uri);
			int length = path.split(File.pathSeparator).length;
			String filename = path.split(File.pathSeparator)[length - 1];
			MyLog.i(TAG, "path:" + path);
			switch (requestCode) {
			case GlobalValue.APK_SELECT_CODE:
				app.setApkPath(path);
				apkText.setText(filename);
				break;
			case GlobalValue.APK_LOGO_SELECT_CODE:
				app.setLogoPath(path);
				logoImg.setImageURI(uri);
				break;
			case GlobalValue.APK_IMGS_SELECT_CODE:
				app.getImgsList().add(path);
				appImgUriList.add(uri);
				appImgAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		} else {

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
