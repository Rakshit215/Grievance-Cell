package com.example.upesg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomList extends BaseAdapter 
{
	private final Activity context;
	private final JSONArray ja;
	   private static LayoutInflater inflater = null;

	



	public CustomList(Activity context,JSONArray ja) 
	{
		// TODO Auto-generated constructor stub
		this.context = context;
		this.ja =ja;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ja.length();
	}





	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}





	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = context.getLayoutInflater();

int i=0;
View row=null;	

try
{
    JSONObject jo = ja.getJSONObject(position);
if(jo!=null)
{
	View rowView= inflater.inflate(R.layout.list_super, null, true);
	TextView tv = (TextView) rowView.findViewById(R.id.cmpid);
	ProgressBar pr=(ProgressBar)rowView.findViewById(R.id.prBar1);

	tv.setText(jo.getString("cid"));
	i=Integer.valueOf(jo.getString("status"));
	pr.setProgress(i*34);
	row=rowView;
}
else
{
	View rowView= inflater.inflate(R.layout.whitespace, null, true);
	row=rowView;
}
}
catch (JSONException e)
{
    e.printStackTrace();
}
return row;



	}}


	
