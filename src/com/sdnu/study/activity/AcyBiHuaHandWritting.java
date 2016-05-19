package com.sdnu.study.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AcyBiHuaHandWritting extends Activity {

	private WebView wb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acybihuahandwritting_layout);
		init();
	}

	private void init() {
		wb = (WebView) this.findViewById(R.id.wbView);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.loadUrl("file:///android_asset/write/index.html");
		wb.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
