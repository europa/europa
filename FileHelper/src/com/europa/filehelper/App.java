package com.europa.filehelper;

import android.app.Application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.europa.filehelper.Tool.GlobalValue;

public class App extends Application{

	@Override
	public void onCreate() {
		AVOSCloud.useAVCloudCN();
		AVOSCloud.initialize(this, GlobalValue.AVOS_APP_ID,GlobalValue.AVOS_APP_KEY);
		AVAnalytics.start(this);
		super.onCreate();
       
	}
	
}
