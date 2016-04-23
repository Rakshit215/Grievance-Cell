package com.example.upesg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LibrActivity extends Activity  implements OnClickListener
{
Button btn;
public String st="",str="",str1;
public RadioGroup mrg;
public RadioButton mr;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_libr);
		Bundle b= getIntent().getExtras();
		st=b.getString("loc");
		str=b.getString("sapid");

		mrg=(RadioGroup)findViewById(R.id.rGroup4);	

		btn=(Button)findViewById(R.id.li1);
		btn.setOnClickListener(this);
	
	
	}
	public void onClick(View v) 
	{
		if(v.getId()==R.id.li1)
		{
			int selectedId = mrg.getCheckedRadioButtonId();
	         mr = (RadioButton) findViewById(selectedId);
	    str1= mr.getText().toString();
st=st+"\nLibrary Floor : "+str1;

			Intent i=new Intent(LibrActivity.this,ProbActivity.class);

			i.putExtra("build",st);
			i.putExtra("sapid",str);

			startActivity(i);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.libr, menu);
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
