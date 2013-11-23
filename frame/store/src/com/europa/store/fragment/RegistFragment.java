package com.europa.store.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.europa.store.R;
import com.europa.store.activity.AppsActivity;
import com.europa.store.tool.TextTool;
import com.europa.store.tool.ToastTool;
import com.europa.tool.ViewUtil;

public class RegistFragment extends BaseFragment {

	EditText unameEdit, pwdEdit, rePwdEdit, emailEdit;
	Button registBtn;

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.registBtn:
			if(canRegist()){
				regist();
			}
			break;

		default:
			break;
		}
	}

	@Override
	public View findView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.fragment_regist, null);
		unameEdit = (EditText) view.findViewById(R.id.unameEdit);
		pwdEdit = (EditText) view.findViewById(R.id.pwdEdit);
		rePwdEdit = (EditText) view.findViewById(R.id.rePwdEdit);
		emailEdit = ViewUtil.findEdit(view, R.id.emailEdit);
		registBtn = (Button) view.findViewById(R.id.registBtn);
		return view;
	}

	@Override
	public void handle() {

	}

	/**
	 * to check the prepare condition to regist
	 * @return Boolean:true can regist;false can't regist
	 */
	public Boolean canRegist() {
		if (TextUtils.isEmpty(unameEdit.getText())
				|| TextUtils.isEmpty(pwdEdit.getText())
				|| TextUtils.isEmpty(pwdEdit.getText())
				|| TextUtils.isEmpty(rePwdEdit.getText())
				|| TextUtils.isEmpty(emailEdit.getText())){
			ToastTool.show(hostActivity,"注册信息必须填写完整");
			return false;
		}
		if(!TextUtils.equals(pwdEdit.getText(), rePwdEdit.getText())){
			ToastTool.show(hostActivity,"您输入的密码不一致.");
			return false;
		}
		if(!TextTool.isEmail(emailEdit)){
			ToastTool.show(hostActivity,"请输入正确的邮箱名");
			return false;
		}
		return true;
	}
	
	private void regist(){
		AVUser user=new AVUser();
		user.setUsername(TextTool.getStr(unameEdit));
		user.setPassword(TextTool.getStr(pwdEdit));
		user.setEmail(TextTool.getStr(emailEdit));
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(AVException arg0) {
				if(arg0==null){
					ToastTool.show(hostActivity, "恭喜您！已注册成功！");
					startActivity(new Intent(hostActivity, AppsActivity.class));
				}else{
					ToastTool.show(hostActivity, "注册失败，请重新注册！");
				}
			}
		});
	}
}
