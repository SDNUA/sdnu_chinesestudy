package com.sdnu.study.fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.sdnu.study.activity.AcyPinyinItem;
import com.sdnu.study.activity.R;
import com.sdnu.study.domain.PinyinTableItem;
import com.sdnu.study.myUtils.PullXMLUtils;

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
		
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			
	}

}
