package com.jens.ServerLibrary.Protocol;

import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.common.collect.Maps;
import com.jens.ServerLibrary.Utils.Http.JsonReader;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

public class GameProfileReader {
	
	protected static Map<UUID, CachedProfile> cachedProfiles = Maps.newConcurrentMap();
	
	public GameProfile getGameProfile(String name){
		name = name.toLowerCase();
		
		UUID uuid = UUIDReader.getUUID(name);
		
		if(cachedProfiles.containsKey(uuid)){
			
			CachedProfile profile = cachedProfiles.get(uuid);
			
			GameProfile result = GameProfileBuilder.buildNew(profile.name, profile.properties);
			
			return result;
		} else {
			
			String profileID = uuid.toString().replace("-", "");
			GameProfile profile = null;
			
			JsonReader reader = new JsonReader(AuthentificationConnect.GAMEPROFILE.getURL());
			
			try {
				JSONObject result = reader.read(profileID);
				
				profile = new GameProfile(uuid, result.get("name").toString());
				
				JSONArray propertieArray = (JSONArray) result.get("properties");
				
				JSONObject properties = (JSONObject) propertieArray.get(0);
				
				profile.getProperties().put("textures", new Property("textures",
						properties.get("value").toString(),
						properties.get("signature").toString()));
				
				CachedProfile endProfile = new CachedProfile(result.get("name").toString(),
						uuid,
						profile.getProperties());
				
				cachedProfiles.put(uuid, endProfile);
				
				return profile;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	class CachedProfile {
		
		public String name;
		public UUID uuid;
		public PropertyMap properties;
		
		public CachedProfile(String name, UUID uuid, PropertyMap properties) {
			this.name = name;
			this.uuid = uuid;
			this.properties = properties;
		}
	}

}
