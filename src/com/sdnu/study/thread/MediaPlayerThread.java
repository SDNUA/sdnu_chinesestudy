package com.sdnu.study.thread;

import com.sdnu.study.myUtils.NetStateUtils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

public class MediaPlayerThread implements Runnable{
	private static  MediaPlayerThread playerThread=null;
	private static Context context=null;
	private static Handler hand=null;
	private static String url=null;
	private static MediaPlayer player = null;
	private MediaPlayerThread() {
		
	}
	public static MediaPlayerThread getInstance(Context ctx, Handler h,String u){
		context=ctx;
		hand=h;
		url=u;
		if(playerThread==null){
			playerThread=new MediaPlayerThread();
		}
		return playerThread;
	}
	
	@Override
	public void run() {
		play();
	}
	
	
	private void play() {
		Uri uri = Uri.parse(url);
		boolean flag = NetStateUtils
				.isNetworkAvailable(context);
		Message msg = new Message();
		System.out.println(flag);
		if (flag) {
			player = MediaPlayer.create(context, uri);
			if(player!=null){
				player.start();
			}else{
				msg.what = 404;
				msg.obj = "出现异常";
			}
		} else {
			msg.what = 404;
			msg.obj = "网络异常";
		}
		hand.sendMessage(msg);

	}
	
	public static void stopPlayer(){
		if(player!=null){
			player.stop();
		}
		
	}
	
	

}
