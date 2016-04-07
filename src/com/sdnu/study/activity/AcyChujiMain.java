package com.sdnu.study.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class AcyChujiMain extends Activity implements OnItemClickListener {
	
	private GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acychujimain_layout);
		List<Map<String, String>> data=getData();
		SimpleAdapter sa=new SimpleAdapter(this, data, R.layout.acychujimain_item_layout, new String[]{"title"},new int[]{R.id.tvDlgMainItemTitle});
		gv=(GridView) this.findViewById(R.id.gvDlgMain);
		gv.setAdapter(sa);
		gv.setOnItemClickListener(this);
	}
	private List<Map<String, String>> getData() {
		List<Map<String, String>> data=new ArrayList<Map<String,String>>();
		Map<String, String> map=new HashMap<String, String>();
		map.put("title", "第一课");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "第一课");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "第一课");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "第一课");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		map=new HashMap<String, String>();
		map.put("title", "哈哈哈哈");
		data.add(map);
		return data;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i=new Intent(AcyChujiMain.this,AcyShortDlg.class);
		this.startActivity(i);
	}

}
