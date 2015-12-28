package com.jens.ServerLibrary.Utils.Http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonReader {
	
	protected String url;
	
	public JsonReader(String url) {
		this.url = url;
	}
	
	public JSONObject read(Object... args) throws Exception {
		HttpURLConnection con;
		
		if(args != null){
			con = (HttpURLConnection) new URL(String.format(url, args)).openConnection();
		} else {
			con = (HttpURLConnection) new URL(url).openConnection();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),
				Charset.forName("UTF-8")));
		
		JSONObject result = (JSONObject) JSONValue.parse(reader);
		
		return result;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
