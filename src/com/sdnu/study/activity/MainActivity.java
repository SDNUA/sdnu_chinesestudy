package com.sdnu.study.activity;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sdnu.study.myview.MySlidingMenu;

public class MainActivity extends Activity implements OnClickListener {
	private MySlidingMenu slidingMenu;
	private TextView tvRumen;
	private TextView tvStart;
	private TextView tvHandWrite;
	private TextView tvSlideMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 侧滑菜单
		slidingMenu = new MySlidingMenu(this);
		slidingMenu.showSlidingMenu();
		init();

	}

	private void init() {
		tvRumen = (TextView) this.findViewById(R.id.tvPinyinbiao);
		tvStart = (TextView) this.findViewById(R.id.tvStart);
		tvHandWrite = (TextView) this.findViewById(R.id.tvHandWrite);
		tvSlideMenu = (TextView) this.findViewById(R.id.tvSlideMenu);

		tvRumen.setOnClickListener(this);
		tvStart.setOnClickListener(this);
		tvHandWrite.setOnClickListener(this);
		tvSlideMenu.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = null;
		switch (v.getId()) {
		case R.id.tvPinyinbiao:
			Intent ipinyin = new Intent(MainActivity.this, AcyPinyin.class);
			MainActivity.this.startActivity(ipinyin);
			break;
		case R.id.tvSlideMenu:
			slidingMenu.setToggle(true);
			break;
		case R.id.tvStart:
			i = new Intent(MainActivity.this, AcyChujiMain.class);
			this.startActivity(i);
			break;
		case R.id.tvHandWrite:
			i = new Intent(MainActivity.this, AcyHandWrite.class);
			this.startActivity(i);
			break;

		default:
			break;
		}

	}

}
