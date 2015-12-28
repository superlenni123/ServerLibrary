package com.jens.ServerLibrary.Protocol;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;

import com.jens.ServerLibrary.Utils.Http.JsonReader;
import com.mojang.util.UUIDTypeAdapter;

public class UUIDReader {
	
	protected static Map<String, UUID> cachedUUIDS = new ConcurrentHashMap<String, UUID>();
	
	/**
	 * Returns the UUID of the specified Player
	 * name on the current Timemillies of the
	 * given System.
	 * @param name gives the name of the Player that should be searched for
	 * @return Mojang UUID of the given name
	 */
	
	public static UUID getUUID(String name){
		return getUUID(name, System.currentTimeMillis());
	}
	
	/**
	 * Returns the UUID of the specified Player
	 * name on the given Timemillies Argument.
	 * @param name gives the name of the Player that should be searched for
	 * @param timemillies gives the Timemillies of the searched UUID
	 * @return Mojang UUID of the given name
	 */
	
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
