package com.sdnu.study.activity;

import java.util.List;

import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.DBUtils;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class AcyPinyin extends Activity {
	
	
	private GridLayout gl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinyinbiao);
		
		List<PinyinTableItem> list= getPinyinList();
		gl=(GridLayout) this.findViewById(R.id.glBiao);
		
		for(PinyinTableItem item:list){
			Button btn=new Button(this);
			btn.setWidth(LayoutParams.WRAP_CONTENT);
			btn.setHeight(LayoutParams.WRAP_CONTENT);
			btn.setText(item.getmChar());
			gl.addView(btn);
		}
		
	}
	
	private List<PinyinTableItem> getPinyinList(){
		DBUtils utils=DBUtils.getInstance(this);
		utils.open();
		List<PinyinTableItem> list=utils.findPinYinTableItem();
		utils.close();
		return list;
	}

}
