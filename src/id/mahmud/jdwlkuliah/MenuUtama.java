package id.mahmud.jdwlkuliah;

import id.mahmud.jdwlkuliah.adapter.CostumAdapterhari;
import id.mahmud.jdwlkuliah.adapter.List_Hari;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuUtama extends Activity {
	//Deklarasi Listview
	ListView listView;
	//Deklarasi untuk menu utama pada listview
	String[]namamenu;
	//TypedArray iconmenu;
	private List<List_Hari>list;
	private CostumAdapterhari adapter;
    //Deklarasi Warna untuk actionBar
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_utama);
		//Deklarasi Warna action bar
		actionBar = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
		actionBar.setBackgroundDrawable(colorDrawable);
		//Deklarasi untuk lisview
		listView = (ListView)findViewById(R.id.list_menuutama);
		//Deklarsi untu nama menu dan icon menu
		namamenu = getResources().getStringArray(R.array.nama_hari);
		//iconmenu = getResources().obtainTypedArray(R.array.icon_hari);
		list = new ArrayList<List_Hari>();
		for (int i = 0; i < namamenu.length; i++) {
			List_Hari menu = new List_Hari(namamenu[i],0);
			list.add(menu);
		}
		//iconmenu.recycle();
		adapter = new CostumAdapterhari(getApplicationContext(), list);
		listView.setAdapter(adapter);
		//perintah untuk onClik pada Listview
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			if(position == 0){
				Intent trans = new Intent(getApplicationContext(),DetailHari.class);
				startActivity(trans);
			}else if(position == 1){
				Intent trans = new Intent(getApplicationContext(),DetailSelasa.class);
				startActivity(trans);
			}else if(position == 2){
				Intent trans = new Intent(getApplicationContext(),DetailRabu.class);
				startActivity(trans);
			}else if(position == 3){
				Intent trans = new Intent(getApplicationContext(),DetailKamis.class);
				startActivity(trans);
			}else if(position == 4){
				Intent trans = new Intent(getApplicationContext(),DetailJumat.class);
				startActivity(trans);
			}else if(position == 5){
				Intent trans = new Intent(getApplicationContext(),DetailSabtu.class);
				startActivity(trans);
			}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_utama, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent trans = new Intent(getApplicationContext(),Aboutme.class);
			startActivity(trans);
			return true;
		}else if(id== R.id.action_penggunaan){
			Intent trans = new Intent(getApplicationContext(),PenggunaanApk.class);
			startActivity(trans);
		}
		return super.onOptionsItemSelected(item);
	}
}
