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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegiActivity extends Activity implements OnClickListener 
{
	Button register;
	Intent i;
	public EditText et1;
	public EditText et2;
	public EditText et3;
	public EditText et4;
	public EditText et5;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regi);
		register=(Button)findViewById(R.id.regi2);
	
		et1=(EditText)findViewById(R.id.name1);
		et2=(EditText)findViewById(R.id.phn);
		et3=(EditText)findViewById(R.id.email);
		et4=(EditText)findViewById(R.id.pass);
		et5=(EditText)findViewById(R.id.sap);
		
		
		
		register.setOnClickListener( this);
		
	}


	public void onClick(View v) 
	{
		if(v.getId()==R.id.regi2)
		{
			new UpdateUser().execute();
			
			i=new Intent(this,FuncActivity.class);
		}
		else {
		
		}
		startActivity(i);
		finish();
		
	}
	
	
	class UpdateUser extends AsyncTask<String, String, String> 
	{
	public JSONObject jo=null;
	 public boolean var=false;
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
		 }

		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			 String name = et1.getText().toString();
	            String phn = et2.getText().toString();
	            String email = et3.getText().toString();
	            String pass= et4.getText().toString();
	            String sap= et5.getText().toString();
		     	
	     		        
	            List<NameValuePair> values = new ArrayList<NameValuePair>();
	            values.add(new BasicNameValuePair("name", name));
	            values.add(new BasicNameValuePair("phn",phn));
	            values.add(new BasicNameValuePair("email",email));
	            values.add(new BasicNameValuePair("pass",pass));
	            values.add(new BasicNameValuePair("sap",sap));
	            try
	            {
	            	DefaultHttpClient httpClient = new DefaultHttpClient();
	                HttpPost httpPost = new HttpPost("http://192.168.12.1/update.php");
	                httpPost.setEntity(new UrlEncodedFormEntity(values));
	                Log.e("Value Send",name+" "+email+" "+phn);
	                HttpResponse httpResponse = httpClient.execute(httpPost);
	                HttpEntity httpEntity = httpResponse.getEntity();
	        	
	                try
					{
						String str=EntityUtils.toString(httpEntity);
						Log.e("Dragonoid",str);
					}
				
				catch(IOException e)
				{
					e.printStackTrace();
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
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
    	}
		
	}
	


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regi, menu);
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
