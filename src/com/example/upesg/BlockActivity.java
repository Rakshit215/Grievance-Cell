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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class BlockActivity extends Activity implements OnClickListener/* OnCheckedChangeListener 
*/{
	public RadioGroup mrg;
	public RadioButton mr;
	private Button btn;
public String str1="",str2="",str3="aaaa",st="",str;
	public Spinner s1;
	public Spinner s2;
	int num=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block);
		Bundle b= getIntent().getExtras();
		st=b.getString("loc");
		str=b.getString("sapid");
		s1=(Spinner)findViewById(R.id.spinner1);
		s2=(Spinner)findViewById(R.id.spinner2);
		
		mrg=(RadioGroup)findViewById(R.id.radioGroup1);
//mrg.setOnCheckedChangeListener( this);
		btn=(Button)findViewById(R.id.b1);
		btn.setOnClickListener(this);
				
		ArrayAdapter ap1=ArrayAdapter.createFromResource(this, R.array.block, android.R.layout.simple_spinner_item);
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
			//	Toast.makeText(BlockActivity.this,"Spinner 1 : Unselected",3000);
				
			}
		
		}
		);	


		ArrayAdapter ap2=ArrayAdapter.createFromResource(this, R.array.floor, android.R.layout.simple_spinner_item);
		ap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s2.setAdapter(ap2);
		s2.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView arg0, View arg1,
					int arg2, long arg3) 
			{
				str2=s2.getItemAtPosition(arg2).toString();
					
			}

			@Override
			public void onNothingSelected(AdapterView arg0) 
			{
	//			Toast.makeText(BlockActivity.this,"Spinner 2 : Unselected",3000);
				
			}
		
		}
		);	

	}

	public void onClick(View v) 
	{
		 int selectedId = mrg.getCheckedRadioButtonId();
         mr = (RadioButton) findViewById(selectedId);
    str3= mr.getText().toString();

		if(v.getId()==R.id.b1)
		{
			Intent i=new Intent(BlockActivity.this,ProbActivity.class);
		st=st+"\nBlock No : "+str1+"\nFloor : "+str2+"\nRadio : "+str3;
	i.putExtra("build",st);
i.putExtra("sapid",str);
	startActivity(i);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.block, menu);
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
