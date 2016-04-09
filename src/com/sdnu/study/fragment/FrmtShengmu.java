package com.sdnu.study.fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sdnu.study.activity.AcyPinyinItem;
import com.sdnu.study.activity.R;
import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.PullXMLUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class FrmtShengmu extends Fragment  implements OnItemClickListener{
	
	
	private View view=null;
	private  GridView gv;
	private List<PinyinTableItem> ptList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.yunmubiao, null);
		init();
		return view;
	}
	
	
	private void init() {
gv=(GridView) view.findViewById(R.id.glPinyinbiao);
		
		InputStream is = this.getResources().openRawResource(
				R.raw.res_pinyin_shengmu);
		try {
			ptList = PullXMLUtils.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for(PinyinTableItem item:ptList){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("mChar", item.getmChar());
			map.put("mHanzi", item.getHanziFirst());
			list.add(map);
		}
		
		SimpleAdapter sadapter=new SimpleAdapter(view.getContext(), list, R.layout.pinyinbiao_item_layout, 
				new String[]{"mChar","mHanzi"},
				new int[]{R.id.tvPinyinbiaoItem,R.id.tvPinyinbiaoHanzi});
			
		gv.setAdapter(sadapter);
		
		gv.setOnItemClickListener(this);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i=new Intent(getContext(), AcyPinyinItem.class);
		i.putExtra("pos", position);
		i.putExtra("type", 2);
		getContext().startActivity(i);

		
	}

}
