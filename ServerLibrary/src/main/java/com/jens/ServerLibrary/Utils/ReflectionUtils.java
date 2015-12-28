package com.jens.ServerLibrary.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtils {
	
	/**
	 * Sets the specified field with the help 
	 * of java.reflect.
	 * @param instance gives the Object instance
	 * @param name gives the name of the searched Field in the instance
	 * @param value gives the new value
	 * @throws Exception if operation fails like unchecked Modifiers
	 */
	
	public static void set(Object instance, String name, Object value) throws Exception {
		Field field = instance.getClass().getDeclaredField(name);
		if(field.getModifiers() == Modifier.PRIVATE){
			field.setAccessible(true);
		}
		field.set(instance, value);
	}
	
	/**
	 * Gets the value of the specified Field
	 * with help of java.reflect.
	 * @param instance gives the Object instance
	 * @param name gives the name of the searched Field in the instance
	 * @return the current value of the searched Field
	 * @throws Exception if operation fails
	 */
	
	public static Object get(Object instance, String name) throws Exception {
		Field field = instance.getClass().getDeclaredField(name);
		
		return field.get(instance);
	}

}
