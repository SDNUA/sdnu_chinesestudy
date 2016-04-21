package com.sdnu.study.adapter;

import java.util.List;
import java.util.Map;

import com.sdnu.study.activity.R;
import com.sdnu.study.domain.ShortDialogItem;
import com.sdnu.study.interfaces.IOnItemOnclick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DlgListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<ShortDialogItem> list;
	private int TYPE_0 = 0;
	private int TYPE_1 = 1;
	IOnItemOnclick ionClick;
	public DlgListAdapter(Context context, List<ShortDialogItem> list,IOnItemOnclick ionClick) {
		this.mInflater = LayoutInflater.from(context);
		this.list = list;
		this.ionClick=ionClick;

	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		int type = position;
		if (type % 2 == 0) {
			return TYPE_0;
		} else {
			return TYPE_1;
		}
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		int type = getItemViewType(position);
		Holder h1 = null;
		Holder h2 = null;
		if (convertView == null) {
			if (type == TYPE_0) {
				convertView = mInflater.inflate(R.layout.left_buble_layout,
						null);
				h1 = new Holder();
				h1.dlgtext = (TextView) convertView
						.findViewById(R.id.tvDlgContent);
				convertView.setTag(h1);
			} else {
				convertView = mInflater.inflate(R.layout.right_buble_layout,
						null);
				h2 = new Holder();
				h2.dlgtext = (TextView) convertView
						.findViewById(R.id.tvDlgContent);
				convertView.setTag(h2);
			}
		} else {
			if (type == TYPE_0) {
				h1=(Holder) convertView.getTag();
			} else {
				h2=(Holder) convertView.getTag();
			}
		}
		ShortDialogItem sdt=null;
		if(type == TYPE_0){
			sdt=list.get(position);
			h1.dlgtext.setText(sdt.getChinese()+"\n"+sdt.getPinyin());
			h1.dlgtext.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ionClick.onclick(position);
				}
			});
		}else{
			sdt=list.get(position);
			h2.dlgtext.setText(sdt.getChinese()+"\n"+sdt.getPinyin());
			h2.dlgtext.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ionClick.onclick(position);
				}
			});
		}
		
		
		return convertView;
	}

	static class Holder {
		public TextView dlgtext;
	}
}
