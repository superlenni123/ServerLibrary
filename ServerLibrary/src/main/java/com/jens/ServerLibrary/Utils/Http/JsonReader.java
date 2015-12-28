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
	
	/**
	 * Initializes the Json Reader as an Object.
	 * Can be used to read Json Objects from
	 * Http Url Connections and to serialize them
	 * into JSONObject from org.json.simple.
	 * @param url the path to the JSONObject
	 */
	
	public JsonReader(String url) {
		this.url = url;
	}
	
	/**
	 * Reads the JSONObject from the given URL in the 
	 * Constructor.
	 * @param args The Arguments that have to be given to read the specified JSONObject
	 * @return JSONObject from org.json.simple
	 * @throws Exception when HttpURLConnection fails
	 */
	
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
	
	/**
	 * Sets the URL of the Reader.
	 * @param url new URL to read
	 */
	
	public void setUrl(String url) {
		this.url = url;
	}

}
