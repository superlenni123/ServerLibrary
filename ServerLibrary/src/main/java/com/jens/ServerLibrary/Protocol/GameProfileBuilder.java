package com.jens.ServerLibrary.Protocol;

import com.jens.ServerLibrary.Utils.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;

public class GameProfileBuilder {
	
	/**
	 * Builds a new GameProfile out of 
	 * Target Name, New Name, and Properties.
	 * @param target gives the name of the Target that should get a new Profile
	 * @param newName gives the newName of the new Profile
	 * @param properties gives the new Properties of the new Profile
	 * @return a new readable GameProfile
	 */
	
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
	
	/**
	 * Builds a new GameProfile out of
	 * Target Name, and Properties.
	 * @param target gives the name of the Target that should get a new Profile
	 * @param properties gives the Properties of the new Profile
	 * @return a new readable GameProfile
	 */
	
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
