package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.sdnu.study.activity.R;

public class FrmtCharTable extends Fragment implements OnItemClickListener{
	
	
	private View view=null;
	private  GridView gv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.frmtchartable_layout, null);
		init();
		return view;
	}
	
	
	private void init() {
		gv=(GridView) view.findViewById(R.id.glPinyinbiao);
		List<Map<String, String>> data=new ArrayList<Map<String,String>>();
		Map<String, String> map=new HashMap<String, String>();
		map.put("hanzi", "一");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("hanzi", "二");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("hanzi", "三");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("hanzi", "四");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("hanzi", "五");
		data.add(map);
		
		SimpleAdapter adapter=new SimpleAdapter(getContext(),data,R.layout.frmtchartable_item_layout,new String[]{"hanzi"},new int[]{R.id.tvHanzi});
		gv.setAdapter(adapter);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			
	}

}
