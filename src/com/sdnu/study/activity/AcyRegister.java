package com.sdnu.study.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class AcyRegister extends Activity {
	private EditText etEmail;
	private EditText etPassWord;
	private EditText etAgain;
	private TextView tvWarningEmail;
	private TextView tvWarningPassword;
	private TextView tvWarningAgain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acyregister_layout);
		initViews();
	}

	private void initViews() {
		etEmail = (EditText) this.findViewById(R.id.etEmail);
		etPassWord = (EditText) this.findViewById(R.id.etPassword);
		etAgain = (EditText) this.findViewById(R.id.etAgain);

		tvWarningEmail = (TextView) this.findViewById(R.id.tvWarningEmail);
		tvWarningPassword = (TextView) this
				.findViewById(R.id.tvWarningPassword);
		tvWarningAgain = (TextView) this.findViewById(R.id.tvWarningAgain);

		// validate(etEmail.getText().toString(),
		// etPassWord.getText().toString(),
		// etPassWord.getText().toString());

		etEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					System.out.println(etEmail.getText().toString());
					String email=etEmail.getText().toString();
					boolean b = validateEmail(email);
					System.out.println(b);
				}else{
					//setWarning(tvWarningEmail,false);
				}
				
			}
		});
		etPassWord.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				boolean b = validatePassword(etEmail.getText().toString());
				System.out.println(b);
			}

		});
		etAgain.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				boolean b = validateAgain(etEmail.getText().toString());
				System.out.println(b);
			}
		});
	}

	/**
	 * 验证用户密码确认
	 * 
	 * @param password
	 * @return
	 */
	private boolean validateAgain(String again) {
		if (again != null && etPassWord != null) {
			if(again.equals(etPassWord.getText().toString())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证用户密码
	 * 
	 * @param password
	 * @return
	 */
	private boolean validatePassword(String password) {
		return false;
	}

	/**
	 * 验证用户输入信息
	 * 
	 * @param email
	 * @param password
	 * @param again
	 * @return
	 */

	private boolean validateEmail(String email) {
		if (email != null && email.trim() != "" && !email.equals(null)) {
			System.out.println(email.length()+"109:"+isEmail(email));
			if (isEmail(email)) {
				//setWarning(tvWarningEmail, false);
				return true;
			} else {
				setWarning(tvWarningEmail, "邮箱格式有误");
			}
		} else {
			setWarning(tvWarningEmail, "邮箱不能为空");
			return false;
		}

		return false;

	}

	/**
	 * 
	 * @param tvWarning
	 * @param b
	 *            true表示显示 fals表示不现实
	 */
	private void setWarning(TextView tvWarning, boolean b) {
		if (tvWarning != null && !b) {
			tvWarning.setVisibility(View.INVISIBLE);
		}

	}

	/**
	 * 提示用户
	 * 
	 * @param tvWarning
	 * @param msg
	 */
	private void setWarning(TextView tvWarning, String msg) {
		if (tvWarning != null && msg != null) {
			tvWarning.setVisibility(View.VISIBLE);
			tvWarning.setText(msg);
		}

	}

	/**
	 * 判断邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	private boolean isEmail(String email) {
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		boolean flag = matcher.matches();
		System.out.println(flag);
		return flag;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
