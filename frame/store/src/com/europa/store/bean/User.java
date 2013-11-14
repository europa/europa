package com.europa.store.bean;


public class User {
	String username;
	String pwd;
	String email;
	int autologin;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAutologin() {
		return autologin;
	}
	public void setAutologin(int autologin) {
		this.autologin = autologin;
	}
}
