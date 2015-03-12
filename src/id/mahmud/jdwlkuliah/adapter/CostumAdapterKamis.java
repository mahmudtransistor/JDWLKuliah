package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.List;

import id.mahmud.jdwlkuliah.Kamis;
import id.mahmud.jdwlkuliah.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CostumAdapterKamis extends ArrayAdapter<Kamis> {

	Context context;
	int layoutResourceID;
	List<Kamis>JadwalKamis;
	public CostumAdapterKamis(Context context, int resource, List<Kamis>objects) {
		super(context, resource,objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		layoutResourceID = resource;
		JadwalKamis = objects;
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
		Kamis jadwal = JadwalKamis.get(position);
		holder.nmmatkul.setText(jadwal.getNm_matkul());
		return convertView;
	}
	public void UpdateData(ArrayList<Kamis>jadwalkamis){
		this.JadwalKamis = jadwalkamis;
		notifyDataSetChanged();
	}
	//untuk menampung ViewHolder
	public static class ViewHolder{
		public TextView nmmatkul;
		
	}
	

}
