package com.europa.store.tool;

public class SqlValue {
	public static final String DATABASE_NAME = "store.db";
	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER = "user";
	public static final String USER_COLUMN_USERNAME = "username";
	public static final String USER_COLUMN_PWD = "pwd";
	public static final String USER_COLUMN_EMAIL = "email";
	public static final String USER_COLUMN_AUTOLOGIN = "autologin";
	public static final String USER_CREAT = "create table " +"if not exists "+ TABLE_USER + "("
			+ USER_COLUMN_USERNAME + " text not null," + USER_COLUMN_PWD
			+ " text not null," + USER_COLUMN_EMAIL + " text not null,"
			+ USER_COLUMN_AUTOLOGIN + " integer not null); ";
}
