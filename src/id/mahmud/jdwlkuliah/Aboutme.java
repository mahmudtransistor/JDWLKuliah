package id.mahmud.jdwlkuliah;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Aboutme extends Activity {
	TextView txtaboutme;
	//Deklarasi Warna untuk actionBar
    ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutme);
		//deklarasi warna untuk actionBar
				actionBar = getActionBar();
				ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2196f3"));
				actionBar.setBackgroundDrawable(colorDrawable);
				actionBar.setIcon(R.drawable.ic_launcher);
		txtaboutme = (TextView)findViewById(R.id.txtfacebook);
		//Deklarasi untuk File HTML
		StringBuilder html = new StringBuilder();
		html.append("<a href = 'https://www.facebook.com/mahmud.imamura/'>www.facebook.com</a>");
		txtaboutme.setText(Html.fromHtml(html.toString()));
		txtaboutme.setClickable(true);
		txtaboutme.setMovementMethod(LinkMovementMethod.getInstance());
		
	}
}
