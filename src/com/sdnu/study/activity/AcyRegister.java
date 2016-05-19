package com.sdnu.study.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sdnu.study.net.ThreadPoolUtils;
import com.sdnu.study.thread.HttpPostThread;

import android.app.Activity;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AcyRegister extends Activity {
	private EditText etEmail;
	private EditText etPassWord;
	private EditText etAgain;
	private TextView tvWarningEmail;
	private TextView tvWarningPassword;
	private TextView tvWarningAgain;
	private Button btnValidate;
	private boolean bEmail =false;
	private boolean bPasswprd =false;
	private boolean bAgain =false;
	
	
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
		btnValidate = (Button) this.findViewById(R.id.btnConfirm);

		tvWarningEmail = (TextView) this.findViewById(R.id.tvWarningEmail);
		tvWarningPassword = (TextView) this
				.findViewById(R.id.tvWarningPassword);
		tvWarningAgain = (TextView) this.findViewById(R.id.tvWarningAgain);

		
		etEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					setWarning(tvWarningEmail, false);
					//System.out.println(etEmail.getText().toString());
					String email = etEmail.getText().toString();
					bEmail = validateEmail(email);
				}else{
					setWarning(tvWarningEmail, false);
				}

			}
		});
		etPassWord.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					setWarning(tvWarningPassword, false);
					bPasswprd = validatePassword(etPassWord.getText().toString());
				}else{
					setWarning(tvWarningPassword, false);
				}
			}

		});
		etAgain.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					setWarning(tvWarningAgain, false);
					//bAgain = validateAgain(etAgain.getText().toString());
				}else{
					setWarning(tvWarningAgain, false);
				}
			}
		});
		
		btnValidate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				confirm();
				
			}
		});
		
	}
	/**
	 * 确认信息
	 */
	private void confirm() {
		String email=null;
		String password=null;
		String again=null;
		if(!bEmail){
			etAgain.setText("");
			etPassWord.setText("");
			etEmail.setText("");
			setWarning(tvWarningEmail, "请输入邮箱");
			return ;
		}else{
			email=etEmail.getText().toString();
		}
		
		if(!bPasswprd){
			etAgain.setText("");
			etPassWord.setText("");
			setWarning(tvWarningPassword, "请输入密码");
			return ;
		}else{
			password=etPassWord.getText().toString();
		}
		bAgain = validateAgain(etAgain.getText().toString());
		if(!bAgain){
			etAgain.setText("");
			//etPassWord.setText("");
			setWarning(tvWarningAgain, "请确认密码");
			return ;
		}else{
			again=etAgain.getText().toString();
		}
		register(email,again);
	}
	private void register(String email,String password) {
		if(email!=null&&password!=null){
			String value="email:"+email+",password:"+password;
			String url="";
			ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));
		}
		
	}
	
	Handler hand=new Handler(){
		
	};

	/**
	 * 验证用户密码确认
	 * 
	 * @param password
	 * @return
	 */
	private boolean validateAgain(String again) {
		System.out.println("again:"+again);
		if (again != null && etPassWord != null) {
			if (again.equals(etPassWord.getText().toString())) {
				return true;
			} else {
				setWarning(tvWarningAgain, "两次输入不一致");
				return false;
			}
		} else {
			setWarning(tvWarningAgain, "不能为空！");
			return false;
		}

	}

	/**
	 * 验证用户密码
	 * 
	 * @param password
	 * @return
	 */
	private boolean validatePassword(String password) {
		System.out.println("password:"+password);
		if (password != null) {
			if (isPassword(password)) {
				return true;
			} else {
				setWarning(tvWarningPassword, "密码长度5-16");
				return false;
			}
		} else {
			setWarning(tvWarningPassword, "不能为空！");
			return false;
		}

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
			// System.out.println(email.length()+"109:"+isEmail(email));
			if (isEmail(email)) {
				// setWarning(tvWarningEmail, false);
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
		// System.out.println(flag);
		return flag;
	}

	private boolean isPassword(String password) {
		Pattern pattern = Pattern.compile("^\\d{5,16}$");
		Matcher matcher = pattern.matcher(password);
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
