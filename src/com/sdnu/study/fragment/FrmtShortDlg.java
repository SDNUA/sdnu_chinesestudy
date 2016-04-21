package com.sdnu.study.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.AvoidXfermode.Mode;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.sdnu.study.activity.AcyPinyinItem;
import com.sdnu.study.activity.R;
import com.sdnu.study.adapter.DlgListAdapter;
import com.sdnu.study.config.MyConfig;
import com.sdnu.study.domain.ShortDialogItem;
import com.sdnu.study.interfaces.IOnItemOnclick;
import com.sdnu.study.model.Model;
import com.sdnu.study.myUtils.DBUtils;
import com.sdnu.study.myUtils.NetStateUtils;

public class FrmtShortDlg extends Fragment {
	private View view=null;
	private ListView lv;
	private Context context;
	private MediaPlayer player = null;
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
		final int key=intent.getIntExtra("key", 1);
		DBUtils dbUtil=DBUtils.getInstance(context);
		dbUtil.openMyDb(MyConfig.DB_NAME);
		List<ShortDialogItem> list=dbUtil.getDialogData(key);
		dbUtil.close(MyConfig.DB_NAME);
		IOnItemOnclick ionClick=new IOnItemOnclick() {
			@Override
			public void onclick(int pos) {
				System.out.println(pos);
				String url=Model.CHUJI_URL+"class_dialog/"+"class_"+key+"/"+"";
						player(url);
			}
		};
		DlgListAdapter dladapter=new DlgListAdapter(context, list,ionClick);
		lv.setAdapter(dladapter);
		
	}
	private void player(final String url) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Uri uri = Uri.parse(url);
				boolean flag = NetStateUtils
						.isNetworkAvailable(getContext());
				if (flag) {
					player = MediaPlayer.create(getContext(), uri);
					player.start();
				} else {
					Message msg = new Message();
					msg.what = 404;
					msg.obj = "网络异常";
					hand.sendMessage(msg);
				}

			}
		}).start();
	}
	@Override
	public void onPause() {
		super.onPause();
		if (player != null) {
			player.stop();
		}
	}
	Handler hand = new Handler() {
		@SuppressLint("ShowToast")
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(getContext(), msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
			}

		};
	};
	

}
