package com.sdnu.study.fragment;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.DlgListAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.ShortDialogItem;
import com.sdnu.study.myUtils.DBUtils;

public class FrmtShortDlg extends Fragment {
	private View view=null;
	private ListView lv;
	private Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.frmtshortdialog,null);
		context=view.getContext();
		init();
		return view;
	}
	private void init() {
		lv=(ListView) view.findViewById(R.id.lvDlgList);
		Intent intent=getActivity().getIntent();
		int key=intent.getIntExtra("key", 1);
		DBUtils dbUtil=DBUtils.getInstance(context);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		List<ShortDialogItem> list=dbUtil.getDialogData(key);
		dbUtil.close(MyConfig.DB_NAME);
		DlgListAdapter dladapter=new DlgListAdapter(context, list);
		lv.setAdapter(dladapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("     ");
				Toast.makeText(getContext(), ""+position, 1).show();
			}
		});
	}

	
	

}
