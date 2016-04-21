package com.sdnu.study.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import com.sdnu.study.activity.AcyUpdate;
import com.sdnu.study.net.MyGetFile;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class MyGetFileThread implements Runnable{

	
	private Handler hand;
	private String url;
	private MyGetFile mgf=new MyGetFile();
	public MyGetFileThread(Handler hand,String endParamerse) {
		this.hand=hand;
		// 拼接访问服务器完整的地址
		//url = Model.HTTPURL + endParamerse;
		url=endParamerse;
	}
	
	@Override
	public void run() {
		Message msg = hand.obtainMessage();
		Log.e("victor", url);
		
		try {
			InputStream is=null;
			if(mgf.doGet(url)!=null){
				HttpResponse httpResponse=mgf.doGet(url);
				is=httpResponse.getEntity().getContent();
				//System.out.println(httpResponse.getEntity().toString());
				msg.what = 200;
				msg.obj = is;
				
			}else{
				msg.what = 404;
				System.out.println(mgf.doGet(url));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.what = 404;
		}
		hand.sendMessage(msg);
	}
	
	
}
