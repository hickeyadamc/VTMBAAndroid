package edu.vt.mba.alumni.controllers.splashscreen;

import edu.vt.mba.alumni.R;
import edu.vt.mba.alumni.controllers.slidingmenu.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

}
