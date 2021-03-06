package com.func.kikbug;

import com.local.data.UserInfo;
import com.net.data.KikLoginTask;
import com.net.data.KikLoginTask.LoginListener;
import com.squareup.picasso.Picasso;
import com.view.util.SysCall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	private static final String TAG = FirstActivity.class.getName();
	private View loginBu, registeBu;
	private ImageView background;
	private EditText nameEdit, pwEdit;
	private View moocView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		nameEdit = (EditText) findViewById(R.id.nameEdit);
		pwEdit = (EditText) findViewById(R.id.pwEdit);
		initBackground();
		initLoginBu();
		initRegisterBu();
		initMoocView();
	}

	private void initMoocView() {
		moocView = findViewById(R.id.moocView);
		moocView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FirstActivity.this,
						MoocActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initBackground() {
		background = (ImageView) findViewById(R.id.background);
		Picasso.with(this).load(R.drawable.today_background).resize(480, 800)
				.into(background);
	}

	private void initRegisterBu() {
		registeBu = findViewById(R.id.registeBu);
		registeBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FirstActivity.this,
						RegisteActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initLoginBu() {
		loginBu = findViewById(R.id.loginBu);
		nameEdit.setText(UserInfo.getInstance().getAccount());
		pwEdit.setText(UserInfo.getInstance().getPassword());
		loginBu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String account = nameEdit.getText().toString();
				final String pw = pwEdit.getText().toString();
				if (account.equals("")) {
					SysCall.toast(getContext(), "账号不能为空");
					return;
				}
				KikLoginTask kiklogintask = new KikLoginTask(account, pw);
				kiklogintask.setLoginListener(new LoginListener() {
					@Override
					public void result(int code, int msg) {
						if (KikLoginTask.CODE_SUCCESS == code) {
							// login success
							UserInfo.getInstance().setAccount(account);
							UserInfo.getInstance().setPassword(pw);
							Intent intent = new Intent(FirstActivity.this,
									MainActivity.class);
							startActivity(intent);
							finish();
						} else if (KikLoginTask.CODE_VERIFY == code) {
							SysCall.toast(getContext(), "密码错误");
							return;
						} else if (KikLoginTask.CODE_ACCOUNT == code) {
							switch (msg) {
							case KikLoginTask.MSG_MISSING:
								SysCall.toast(getContext(), "用户名不存在");
								break;
							case KikLoginTask.MSG_ACTIVATE:
								SysCall.toast(getContext(), "账号未激活");
								break;
							case KikLoginTask.MSG_FORBIDDEN:
								SysCall.toast(getContext(), "账号被禁用");
								break;
							default:
								SysCall.toast(getContext(), "错误代码：" + code
										+ "-" + msg);
								break;
							}
						}
					}
				});
				kiklogintask.execute("");
			}
		});
	}

	private Context getContext() {
		return FirstActivity.this;
	}
}
