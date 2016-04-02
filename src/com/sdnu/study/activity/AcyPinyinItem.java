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
import android.widget.ImageView;
import android.widget.TextView;

import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.model.Model;
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
		InputStream is = this.getResources().openRawResource(
				R.raw.res_pinyin_yunmu);

		try {
			list = PullXMLUtils.parse(is);
			tvMchar.setText(list.get(pos).getmChar());
			tvHanziFirst.setText(list.get(pos).getHanziFirst());
			tvHanziSecond.setText(list.get(pos).getHanziSecond());
			tvHanziThird.setText(list.get(pos).getHanziThird());
			tvHanziForth.setText(list.get(pos).getHanziForth());
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
				player = MediaPlayer.create(AcyPinyinItem.this, uri);
				player.start();
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
			url = Model.YINPIN_URL + "a1.mp3";
			player(url);
			break;
		case R.id.ivHanziFirstSounds:
			url = Model.YINPIN_URL + "a1.mp3";
			player(url);
			break;
		case R.id.ivHanziSecondSounds:
			url = Model.YINPIN_URL + "a1.mp3";
			player(url);
			break;
		case R.id.ivHanziThirdSounds:
			url = Model.YINPIN_URL + "a1.mp3";
			player(url);
			break;
		case R.id.ivHanziForthSounds:
			url = Model.YINPIN_URL + "a1.mp3";
			player(url);
			break;

		default:
			break;
		}
	}

}
