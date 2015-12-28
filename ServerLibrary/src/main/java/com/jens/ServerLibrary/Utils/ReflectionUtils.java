package com.jens.ServerLibrary.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtils {
	
	public static void set(Object instance, String name, Object value) throws Exception {
		Field field = instance.getClass().getDeclaredField(name);
		if(field.getModifiers() == Modifier.PRIVATE){
			field.setAccessible(true);
		}
		field.set(instance, value);
	}
	
	public static Object get(Object instance, String name) throws Exception {
		Field field = instance.getClass().getDeclaredField(name);
		
		return field.get(instance);
	}

}
