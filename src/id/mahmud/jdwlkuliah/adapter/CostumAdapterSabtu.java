package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.List;
import id.mahmud.jdwlkuliah.R;
import id.mahmud.jdwlkuliah.Sabtu;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CostumAdapterSabtu extends ArrayAdapter<Sabtu> {

	Context context;
	int layoutResourceID;
	List<Sabtu>jadwalSabtu;
	public CostumAdapterSabtu(Context context, int resource, List<Sabtu>objects) {
		super(context, resource,objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		layoutResourceID = resource;
		jadwalSabtu = objects;
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
		Sabtu jadwal = jadwalSabtu.get(position);
		holder.nmmatkul.setText(jadwal.getNm_matkul());
		return convertView;
	}
	public void UpdateData(ArrayList<Sabtu>jadwalsabtu){
		this.jadwalSabtu = jadwalsabtu;
		notifyDataSetChanged();
	}
	//untuk menampung ViewHolder
	public static class ViewHolder{
		public TextView nmmatkul;
		
	}
	

}
