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

public class LocationActivity  extends ListActivity  
{
	String []namemenu={"Block","Laboratory","Library","Cit","Food Court"};
	String []screen={"BlockActivity","LabActivity","LibrActivity","CitActivity","FcActivity"};
	int[] info={R.drawable.block,R.drawable.lab,R.drawable.libr,R.drawable.cit,R.drawable.fd};
public String st="Location : ",str3="";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		MyAdapter adapter= new MyAdapter(this,android.R.layout.simple_list_item_1,namemenu);
		setListAdapter(adapter);
		Bundle b= getIntent().getExtras();
		str3=b.getString("sapid");
		

	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
	
		super.onListItemClick(l, v, position, id);
	 if(namemenu[position].equals("EXIT"))
	 {
		finish(); 
	 }
	else
	{

			String str = "com.example.upesg."+screen[position];
	try
	{
		
		st=st+namemenu[position];
		Toast.makeText(LocationActivity.this,String.valueOf(position),3000).show();
		Class c=Class.forName(str);
	Intent i= new Intent(this,c);
	i.putExtra("loc",st);
	i.putExtra("sapid",str3);
	startActivity(i);

	}
	catch(Exception e)
	{
		Toast.makeText(this,"Screen not yet Designed",Toast.LENGTH_SHORT).show();
	}

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
				Toast.makeText(LocationActivity.this, "Error",3000).show();
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
