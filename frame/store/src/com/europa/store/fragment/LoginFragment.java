package com.europa.store.fragment;

import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.ParseException;
import com.avos.avoscloud.ParseUser;
import com.europa.store.R;
import com.europa.store.activity.AppsActivity;
import com.europa.store.activity.RegistActivity;
import com.europa.store.tool.TextTool;
import com.europa.store.tool.ToastTool;
import com.europa.tool.ViewUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends BaseFragment {

	EditText unameEdit,pwdEdit;
	View view;
	Button loginBtn,toRegistBtn;
	public View findView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_login, null);
		unameEdit=ViewUtil.findEdit(view, R.id.unameEdit);
		pwdEdit=ViewUtil.findEdit(view, R.id.pwdEdit);
		loginBtn=(Button) view.findViewById(R.id.loginBtn);
		toRegistBtn=(Button) view.findViewById(R.id.toRegistBtn);
		return view;
	}
	@Override
	public void handle() {
		
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.loginBtn:
			if(canLogin()){
				login();
			}
			break;
		case R.id.toRegistBtn:
			startActivity(new Intent(hostActivity, RegistActivity.class));
			break;
		default:
			break;
		}
	}
	
	/**to check the prepare condition for login
	 * 
	 * @return Boolean:true,can login;false,can't login
	 */
	private Boolean canLogin(){
		if(TextTool.isEmpty(unameEdit)||TextTool.isEmpty(pwdEdit)){
			ToastTool.show(hostActivity, "用户名或密码不能为空!");
			return false;
		}
		return true;
	}
	
	private void login(){
		ParseUser.logInInBackground(TextTool.getStr(unameEdit), TextTool.getStr(pwdEdit), new LogInCallback<ParseUser>() {
			@Override
			public void done(ParseUser arg0, ParseException arg1) {
				if(arg0!=null){
					startActivity(new Intent(hostActivity, AppsActivity.class));
				}else{
					ToastTool.show(hostActivity,"登录失败！");
				}
			}
		});
	}
}
