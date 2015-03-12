package id.mahmud.jdwlkuliah;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class PenggunaanApk extends Activity {
	 //Deklarasi Warna untuk actionBar
		ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penggunaan_apk);
		//Deklarasi Warna action bar
				actionBar = getActionBar();
				ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
				actionBar.setBackgroundDrawable(colorDrawable);
	}

}
