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

public class SubLoc extends ListActivity  
{

	String [][]namemenu={{"Light","Fan","Charging Point","Projector","Card Reader","Hand Dryer","Air Conditioner","Others"},{"Water Storage","Water Overflow","Water Cooler Malfunction","Water Purifier","Tap Leak","Others"},{"Litter","Water Spill","Dustbin Overflow","Stinking Dustbin","Flooded Wash Basin","Flooded Toilets","Others"},{"Fight","Quarrel","Abuse","Ragging","Eve Teasing","Others"},{"Card Reader","Projector","Projector Sheet","Window","Board","Furniture","Doors","Others"},{"Laptop","Cellphone","Helmet","Copies","ID Card","File","Gadgets","Others"}};
public int m=0;
public String st="",str;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle b= getIntent().getExtras();
m=Integer.valueOf(b.getString("m"));
str=b.getString("sapid");
	st=b.getString("pblm");
		MyAdapter ad= new MyAdapter(this,android.R.layout.simple_list_item_1,namemenu[m]);
		setListAdapter(ad);
		

	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
	
		super.onListItemClick(l, v, position, id);
	 if(namemenu[m][position].equals("EXIT"))
	 {
		finish(); 
	 }
	else
	{

		
	try
	{
		st=st+"\nProblem : "+namemenu[m][position];
	Intent i= new Intent(SubLoc.this,RemarkActivity.class);
	i.putExtra("final",st);
	i.putExtra("sapid",str);
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
			t1.setText(namemenu[m][position]);
	
			}
			catch(Exception e)
			{
				Toast.makeText(SubLoc.this, "Error",3000).show();
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
