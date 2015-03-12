package id.mahmud.jdwlkuliah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class Flash extends Activity {

	protected boolean active = true;
	protected int falstime = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flash);
		//Deklarsi Untuk FlashScreen
		Thread FlashScreen = new Thread(){
			public void run(){
				try{
					int mahmud = 0;
					while(active && (mahmud<falstime)){
						sleep(200);
						if(active){
							mahmud +=200;
						}
					}
				}catch(InterruptedException e){
					
				}finally{
					finish();
					Intent trans = new Intent(getApplicationContext(),MenuUtama.class);
					startActivity(trans);
				}
			}
		};
		FlashScreen.start();
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			active = false;
			
		}
		return super.onTouchEvent(event);
	}
}
