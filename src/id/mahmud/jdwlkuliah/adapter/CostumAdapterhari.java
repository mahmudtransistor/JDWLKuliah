package id.mahmud.jdwlkuliah.adapter;

import id.mahmud.jdwlkuliah.R;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CostumAdapterhari extends BaseAdapter {
	Context context;
	List<List_Hari>list_hari;
	public CostumAdapterhari(Context context, List<List_Hari>list_hari){
		super();
		this.context = context;
		this.list_hari = list_hari;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_hari.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list_hari.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return list_hari.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_hari, null);
			
		}
		List_Hari hari = list_hari.get(position);
		TextView namahari = (TextView)convertView.findViewById(R.id.namahari);
		namahari.setText(hari.getNama_hari());
		return convertView;
	}
	

}
