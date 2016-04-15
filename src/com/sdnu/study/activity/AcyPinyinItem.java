package com.sdnu.study.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.model.Model;
import com.sdnu.study.myUtils.NetStateUtils;
import com.sdnu.study.myUtils.PullXMLUtils;

public class AcyPinyinItem extends Activity implements OnClickListener {

	private Intent intent;
	private List<PinyinTableItem> list;
	private MediaPlayer player = null;

	private ImageView ivMcharSounds;
	private TextView tvMchar;

	private TextView tvHanziFirst;
	private TextView tvHanziFirstPy;
	private TextView tvHanziSecond;
	private TextView tvHanziSecondPy;
	private TextView tvHanziThird;
	private TextView tvHanziThirdPy;
	private TextView tvHanziForth;
	private TextView tvHanziForthPy;

	private ImageView ivHanziFirstSounds;
	private ImageView ivHanziSecondSounds;
	private ImageView ivHanziThirdSounds;
	private ImageView ivHanziForthSounds;

	private String charDir = null;
	private String char1 = null;
	private String char2 = null;
	private String char3 = null;
	private String char4 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acypinyinitem_layout);

		intent = getIntent();

		init();
	}

	private void init() {
		tvMchar = (TextView) this.findViewById(R.id.tvMchar);

		tvHanziFirst = (TextView) this.findViewById(R.id.tvHanziFirst);
		tvHanziFirstPy = (TextView) this.findViewById(R.id.tvHanziFirstPy);

		tvHanziSecond = (TextView) this.findViewById(R.id.tvHanziSecond);
		tvHanziSecondPy = (TextView) this.findViewById(R.id.tvHanziSecondPy);

		tvHanziThird = (TextView) this.findViewById(R.id.tvHanziThird);
		tvHanziThirdPy = (TextView) this.findViewById(R.id.tvHanziThirdPy);

		tvHanziForth = (TextView) this.findViewById(R.id.tvHanziForth);
		tvHanziForthPy = (TextView) this.findViewById(R.id.tvHanziForthPy);

		ivMcharSounds = (ImageView) this.findViewById(R.id.ivMcharSounds);
		ivHanziFirstSounds = (ImageView) this
				.findViewById(R.id.ivHanziFirstSounds);
		ivHanziSecondSounds = (ImageView) this
				.findViewById(R.id.ivHanziSecondSounds);
		ivHanziThirdSounds = (ImageView) this
				.findViewById(R.id.ivHanziThirdSounds);
		ivHanziForthSounds = (ImageView) this
				.findViewById(R.id.ivHanziForthSounds);

		int pos = intent.getIntExtra("pos", 0);
		int type = intent.getIntExtra("type", 1);
		InputStream is = null;
		if (type == 1) {
			is = this.getResources().openRawResource(R.raw.res_pinyin_yunmu);
		} else if (type == 2) {
			is = this.getResources().openRawResource(R.raw.res_pinyin_shengmu);
		}
		try {
			list = PullXMLUtils.parse(is);
			charDir = list.get(pos).getmChar();
			tvMchar.setText(charDir);
			char1 = list.get(pos).getHanziFirst();
			tvHanziFirst.setText(char1);
			char2 = list.get(pos).getHanziSecond();
			tvHanziSecond.setText(char2);
			char3 = list.get(pos).getHanziThird();
			tvHanziThird.setText(char3);
			char4 = list.get(pos).getHanziForth();
			tvHanziForth.setText(char4);
			tvHanziFirstPy.setText(list.get(pos).getHanziFirstPy());
			tvHanziSecondPy.setText(list.get(pos).getHanziSecondPy());
			tvHanziThirdPy.setText(list.get(pos).getHanziThirdPy());
			tvHanziForthPy.setText(list.get(pos).getHanziForthPy());

			ivMcharSounds.setOnClickListener(this);
			ivHanziFirstSounds.setOnClickListener(this);
			ivHanziSecondSounds.setOnClickListener(this);
			ivHanziThirdSounds.setOnClickListener(this);
			ivHanziForthSounds.setOnClickListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 播放音频
	 * 
	 * @param url
	 */

	private void player(final String url) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Uri uri = Uri.parse(url);
				boolean flag = NetStateUtils
						.isNetworkAvailable(AcyPinyinItem.this);
				if (flag) {
					player = MediaPlayer.create(getApplicationContext(), uri);
					player.start();
				} else {
					Message msg = new Message();
					msg.what = 404;
					msg.obj = "网络异常";
					hand.sendMessage(msg);
				}

			}
		}).start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (player != null) {
			player.stop();
		}
	}

	@Override
	public void onClick(View v) {
		String url = null;
		switch (v.getId()) {
		case R.id.ivMcharSounds:
			url = Model.YINPIN_URL + charDir + "/" + char1 + ".wav";
			player(url);
			break;
		case R.id.ivHanziFirstSounds:
			url = Model.YINPIN_URL + charDir + "/" + char1 + ".wav";
			player(url);
			break;
		case R.id.ivHanziSecondSounds:
			url = Model.YINPIN_URL + charDir + "/" + char2 + ".wav";
			player(url);
			break;
		case R.id.ivHanziThirdSounds:
			url = Model.YINPIN_URL + charDir + "/" + char3 + ".wav";
			player(url);
			break;
		case R.id.ivHanziForthSounds:
			url = Model.YINPIN_URL + charDir + "/" + char4 + ".wav";
			player(url);
			break;

		default:
			break;
		}
	}

	Handler hand = new Handler() {
		@SuppressLint("ShowToast")
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(AcyPinyinItem.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
			}

		};
	};
}
