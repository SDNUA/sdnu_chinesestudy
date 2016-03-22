package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sdnu.study.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class FrmtShengmu extends Fragment{
	
	
	private View view=null;
	private  GridView gv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.yunmubiao, null);
		init();
		return view;
	}
	
	
	private void init() {
		gv=(GridView) view.findViewById(R.id.glPinyinbiao);
		
		String arrItem[][]={{"c","阿"},{"c","阿"},{"c","阿"},{"c","阿"},
				{"b","阿"},{"c","阿"},{"d","阿"},{"a","阿"},{"a","阿"},
				{"a","阿"},{"a","阿"},{"a","阿"},{"a","阿"},{"a","阿"}};
		
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for(String str[]:arrItem){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("mChar", str[0]);
			map.put("mHanzi", str[1]);
			list.add(map);
		}
		
		SimpleAdapter sadapter=new SimpleAdapter(view.getContext(), list, R.layout.pinyinbiao_item_layout, 
				new String[]{"mChar","mHanzi"},
				new int[]{R.id.tvPinyinbiaoItem,R.id.tvPinyinbiaoHanzi});
			
		gv.setAdapter(sadapter);
	}

}
