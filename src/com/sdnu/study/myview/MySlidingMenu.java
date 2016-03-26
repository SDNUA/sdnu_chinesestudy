package com.sdnu.study.myview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sdnu.study.activity.AcyLogin;
import com.sdnu.study.activity.MainActivity;
import com.sdnu.study.activity.R;
import com.sdnu.study.config.MyConfig;

public class MySlidingMenu {
	private Activity activity;
	
	private SlidingMenu slidingMenu;
	private ListView lv;
	private TextView tvUserName;
	
	public MySlidingMenu(Activity activity) {
		this.activity = activity;
		
	}
	
	public void showSlidingMenu() {
		slidingMenu = new SlidingMenu(activity);
												
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// slidingMenu.setShadowWidthRes(R.dimen.sliding_menu_ShadowWidth);
		// slidingMenu.setShadowDrawable(R.drawable.ic_launcher);

		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.slidingmenu);
		
		lv=(ListView) activity.findViewById(R.id.lv_leftslider);
		tvUserName=(TextView) activity.findViewById(R.id.tvUserName);
    	String username =tvUserName.getText().toString();
		List<HashMap<String, String>> list=getList();
		
		SimpleAdapter sa=new SimpleAdapter(activity, list, R.layout.slidingmenu_item, 
				new String[]{"tvitem"}, new int[]{R.id.tv_slider_item_title});
		lv.setAdapter(sa);
		
		if(username.trim().equals(MyConfig.NO_LOGIN)){
    		tvUserName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent ilogin=new Intent(activity, AcyLogin.class);
					activity.startActivity(ilogin);
				}
			});
    	}
		
		
	}

	private List<HashMap<String, String>> getList() {
		// TODO Auto-generated method stub
		
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("tvitem", "我的主页");
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("tvitem", "我的主页");
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("tvitem", "我的主页");
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("tvitem", "我的主页");
		list.add(map);
		 
		 
		return list;
	}
	
	

	

}
