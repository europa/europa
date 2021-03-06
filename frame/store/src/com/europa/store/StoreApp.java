package com.europa.store;

import com.avos.avoscloud.AVOSCloud;
import com.europa.store.tool.GlobalValue;
import com.europa.store.tool.DataTool.MySqliteHelper;

import android.app.Application;

public class StoreApp extends Application {

	@Override
	public void onCreate() {
		AVOSCloud.useAVCloudCN();
		AVOSCloud.initialize(this, GlobalValue.AVOS_APP_ID, GlobalValue.AVOS_APP_KEY);
		MySqliteHelper helper=new MySqliteHelper(this);
		super.onCreate();
	}
}
