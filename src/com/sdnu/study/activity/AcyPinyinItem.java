package com.sdnu.study.activity;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.PullXMLUtils;

public class AcyPinyinItem extends Activity implements OnClickListener{

	private TextView tvMchar;
	private Intent intent;
	private List<PinyinTableItem> list;
	private ImageView ivMcharSounds;
	private MediaPlayer player=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acypinyinitem_layout1);

		intent = getIntent();

		init();
	}

	private void init() {
		tvMchar = (TextView) this.findViewById(R.id.tvMchar);
		ivMcharSounds= (ImageView) this.findViewById(R.id.ivMcharSounds);
		int pos = intent.getIntExtra("pos", 0);
		InputStream is = this.getResources().openRawResource(
				R.raw.res_pinyin_yunmu);
		
		try {
			list = PullXMLUtils.parse(is);
			tvMchar.setText(list.get(pos).getmChar());
			ivMcharSounds.setOnClickListener(this);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 播放音频
	 * @param url
	 */
	
	private void player(final String url){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Uri uri = Uri.parse(url);
				player = MediaPlayer.create(AcyPinyinItem.this, uri);
				player.start();
			}
		}).start();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(player!=null){
			player.stop();
		}
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.ivMcharSounds:
			String url = "http://192.168.88.1/better/images/voice/a1.mp3";
			player(url);
			break;

		default:
			break;
		}
	}


}
