package com.example.upesg;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity implements OnClickListener 
{

	Button login;
	Button regis;
	public EditText et1;
	public EditText et2;
	Intent i=null;  
	public boolean value=false;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login=(Button)findViewById(R.id.log);
		regis=(Button)findViewById(R.id.reg);
		et1=(EditText)findViewById(R.id.logid);
		et2=(EditText)findViewById(R.id.pass);
		
		login.setOnClickListener( this);
		regis.setOnClickListener( this);
		
 //	

	}

	public void onClick(View v) 
	{
	String str="";
		try
		{
			if(v.getId()==R.id.log)
		
		{
				
				LoginCheck ob=new LoginCheck();
			ob.execute();
			
	if(value)
		i=new Intent(this,FuncActivity.class);

	str=et1.getText().toString();

	i.putExtra("sapid",str);
		//Toast.makeText(LoginActivity.this,"Wrong Password",3000).show();
		
		}
		else if(v.getId()==R.id.reg)
		{
			i=new Intent(this,RegiActivity.class);
			
		}
		startActivity(i);
		finish();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
/*====================Sending Information===============*/
	
	class LoginCheck extends AsyncTask<String, String, String> 
	{
	public JSONObject jo=null;
	 public  boolean var=false;
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	 
	    
		 }

		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
            String id = et1.getText().toString();
            String password = et2.getText().toString();
     
            Log.e("Entity Response 3",id+" : "+password);
    		        
            List<NameValuePair> values = new ArrayList<NameValuePair>();
            values.add(new BasicNameValuePair("id", id));
            values.add(new BasicNameValuePair("password",password));
            try
            {
            	DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.12.1/id.php");
                httpPost.setEntity(new UrlEncodedFormEntity(values));
         
            	
                Log.e("Entity Response 4",id+" : "+password);
                
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
        	
                try
				{
					String str=EntityUtils.toString(httpEntity);
					Log.e("Dragonoid",str);
					jo=new JSONObject(str);
					Log.e("string",jo.getString("message"));
					check_func(jo);		
					
				
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
		
				if(s==1)
					value=true;
				else
					value=false;
					}
		
				@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
    		  
		}
		
	}
	
	
	//====================End of Sending Information =====================
	

		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
