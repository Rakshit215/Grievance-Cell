package com.example.upesg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatusActivity extends Activity
{
	ListView listm;
	int row;
	public String titlename;	
	public Intent i;
	public ProgressBar pb;
public String cid,cmpln;
int in;
TextView tv1,tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s);
		Bundle b= getIntent().getExtras();
		cid=b.getString("cid");
		cmpln=b.getString("cmpln");
		in=Integer.valueOf(b.getString("status"));
		tv1=(TextView)findViewById(R.id.title);
		tv2=(TextView)findViewById(R.id.cmpi);
		
pb=(ProgressBar)findViewById(R.id.pbar2);
tv1.setText(cid);
tv2.setText(cmpln);
pb.setProgress(in*34);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
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
