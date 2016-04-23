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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Statuslist extends Activity
{
	public ListView listm;
	int row;
	public String st;	
	public Intent i;
	public JSONObject jo=null;
	public  JSONArray ja = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		listm=(ListView)findViewById(R.id.list);
		
		Bundle b= getIntent().getExtras();
		st=b.getString("sapid");
		
		new StatusCheck().execute();

		
		listm.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			{
				// TODO Auto-generated method stub
                JSONObject jo;
				try {
					jo = ja.getJSONObject(position);
				

				Intent intent = new Intent(Statuslist.this,StatusActivity.class);
				intent.putExtra("cid",jo.getString("cid"));
				intent.putExtra("cmpln",jo.getString("cmpln"));
				intent.putExtra("status",jo.getString("status"));
				
				startActivity(intent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}); 

		registerForContextMenu(listm);

	}

	class StatusCheck extends AsyncTask<String, String, String> 
	{
	 public  boolean var=false;
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	 
	    
		 }

		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
            Log.e("Value Sending",st);
    		        
            List<NameValuePair> values = new ArrayList<NameValuePair>();
            values.add(new BasicNameValuePair("sapid",st));
            try
            {
            	DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.12.1/status.php");
                httpPost.setEntity(new UrlEncodedFormEntity(values));
         
            	
                Log.e("Sending 2",st);
                
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
        	
               

                if (httpEntity != null) {
                    try {
                        String entityResponse = EntityUtils.toString(httpEntity);

                        Log.e("Entity Response  : ", entityResponse);


                    ja = new JSONArray(entityResponse);
                    }
		catch(JSONException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
		     
    	    	
                }
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } 
	catch (IOException e) {
                e.printStackTrace();
            }
     
        		    	 
			return null;
		}
		
		public void check_func(JSONObject ja)
		{
		int s=0;
		boolean va=false;
			Log.e("Entity Response","Step@ 2");
			
				try
				{
					
			s=ja.getInt("success");
				}
					catch(Exception e)
					{
						e.printStackTrace();
					}
		
					}
		
		
				@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
if(ja!=null)
{
			CustomList adapter = new CustomList(Statuslist.this,ja);
			listm.setAdapter(adapter);
}
else
{
	setContentView(R.layout.whitespace);
	Toast.makeText(Statuslist.this,"No Complains filed",3000).show();
	
}
    		  
		}
		
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
