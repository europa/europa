package com.europa.store.fragment;

import java.io.File;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.LogUtil.avlog;
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
	MyLog log = new MyLog(TAG);
	TextView apkText;
	ImageView logoImg;
	GridView appImgsGrid;
	AppImgAdapter appImgAdapter;
	EditText packageEdit, comEdit, appUpdateInfoEditText, versionCodeEdit,
			versionNameEdit, appIntroEdit;
	RadioGroup forceUpdateGroup;
	int onClickPosition = -1;
	ProgressDialog progressDialog;

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
			if (canSubmit()) {
				fillApp();
				submit();
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
		appUpdateInfoEditText = ViewUtil.findEdit(view, R.id.appUpateInfoEdit);
		versionCodeEdit = ViewUtil.findEdit(view, R.id.versionCodeEdit);
		versionNameEdit = ViewUtil.findEdit(view, R.id.versionNameEdit);
		appIntroEdit = ViewUtil.findEdit(view, R.id.introEdit);
		logoImg = (ImageView) view.findViewById(R.id.appLogoImg);
		appImgsGrid = (GridView) view.findViewById(R.id.appImgsGrid);
		forceUpdateGroup = (RadioGroup) view
				.findViewById(R.id.forceUpdateGroup);
		appImgAdapter = new AppImgAdapter(app.getImgsList(), hostActivity);
		appImgsGrid.setAdapter(appImgAdapter);
		appImgsGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				onClickPosition = arg2;
				new Thread() {
					@Override
					public void run() {
						showFileChooser(GlobalValue.APK_IMGS_SELECT_CODE);
					}
				}.start();
				// showFileChooser(GlobalValue.APK_IMGS_SELECT_CODE);
			}
		});
		progressDialog=new ProgressDialog(hostActivity,ProgressDialog.STYLE_HORIZONTAL);
		return view;
	}

	@Override
	public void handle() {
	}

	public void showFileChooser(int type) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		if (isAdded()) {
			startActivityForResult(
					Intent.createChooser(intent, "Select a File to upload"),
					type);
		} else {
			this.onAttach(hostActivity);
			log.i(TAG + " is not added to hostActivity");
			startActivityForResult(
					Intent.createChooser(intent, "Select a File to upload"),
					type);
		}
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
		EditText[] edits = { packageEdit, comEdit, appUpdateInfoEditText,
				versionCodeEdit, versionNameEdit, appIntroEdit };
		Boolean result = true;
		for (EditText edit : edits) {
			if (TextTool.isEmpty(edit)) {
				edit.setError("不能为空!");
				result = false;
			}
		}
		if (TextTool.isEmpty(apkText)) {
			result = false;
			ToastTool.show(hostActivity, "请上传app文件！");
			return result;
		}
		if (logoImg.getBackground() == null) {
			result = false;
			ToastTool.show(hostActivity, "请上传app的logo图片！");
			return result;
		}
		if (appImgAdapter.getCount() != 2) {
			result = false;
			ToastTool.show(hostActivity, "请上传app的两张截图!");
			return result;
		}
		return result;
	}

	/**
	 * 填充app的信息
	 */
	private void fillApp() {
		app.setPackageName(TextTool.getStr(packageEdit));
		app.setComName(TextTool.getStr(comEdit));
		app.setAppUpdateInfo(TextTool.getStr(appUpdateInfoEditText));
		app.setVersionCode(Integer.parseInt(TextTool.getStr(versionCodeEdit)));
		app.setVersionName(TextTool.getStr(versionNameEdit));
		app.setVersionIntro(TextTool.getStr(appIntroEdit));
		if (forceUpdateGroup.getCheckedRadioButtonId() == R.id.forceUpdateRadioBtn) {
			app.setForceUpdate(GlobalValue.FORCEUPDATE);
		} else {
			app.setForceUpdate(GlobalValue.NOT_FORCEUPDATE);
		}
	}

	private void submit() {
		try {
			AVFile appFile = AVFile.parseFileWithAbsoluteLocalPath("file",
					app.getApkPath());
			final AVFile appLogo = AVFile.parseFileWithAbsoluteLocalPath(
					"logo", app.getLogoPath());
			final AVFile appImgA = AVFile.parseFileWithAbsoluteLocalPath(
					"imga", app.getImgsList().get(0));
			final AVFile appImgB = AVFile.parseFileWithAbsoluteLocalPath(
					"imgb", app.getImgsList().get(1));
			String[] fileNames = { "file", "logo", "imga", "imgb" };
			AVFile[] files = { appFile, appLogo, appImgA, appImgB };
			progressDialog.setMax(100);
			uploadFile(fileNames, files, 0);
		} catch (Exception e) {
			e.printStackTrace();
			log.e(e);
		}

	}

	private void uploadFile(final String[] fileNames, final AVFile[] files,
			final int index) throws Exception {
		if (files.length == index) {
			uploadApp(fileNames, files);
			return;
		}
		log.i("upload " + fileNames[index]);
		progressDialog.setProgress(0);
		progressDialog.show(hostActivity,"上传app","正在上传"+fileNames[index],true,false);
		files[index].saveInBackground(new SaveCallback() {
			@Override
			public void done(AVException arg0) {
				if (arg0 == null) {
					log.i("success");
					try {
						uploadFile(fileNames, files, index + 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					ToastTool.show(hostActivity, "upload " + fileNames[index]
							+ " failure.");
				}
			}
		},new ProgressCallback() {
			
			@Override
			public void done(Integer arg0) {
				progressDialog.setProgress(arg0);
			}
		});
	}

	private void uploadApp(String[] fileNames, AVFile[] files) {
		AVObject appObject = new AVObject("app");
		for (int i = 0; i < fileNames.length; i++) {
			appObject.put(fileNames[i], files[i]);
		}
		appObject.put("packageName", app.getPackageName());
		appObject.put("company", app.getComName());
		appObject.put("appDescription", app.getAppUpdateInfo());
		appObject.put("versionName", app.getVersionName());
		appObject.put("versionCode", app.getVersionCode());
		appObject.put("forceUpdate", app.getForceUpdate());
		appObject.put("versionIntro", app.getVersionIntro());
		appObject.saveInBackground(new SaveCallback() {
			@Override
			public void done(AVException arg0) {
				if (arg0 == null) {
					ToastTool.show(hostActivity, "upload app successfully!");
					hostActivity.onBackPressed();
				} else {
					ToastTool.show(hostActivity, "upload app failly!");
				}
			}
		});
	}
}
