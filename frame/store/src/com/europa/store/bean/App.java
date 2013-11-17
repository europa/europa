package com.europa.store.bean;

import java.util.ArrayList;
import java.util.List;

public class App {
	String packageName;
	String comName;
	String appDescription;
	String versionName;
	int versionCode;
	int forceUpdate;
	String versionIntro;
	String apkPath;
	String logoPath;
	List<String> imgsList=new ArrayList<String>();
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionIntro() {
		return versionIntro;
	}
	public void setVersionIntro(String versionIntro) {
		this.versionIntro = versionIntro;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	public List<String> getImgsList() {
		return imgsList;
	}
	public void setImgsList(List<String> imgsList) {
		this.imgsList = imgsList;
	}
	public int getForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(int forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
}
