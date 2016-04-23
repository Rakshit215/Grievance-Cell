package com.example.upesg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FuncActivity extends Activity implements OnClickListener 
{
Button b1;
Button b2;
Intent i;
public String st="";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_func);
		Bundle b= getIntent().getExtras();
		st=b.getString("sapid");
		
		b1=(Button)findViewById(R.id.file);
		b2=(Button)findViewById(R.id.check);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);

	}


	public void onClick(View v) 
	{
		if(v.getId()==R.id.file)
		{
			i=new Intent(this,LocationActivity.class);
			i.putExtra("sapid",st);
		}
		else {
			i=new Intent(this,Statuslist.class);
			i.putExtra("sapid",st);		
		}
		startActivity(i);
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.func, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
