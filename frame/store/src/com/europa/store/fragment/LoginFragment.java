package com.europa.store.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.europa.store.R;
import com.europa.store.activity.AppsActivity;
import com.europa.store.activity.RegistActivity;
import com.europa.store.bean.User;
import com.europa.store.tool.TextTool;
import com.europa.store.tool.ToastTool;
import com.europa.tool.ViewUtil;

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
		AVUser.logInInBackground(TextTool.getStr(unameEdit), TextTool.getStr(pwdEdit), new LogInCallback<AVUser>() {
			@Override
			public void done(AVUser arg0, AVException arg1) {
				if(arg0!=null){
					if(userHelper.getUserByUsername(arg0.getUsername())==null){
						User user=new User();
						user.setUsername(arg0.getUsername());
						user.setPwd(TextTool.getStr(pwdEdit));
						user.setEmail(arg0.getEmail());
						user.setAutologin(1);
						userHelper.insertUser(user);
					}
					saveUserName(arg0.getUsername());
					startActivity(new Intent(hostActivity, AppsActivity.class));
				}else{
					ToastTool.show(hostActivity,"登录失败！");
				}
			}
		});
	}
	
}
