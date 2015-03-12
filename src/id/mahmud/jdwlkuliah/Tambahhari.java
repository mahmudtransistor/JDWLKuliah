package id.mahmud.jdwlkuliah;

import id.mahmud.jdwlkuliah.adapter.SQLHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Tambahhari extends Activity {
	//Deklarasi untuk EDITTEXT
	EditText edtnmmatkul,edtkelas,edtruang,edtjam;
    //deklarasi untuk actionbar
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambahhari);
		//deklarasi warna untuk actionBar
		actionBar = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
		actionBar.setBackgroundDrawable(colorDrawable);
		actionBar.setIcon(R.drawable.ic_launcher);
		//deklarasi untuk edittext
		edtnmmatkul = (EditText)findViewById(R.id.editnmmatkul);
		edtkelas = (EditText)findViewById(R.id.editkelas);
		edtruang = (EditText)findViewById(R.id.editruang);
		edtjam = (EditText)findViewById(R.id.editjam);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tambahhari, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_simpan) {
			if(edtnmmatkul.getText().toString().trim().isEmpty()){
				Toast.makeText(getApplicationContext(),
						"Nama matkul belum diisi",
						Toast.LENGTH_SHORT).show();
				edtnmmatkul.requestFocus();
			}else if(edtkelas.getText().toString().trim().isEmpty()){
				Toast.makeText(getApplicationContext(), "Kelas belum diisi",
						Toast.LENGTH_SHORT).show();
				edtkelas.requestFocus();
			}else if(edtruang.getText().toString().trim().isEmpty()){
				Toast.makeText(getApplicationContext(), "Ruang belum disis",
						Toast.LENGTH_SHORT).show();
				edtruang.requestFocus();
			}else if(edtjam.getText().toString().trim().isEmpty()){
				Toast.makeText(getApplicationContext(), "Jam/Waktu belum diisi",
						Toast.LENGTH_SHORT).show();
				edtjam.requestFocus();
			}else{
				AlertDialog.Builder trans = new AlertDialog.Builder(Tambahhari.this);
				trans.setTitle("Pesan").setCancelable(false);
				trans.setIcon(android.R.drawable.ic_dialog_alert);
				trans.setMessage("Apakah data dengan inputan ini : "+ "\n\n" + 
		                 "Nama Matkul : " + edtnmmatkul.getText().toString() +"\n\n"+
		                 "Kelas : " + edtkelas.getText().toString()+"\n\n"+
		                 "Ruang : " + edtruang.getText().toString()+"\n\n"+
		                 "Jam/Waktu : " + edtjam.getText().toString()+"\n\n"+
		                 "Sudah benar ???");
				trans.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//aksi untuk menyimpan data jadwal baru
						SQLHelper db = new SQLHelper(getApplicationContext());
						String nmmtakul = edtnmmatkul.getText().toString();
						String kelas = edtkelas.getText().toString();
						String ruang = edtruang.getText().toString();
						String jam = edtjam.getText().toString();
						Jadwal jadwal = new Jadwal(nmmtakul,kelas,ruang,jam);
						db.addJadwal(jadwal);
						setResult(RESULT_OK);
						finish();
						
					}
				});
				trans.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					dialog.dismiss();	
					}
				});
				trans.show();
			}
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
