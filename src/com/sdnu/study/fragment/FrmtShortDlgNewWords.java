package com.sdnu.study.fragment;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.NewWordAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.myUtils.DBUtils;

public class FrmtShortDlgNewWords extends Fragment {
	private View view=null;
	private ListView lv;
	private Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.frmtnewwords_layout,null);
		context=view.getContext();
		init();
		return view;
	}
	private void init() {
		lv=(ListView) view.findViewById(R.id.lvNewWords);
		Intent intent=getActivity().getIntent();
		int key=intent.getIntExtra("key", 1);
		DBUtils dbUtil=DBUtils.getInstance(context);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		List<NewWord> list=dbUtil.getWordsData(key);
		dbUtil.close(MyConfig.DB_NAME);
		NewWordAdapter nwadapter=new NewWordAdapter(getContext(), list);
		lv.setAdapter(nwadapter);
	}

	
	

}
