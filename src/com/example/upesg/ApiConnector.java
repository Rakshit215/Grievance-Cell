package com.example.upesg;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class ApiConnector {

	
		public JSONObject GetAllCustomers() 
		{
			String url="http://192.168.12.1/id.php";
			HttpEntity httpEntity=null;
			
			try
			{
			DefaultHttpClient httpClient=new DefaultHttpClient();
			HttpGet httpGet=new HttpGet(url);
			HttpResponse httpResponse=httpClient.execute(httpGet);
			httpEntity=httpResponse.getEntity();
			
			}
			catch (ClientProtocolException e)
			{
			e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			JSONObject jo=null;
			//JSONArray jasonArray=null;
			if(httpEntity!=null)
			{
				try
				{
					String str=EntityUtils.toString(httpEntity);
					Log.e("Entity papap Response",str);
					jo=new JSONObject(str);
				//	jasonArray=new JSONArray(str);
				
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
		
			
			
			
			return jo;
			
		
}
}