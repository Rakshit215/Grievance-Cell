package com.example.upesg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread timer =new Thread()
	 	{
			public void run()
			{
			   try 
			     {
				   sleep(3000);
				   Intent i= new Intent(SplashActivity.this,LoginActivity.class);
					startActivity(i);
					finish();
			     } 
			     catch (InterruptedException e) {
					e.printStackTrace();
			     }
		
				
			}
		};
		timer.start();
	}

	}


