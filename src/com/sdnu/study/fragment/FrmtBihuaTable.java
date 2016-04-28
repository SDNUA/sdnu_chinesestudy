package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.sdnu.study.activity.AcyBiHuaHandWritting;
import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.BihuaListAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.BiHuaItem;
import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.DBUtils;

public class FrmtBihuaTable extends Fragment implements OnItemClickListener {

	private View view = null;
	private GridView gv;
	private List<Integer> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frmtbihuatable_layout, null);
		loadData();
		init();
		return view;
	}

	private void loadData() {
		// DBUtils dbUtil = DBUtils.getInstance(getContext());
		// dbUtil.openMyDb(MyConfig.DB_NAME);
		// data = dbUtil.getBihuaList();
		// dbUtil.close(MyConfig.DB_NAME);
		data = new ArrayList<Integer>();
		int[] pics = new int[] { R.drawable.pic_dian, R.drawable.pic_heng,
				R.drawable.pic_shu, R.drawable.pic_pie, R.drawable.pic_na,
				R.drawable.pic_dian, R.drawable.pic_dian };
		for(int pic:pics){
			data.add(pic);
		}
	}

	private void init() {
		gv = (GridView) view.findViewById(R.id.glPinyinbiao);
		BihuaListAdapter adpater = new BihuaListAdapter(getContext(), data);
		gv.setAdapter(adpater);
		gv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent i=new Intent(getContext(),AcyBiHuaHandWritting.class);
		getContext().startActivity(i);
	}

}
