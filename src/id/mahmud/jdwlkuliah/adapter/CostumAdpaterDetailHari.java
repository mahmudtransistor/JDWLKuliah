package id.mahmud.jdwlkuliah.adapter;

import id.mahmud.jdwlkuliah.Jadwal;
import id.mahmud.jdwlkuliah.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CostumAdpaterDetailHari extends ArrayAdapter<Jadwal>  {
	Context context;
	int layoutResourceID;
	List<Jadwal>jadwals;
	public CostumAdpaterDetailHari(Context context, int Resource, List<Jadwal>obejcts){
		super(context, Resource, obejcts);
		this.context = context;
		layoutResourceID = Resource;
		jadwals = obejcts;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Cek VIew nya 
		ViewHolder holder = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_detail_hari, null);
			holder = new ViewHolder();
			holder.nmmatkul = (TextView)convertView.findViewById(R.id.txtnamamatkul);
			convertView.setTag(holder);
		}
		holder = (ViewHolder)convertView.getTag();
		//Dapatkan Item berdasarkan Posisi
		Jadwal jadwal = jadwals.get(position);
		holder.nmmatkul.setText(jadwal.getNm_matkul());
		return convertView;
	}
	public void UpdateData(ArrayList<Jadwal>jadwals){
		this.jadwals = jadwals;
		notifyDataSetChanged();
	}
	//untuk menampung ViewHolder
	public static class ViewHolder{
		public TextView nmmatkul;
		
	}
}
