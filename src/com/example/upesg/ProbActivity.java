package com.example.upesg;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProbActivity  extends ListActivity  
{
	String []namemenu={"Electricity","Water","Sanitation","Misconduct","Damage","Lost Item","Others"};
	int[] info={R.drawable.power,R.drawable.water2,R.drawable.sani,R.drawable.abuses,R.drawable.lost,R.drawable.lost2,R.drawable.plus};
public String st="",str;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		MyAdapter ad= new MyAdapter(this,android.R.layout.simple_list_item_1,namemenu);
		Bundle b= getIntent().getExtras();
		st=b.getString("build");
		str=b.getString("sapid");
		setListAdapter(ad);
		

	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
	
		super.onListItemClick(l, v, position, id);
	 if(namemenu[position].equals("Others"))
	 {

			Intent in=new Intent(ProbActivity.this,RemarkActivity.class);
			in.putExtra("final",st);
			startActivity(in);
	 }
	else
	{

			Intent i=new Intent(ProbActivity.this,SubLoc.class);
		i.putExtra("pblm",st);
		i.putExtra("sapid",str);
			i.putExtra("m",String.valueOf(position));
			startActivity(i);

	}


	}

	class MyAdapter extends ArrayAdapter
	{

		public MyAdapter(Context context, int textViewResourceId,Object[] objects)
		{
			super(context, textViewResourceId, objects);
		
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View row=null;
			try
			{
			LayoutInflater li=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row=li.inflate(R.layout.activity_location,parent, false);

			
			ImageView iv=(ImageView)row.findViewById(R.id.imageView1);
			TextView t1=(TextView)row.findViewById(R.id.textView1);
			t1.setText(namemenu[position]);
	
				iv.setImageResource(info[position]);
			}
			catch(Exception e)
			{
				Toast.makeText(ProbActivity.this, "Error",3000).show();
			}
				return row;
			
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
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
