package com.sdnu.study.myview;

import android.app.Activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sdnu.study.activity.R;

public class MySlidingMenu {
	private Activity activity;
	
	private SlidingMenu slidingMenu;
	
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
	}

}
