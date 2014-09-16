package com.lee.app;

import java.util.Map;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.lee.service.LoginService;

public class Activity000 extends ActBase {
	
	private EditText mEtUserName;
	private EditText mEtPassWord;
	
	private CheckBox mCbNote;
	private Button mBtnLogin;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity000);
		initView();
		register();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Map<String, String> map = LoginService.getUserInfo(this);
		if(null != map){
			mEtUserName.setText(map.get("username"));
			mEtPassWord.setText(map.get("password"));
		}
	}

	private void initView() {
		mEtUserName = (EditText)findViewById(R.id.et_user_name);
		mEtPassWord = (EditText)findViewById(R.id.et_pass_word);
		
		mCbNote = (CheckBox)findViewById(R.id.cb_note);
		mBtnLogin = (Button)findViewById(R.id.btn_login);
		mCbNote.setEnabled(false);
	}
	
	private void register() {
		mCbNote.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO
			}
		});
		
		mBtnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = mEtUserName.getText().toString();
				String password = mEtPassWord.getText().toString();
				// ① 验证用户名和密码是否为空
				if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
					Toast.makeText(Activity000.this, "输入不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				// ② 是否保存密码
				if(mCbNote.isChecked()){
					LoginService.saveUserInfo(Activity000.this, username, password);
				}else{
					LoginService.saveUserInfo(Activity000.this, null, null);
				}
				// ③ 登陆
				if("123".equals(username) && "123".equals(password)){
					Toast.makeText(Activity000.this, "登陆成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Activity000.this, "登陆失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
