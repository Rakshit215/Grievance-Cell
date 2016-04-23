package com.example.upesg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CitActivity extends Activity implements OnClickListener 
{
Button b1;
public 	Spinner s1;
public RadioGroup mrg;
public RadioButton mr;

public String str,st,str1,str2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cit);
		Bundle b= getIntent().getExtras();
		st=b.getString("loc");
		str=b.getString("sapid");
		mrg=(RadioGroup)findViewById(R.id.rGroup2);

	s1=(Spinner)findViewById(R.id.spinner1);
		
		b1=(Button)findViewById(R.id.c1);
		b1.setOnClickListener( this);

		ArrayAdapter ap1=ArrayAdapter.createFromResource(this, R.array.citlab, android.R.layout.simple_spinner_item);
		ap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		s1.setAdapter(ap1);
		
		s1.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView arg0, View arg1,
					int arg2, long arg3) 
			{
				str1=s1.getItemAtPosition(arg2).toString();
		
			}

			@Override
			public void onNothingSelected(AdapterView arg0) 
			{
				Toast.makeText(CitActivity.this,"Spinner 1 : Unselected",3000);
				
			}
		
		}
		);	



	}
	public void onClick(View v) 
	{
		if(v.getId()==R.id.c1)
		{
			 int selectedId = mrg.getCheckedRadioButtonId();
	         mr = (RadioButton) findViewById(selectedId);
	    str2= mr.getText().toString();

st=st+"\nLab Number : "+str1+"\nFloor : "+str2;

			Intent i=new Intent(CitActivity.this,ProbActivity.class);
			i.putExtra("sapid",str);
			i.putExtra("build",st);
			
			startActivity(i);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cit, menu);
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
