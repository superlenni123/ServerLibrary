package com.jens.ServerLibrary.Protocol;

import com.jens.ServerLibrary.Utils.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;

public class GameProfileBuilder {
	
	public static GameProfile buildNew(String target, String newName, PropertyMap properties){
		GameProfile profile = null;
		
		try {
			ReflectionUtils.set(profile, "name", newName);
			ReflectionUtils.set(profile, "id", UUIDReader.getUUID(target));
			ReflectionUtils.set(profile, "properties", properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return profile;
	}
	
	public static GameProfile buildNew(String target, PropertyMap properties){
		GameProfile profile = null;
		
		try {
			ReflectionUtils.set(profile, "name", target);
			ReflectionUtils.set(profile, "id", UUIDReader.getUUID(target));
			ReflectionUtils.set(profile, "properties", properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return profile;
	}
}
