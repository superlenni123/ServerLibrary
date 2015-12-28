package com.jens.ServerLibrary.Protocol;

import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.google.common.collect.Maps;
import com.jens.ServerLibrary.Utils.Http.JsonReader;
import com.mojang.util.UUIDTypeAdapter;

public class UUIDReader {
	
	protected static Map<String, UUID> cachedUUIDS = Maps.newConcurrentMap();
	
	public static UUID getUUID(String name){
		return getUUID(name, System.currentTimeMillis());
	}
	
	public static UUID getUUID(String name, long timemillies){
		name = name.toLowerCase();
		
		if(cachedUUIDS.containsKey(name)){
			return cachedUUIDS.get(name);
		} else {
			JsonReader reader = new JsonReader(AuthentificationConnect.UUID.getURL());
			
			try {
				JSONObject result = reader.read(name, timemillies / 1000);
				
				UUID uuid = UUIDTypeAdapter.fromString((String)result.get("id"));
				
				cachedUUIDS.put(name, uuid);
				
				return uuid;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
