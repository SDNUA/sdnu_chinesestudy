package com.sdnu.study.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.sdnu.study.myUtils.MyJson;
import com.sdnu.study.net.ThreadPoolUtils;
import com.sdnu.study.thread.HttpGetThread;

public class AcyPinyinItem extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acypinyinitem_layout);
		
		Intent i=getIntent();
		
		init();
	}

	private void init() {
		
		String url = "";
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
		
	}
	
	
	/*
	 * 
	 * android 主线程
	 * HttpGetThread 子线程改变不了主线程的Ui
	 * Handler 传信（传消息）
	 * 
	 */
	//Handler
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(AcyPinyinItem.this,"xxxxxxxxx",1).show();
			} else if (msg.what == 100) {
				Toast.makeText(AcyPinyinItem.this, "yyyyyyy", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				//Log.i("tag", result);
				MyJson.jsonToObj(result);
			}else{
				Toast.makeText(AcyPinyinItem.this, "000000", 1).show();
			}
		}
	};

}
