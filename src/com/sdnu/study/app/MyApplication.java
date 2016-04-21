package com.sdnu.study.app;

import android.app.Application;
import android.content.pm.PackageManager.NameNotFoundException;

import com.sdnu.study.config.MyConfig;

public class MyApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		initGlobal();
		
	}

	private void initGlobal() {
	}
}
