package id.mahmud.jdwlkuliah;

import java.util.ArrayList;

import id.mahmud.jdwlkuliah.adapter.CostumAdapterRabu;
import id.mahmud.jdwlkuliah.adapter.DatabaseRabu;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class DetailRabu extends Activity {
	//Deklarasi untuk Listview
	ListView listView;
	ImageView btntambah;
	CostumAdapterRabu adapter ;
	DatabaseRabu db;
	ArrayList<Rabu>Jadwalhari;
	private static final int REG_EDIT = 2;
	private static final int REG_SIMPAN = 1 ;
	
	//Deklarasi Warna untuk actionBar
    ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_rabu);
		actionBar = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
		actionBar.setBackgroundDrawable(colorDrawable);
		actionBar.setIcon(R.drawable.ic_launcher);
		//Deklarasi untuk listview
		listView = (ListView)findViewById(R.id.list_detail_Rabu);
		btntambah = (ImageView)findViewById(R.id.imagebtntambah);
		btntambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans = new Intent(getApplicationContext(),TambahRabu.class);
				startActivityForResult(trans, REG_SIMPAN);
			}
		});
		db = new DatabaseRabu(getApplicationContext());
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
		Jadwalhari = db.getAllRabu();
	    adapter = new CostumAdapterRabu(getApplicationContext(),R.layout.list_detail_hari,
				Jadwalhari);
		listView.setAdapter(adapter);
		//Perintah untuk OnClik data di listview
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			//perintah untuk membuat pesan Detail apabila LIstview di Clik
				Rabu rabu = Jadwalhari.get(position);
				AlertDialog.Builder trans = new AlertDialog.Builder(DetailRabu.this);
				trans.setTitle("Detail").setCancelable(false);
				trans.setIcon(R.drawable.ic_detail);
				trans.setMessage("Nama Matkul : " + rabu.getNm_matkul().toString()+"\n\n"+
				                 "Kelas : " +  rabu.getKelas().toString()+"\n\n"+
						         "Ruang : " + rabu.getRuang().toString()+"\n\n"+
				                 "Jam/Waktu : " + rabu.getJam().toString());
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
		//Peintah untuk mengahapus data di database selasa
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Rabu rabu = Jadwalhari.get(position);
				AlertDialog.Builder trans = new AlertDialog.Builder(DetailRabu.this);
				trans.setTitle("Hapus").setCancelable(false);
				trans.setIcon(R.drawable.ic_hapus);
				trans.setMessage("Apakah anda yakin ingin menghapus data ini : " + 
						rabu.getNm_matkul().toString());
				trans.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						db.DeleteRabu(rabu);
						setAdapterSaya();
					}
				} );
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
