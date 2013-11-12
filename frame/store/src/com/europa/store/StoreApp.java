package com.europa.store;

import com.avos.avoscloud.Parse;
import com.europa.store.tool.GlobelValue;

import android.app.Application;

public class StoreApp extends Application {

	@Override
	public void onCreate() {
		Parse.useAVCloudCN();
		Parse.initialize(this, GlobelValue.AVOS_APP_ID, GlobelValue.AVOS_APP_KEY);
		super.onCreate();
	}
}
