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

import com.example.upesg.LoginActivity.LoginCheck;

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

public class RemarkActivity extends Activity implements OnClickListener
{
	Button btn;
	public String st="",str,etx="";
	public EditText et;
	public int value=0;
	public JSONObject jo=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remark);
		Bundle b= getIntent().getExtras();
		st=b.getString("final");
		str=b.getString("sapid");
et=(EditText)findViewById(R.id.remark);

		btn=(Button)findViewById(R.id.end);
		btn.setOnClickListener(this);
		
			}

	public void onClick(View v) 
	{
		etx=et.getText().toString();
		if(etx!="")
			st=st+"\nRemark : "+etx+"\n";
		if(v.getId()==R.id.end)
		{
		
			UploadData ob=new UploadData();
			ob.execute();
			
		}
	}
	

	class UploadData extends AsyncTask<String, String, String> 
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
			 String name = et.getText().toString();
	            
	     		        
	            List<NameValuePair> values = new ArrayList<NameValuePair>();
	            values.add(new BasicNameValuePair("sapid",str));
	            values.add(new BasicNameValuePair("cmpln",st));
	            try
	            {
	            	DefaultHttpClient httpClient = new DefaultHttpClient();
	                HttpPost httpPost = new HttpPost("http://192.168.12.1/complain.php");
	                httpPost.setEntity(new UrlEncodedFormEntity(values));
	                Log.e("Value Send",str+" "+st);
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
				Intent i=new Intent(RemarkActivity.this,AcknActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("remark",st);
				i.putExtra("sapid",str);
				try {
					i.putExtra("id",String.valueOf(ja.getInt("success")));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				startActivity(i);
			finish();
					
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
		getMenuInflater().inflate(R.menu.remark, menu);
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
