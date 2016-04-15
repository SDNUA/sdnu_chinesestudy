package com.sdnu.study.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.model.Model;
import com.sdnu.study.myUtils.PullXMLUtils;

@SuppressLint("ResourceAsColor")
public class AcyPinyinGame extends Activity implements OnItemClickListener {
	
	
	private String letter[]=new String[]{
			"a","e","i","o","u","q","g","t","s","w","d",
			"x","h","k","j","z",
			"f","u","m","p","c","r","y","l"	
		};
	private MediaPlayer player = null;
	private StringBuffer sb=new StringBuffer();
	private String key="";
	private String url="http://192.168.88.1/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acypinyingame_layout);
		
		init();
	}
	
	
	private void init() {
		setLetters();
		GridView gridview=(GridView)findViewById(R.id.gridView1);
		List<Map<String,Object>>listltems=new ArrayList<Map<String,Object>>();
		for(int i=0;i<letter.length;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("leter",letter[i]);
			listltems.add(map);
		}
		
		SimpleAdapter adapter=new SimpleAdapter(this,listltems,R.layout.acypinyingameitem_layout,new String[]{"leter"},new int[]{R.id.letter});
		gridview.setAdapter(adapter);
		player(url);
		gridview.setOnItemClickListener(this);
	}
	/**
	 * 产生随机数
	 * @param len
	 * @return
	 */
	private int getRandomNum(int len){
		double num=Math.random();//0~1
		int rand=(int) Math.floor(len*num);
		return rand;
	}
	
	private void setLetters(){
		key=getKey();
		for(int i=0;i<key.length();i++){
			letter[getRandomNum(letter.length)]=String.valueOf(key.charAt(i));
		}
	}
	private String getKey(){
		String str="";
		List<PinyinTableItem> list=getPinYinBiaoList();
		PinyinTableItem item=list.get(getRandomNum(list.size()));
		int type=getRandomNum(4);
		switch (type) {
		case 0:
			str=item.getHanziFirstPy();
			url+=item.getHanziFirstFy();
			break;
		case 1:
			str=item.getHanziSecondPy();
			url+=item.getHanziSecondFy();
			break;
		case 2:
			str=item.getHanziThirdPy();
			url+=item.getHanziThirdFy();
			break;
		case 3:
			str=item.getHanziForthPy();
			url+=item.getHanziForthFy();
			break;

		default:
			break;
		}
		
		return str;
	}
	
	
	private List<PinyinTableItem> getPinYinBiaoList(){
		List<PinyinTableItem> list=new ArrayList<PinyinTableItem>();
		InputStream is=null;
		int id=getRandomNum(2);
		if(id==0){
			is = this.getResources().openRawResource(
				R.raw.res_pinyin_yunmu);
		}else{
			is = this.getResources().openRawResource(
				R.raw.res_pinyin_shengmu);
		}
		try {
			list = PullXMLUtils.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		view.setBackgroundColor(R.color.theme_font_color);
		sb.append(letter[position]);
		if(sb.toString().equals(key)){
			Toast.makeText(this,"成功！", 1).show();
		}
		if(sb.length()>key.length()){
			Toast.makeText(this,"失败！", 1).show();
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
				player = MediaPlayer.create(AcyPinyinGame.this, uri);
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

}
