package com.sdnu.study.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sdnu.study.activity.R;

public class BihuaListAdapter extends BaseAdapter {
	private List<Integer> list;
	private LayoutInflater mInflater;
	public BihuaListAdapter(Context context,List<Integer> list) {
		mInflater=LayoutInflater.from(context);
		this.list=list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=mInflater.inflate(R.layout.frmtbihuatable_item_layout, null);
		ImageView ivBihuaPic=(ImageView) convertView.findViewById(R.id.ivBihuaPic);
		ivBihuaPic.setBackgroundResource(list.get(position));
		return convertView;
	}

}
