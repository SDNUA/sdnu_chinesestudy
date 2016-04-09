package com.sdnu.study.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sdnu.study.activity.AcyShortDlg;
import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.DlgListAdapter;
import com.sdnu.study.adapter.NewWordAdapter;
import com.sdnu.study.domain.NewWord;
import com.sdnu.study.domain.ShortDialogItem;

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
		List<NewWord> list=getData();
		NewWordAdapter nwadapter=new NewWordAdapter(getContext(), list);
		lv.setAdapter(nwadapter);
	}

	
	private List<NewWord> getData() {
		List<NewWord> list=new ArrayList<NewWord>();
		NewWord nw=new NewWord("你","ni","you");
		list.add(nw);
		nw=new NewWord("好","hao","good");
		list.add(nw);
		nw=new NewWord("我","wo","I");
		list.add(nw);
		return list;
	}

}
