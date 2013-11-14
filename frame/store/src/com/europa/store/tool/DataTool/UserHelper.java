package com.europa.store.tool.DataTool;

import com.europa.store.bean.User;
import com.europa.store.tool.MyLog;
import com.europa.store.tool.SqlValue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UserHelper extends SqliteHelper {

	public UserHelper(Context context) {
		super(context);
	}

	public User getUserByUsername(String username) {
		open(0);
		Cursor cursor = query(SqlValue.TABLE_USER, null,
				SqlValue.USER_COLUMN_USERNAME + "=" + "'"+username+"'", null, null,
				null, null);
		User user;
		user = new User();
		MyLog.i(TAG,"query size:"+cursor.getCount());
		if (cursor.moveToNext()) {
			user.setUsername(cursor.getString(cursor
					.getColumnIndex(SqlValue.USER_COLUMN_USERNAME)));
			user.setPwd(cursor.getString(cursor
					.getColumnIndex(SqlValue.USER_COLUMN_PWD)));
			user.setEmail(cursor.getString(cursor
					.getColumnIndex(SqlValue.USER_COLUMN_EMAIL)));
			user.setAutologin(cursor.getInt(cursor
					.getColumnIndex(SqlValue.USER_COLUMN_USERNAME)));
			cursor.close();
			close();
			return user;

		}
		cursor.close();
		close();
		return null;
	}

	public void insertUser(User user) {
		open(1);
		ContentValues values = new ContentValues();
		values.put(SqlValue.USER_COLUMN_USERNAME, user.getUsername());
		values.put(SqlValue.USER_COLUMN_PWD, user.getPwd());
		values.put(SqlValue.USER_COLUMN_EMAIL, user.getEmail());
		values.put(SqlValue.USER_COLUMN_AUTOLOGIN, user.getAutologin());
		MyLog.i(TAG,
				"insert num:"
						+ database.insert(SqlValue.TABLE_USER, null, values));
		close();
	}
}
