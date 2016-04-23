package com.example.upesg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AcknActivity extends Activity  implements OnClickListener
{
	Button btn;
	TextView tv;
public String st,str;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ackn);
		Bundle b= getIntent().getExtras();
		st=b.getString("id");
		str=b.getString("sapid");

		TextView tv=(TextView)findViewById(R.id.cmpln);
		tv.setText(st);
		btn=(Button)findViewById(R.id.home);
		btn.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		if(v.getId()==R.id.home)
		{
			Intent i=new Intent(AcknActivity.this,FuncActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("sapid",str);
		startActivity(i);
			finish();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ackn, menu);
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
