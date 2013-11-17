package com.europa.store.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.europa.store.R;
import com.europa.store.adapter.AppImgAdapter;
import com.europa.store.bean.App;
import com.europa.store.tool.FileTool;
import com.europa.store.tool.GlobalValue;
import com.europa.store.tool.ImageTool;
import com.europa.store.tool.MyLog;
import com.europa.store.tool.TextTool;
import com.europa.store.tool.ToastTool;
import com.europa.tool.ViewUtil;

public class UploadAppFragment extends BaseFragment {

	App app = new App();
	TextView apkText;
	ImageView logoImg;
	GridView appImgsGrid;
	AppImgAdapter appImgAdapter;
	EditText packageEdit, comEdit, appDescriptionEdit, versionCodeEdit,
			versionNameEdit, appIntroEdit;
	RadioGroup forceUpdateGroup;
	int onClickPosition = -1;

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
			if(canSubmit()){
				fillApp();
				
			}
			break;
		default:
			break;
		}
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_upload_app, null);
		apkText = ViewUtil.findText(view, R.id.apkText);
		packageEdit = ViewUtil.findEdit(view, R.id.packageEdit);
		comEdit = ViewUtil.findEdit(view, R.id.companyEdit);
		appDescriptionEdit = ViewUtil.findEdit(view, R.id.descriptionEdit);
		versionCodeEdit = ViewUtil.findEdit(view, R.id.versionCodeEdit);
		versionNameEdit = ViewUtil.findEdit(view, R.id.versionNameEdit);
		appIntroEdit = ViewUtil.findEdit(view, R.id.introEdit);
		logoImg = (ImageView) view.findViewById(R.id.appLogoImg);
		appImgsGrid = (GridView) view.findViewById(R.id.appImgsGrid);
		forceUpdateGroup=(RadioGroup) view.findViewById(R.id.forceUpdateGroup);
		appImgAdapter = new AppImgAdapter(app.getImgsList(), hostActivity);
		appImgsGrid.setAdapter(appImgAdapter);
		appImgsGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				onClickPosition = arg2;
				showFileChooser(GlobalValue.APK_IMGS_SELECT_CODE);
			}
		});
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
			String[] pathStrs = path.split(File.separator);
			int length = pathStrs.length;
			String filename = pathStrs[length - 1];
			MyLog.i(TAG, "path:" + path);
			switch (requestCode) {
			case GlobalValue.APK_SELECT_CODE:
				app.setApkPath(path);
				apkText.setText(filename);
				break;
			case GlobalValue.APK_LOGO_SELECT_CODE:
				app.setLogoPath(path);
				logoImg.setBackgroundDrawable(new BitmapDrawable(ImageTool
						.readBitMap(hostActivity, path)));
				break;
			case GlobalValue.APK_IMGS_SELECT_CODE:
				if (onClickPosition == -1) {
					app.getImgsList().add(path);
				} else {
					app.getImgsList().set(onClickPosition, path);
				}
				appImgAdapter.notifyDataSetChanged();
				// 必须清楚状态
				onClickPosition = -1;
				break;
			default:
				break;
			}
		} else {
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Boolean canSubmit() {
		EditText[] edits = { packageEdit, comEdit, appDescriptionEdit,
				versionCodeEdit, versionNameEdit, appIntroEdit };
		Boolean result=true;
		for (EditText edit : edits) {
			if (TextTool.isEmpty(edit)) {
				edit.setError("不能为空!");
				result=false;
			}
		}
		if(TextTool.isEmpty(apkText)){
			result=false;
			ToastTool.show(hostActivity,"请上传app文件！");
			return result;
		}
		if(logoImg.getBackground()!=null){
			result=false;
			ToastTool.show(hostActivity,"请上传app的logo图片！");
			return result;
		}
		if(appImgAdapter.getCount()!=2){
			result=false;
			ToastTool.show(hostActivity,"请上传app的两张截图!");
			return result;
		}
		return result;
	}
	
	/**
	 * 填充app的信息
	 */
	private void fillApp(){
		app.setPackageName(TextTool.getStr(packageEdit));
		app.setComName(TextTool.getStr(comEdit));
		app.setAppDescription(TextTool.getStr(appDescriptionEdit));
		app.setVersionCode(Integer.parseInt(TextTool.getStr(versionCodeEdit)));
		app.setVersionName(TextTool.getStr(versionNameEdit));
		app.setVersionIntro(TextTool.getStr(appIntroEdit));
		if(forceUpdateGroup.getCheckedRadioButtonId()==R.id.forceUpdateRadioBtn){
			app.setForceUpdate(GlobalValue.FORCEUPDATE);
		}else{
			app.setForceUpdate(GlobalValue.NOT_FORCEUPDATE);
		}
	}
}
