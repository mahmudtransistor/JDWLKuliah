package id.mahmud.jdwlkuliah;

import java.util.ArrayList;

import id.mahmud.jdwlkuliah.adapter.CostumAdpaterDetailHari;
import id.mahmud.jdwlkuliah.adapter.SQLHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class DetailHari extends Activity {
	//Deklarasi untuk Listview
	ListView listView;
	ImageView btntambah;
	CostumAdpaterDetailHari adapter;
	SQLHelper db;
	ArrayList<Jadwal>Jadwalhari;
	private static final int REG_EDIT = 2;
	private static final int REG_SIMPAN = 1 ;
	
	//Deklarasi Warna untuk actionBar
    ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_hari);
		//deklarasi warna untuk actionBar
		actionBar = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
		actionBar.setBackgroundDrawable(colorDrawable);
		actionBar.setIcon(R.drawable.ic_launcher);
		//Deklarasi untuk listview
		listView = (ListView)findViewById(R.id.list_detail_hari);
		btntambah = (ImageView)findViewById(R.id.imagebtntambah);
		btntambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//perintah untuk memanggil Inten Tambah data 
				Intent trans = new Intent(getApplicationContext(),Tambahhari.class);
				startActivityForResult(trans, REG_SIMPAN);
			}
		});
		//Deklrasi untuk database SQLite
		db = new SQLHelper(getApplicationContext());
		setAdapterSaya();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//pesan jika data di tambah pada listview
		if(requestCode == REG_SIMPAN && resultCode == RESULT_OK){
			Toast.makeText(getApplicationContext(), "Data berhasil di tambah",
					Toast.LENGTH_LONG).show();
			setAdapterSaya();
		}
		//pesan jika data berhasil di edit
		if(requestCode == REG_EDIT && resultCode == RESULT_OK){
			Toast.makeText(getApplicationContext(), "Data berhasil diedit",
					Toast.LENGTH_LONG).show();
		setAdapterSaya();
		}
	}
	private void setAdapterSaya(){
		Jadwalhari = db.getAllJadwals();
	    adapter = new CostumAdpaterDetailHari(getApplicationContext(),R.layout.list_detail_hari,
				Jadwalhari);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			Jadwal jadwal = Jadwalhari.get(position);
			AlertDialog.Builder trans = new AlertDialog.Builder(DetailHari.this);
			trans.setTitle("Detail").setCancelable(false);
			trans.setIcon(R.drawable.ic_detail);
			trans.setMessage("Nama Matkul : " + jadwal.getNm_matkul() +"\n\n"+
			                "Kelas : " + jadwal.getKelas()+"\n\n"+
					        "Ruang : " + jadwal.getRuang()+"\n\n"+
			                "Jam/Waktu : " + jadwal.getJam());
			trans.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			trans.show();
			
			}
		});
		//perintah untuk hapus data
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Jadwal jadwal = Jadwalhari.get(position);
				AlertDialog.Builder trans = new AlertDialog.Builder(DetailHari.this);
				trans.setTitle("Delete").setCancelable(false);
				trans.setIcon(R.drawable.ic_hapus);
				trans.setMessage("Anda yakin ingin menghapus Jadwal : " + jadwal.getNm_matkul().toString());
				trans.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						db.DeleteJadwal(jadwal);
						setAdapterSaya();
					}
				});
				trans.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					dialog.dismiss();	
					}
				});
				trans.show();
				
				return false;
			}
		});
	}
	
}
