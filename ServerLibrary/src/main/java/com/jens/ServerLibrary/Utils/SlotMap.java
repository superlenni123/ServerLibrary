package com.jens.ServerLibrary.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlotMap {
	
	protected Map<Object, Object[]> map;
	protected Object[] values;
	
	public SlotMap(int maxSlots) {
		map = new ConcurrentHashMap<Object, Object[]>();
		values = new Object[maxSlots];
	}
	
	public void put(Object key, int position, Object value){
		if(map.get(key) != null){
			values = map.get(key);
		}
		
		values[position - 1] = value;
		
		map.put(key, values);
	}
	
	public void put(Object key, Object... value){
		values = value;
		
		map.put(key, values);
	}
	
	public void remove(Object key){
		if(map.get(key) != null){
			map.remove(key);
		} else {
			throw new NullPointerException("The key that should be removed is null");
		}
	}
	
	public void remove(Object key, int position){
		if(map.get(key) != null){
			values = map.get(key);
			values[position - 1] = null;
			map.put(key, values);
		} else {
			throw new NullPointerException("The key you search for is null");
		}
	}
	
	public void clear(){
		map.clear();
	}
	
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	public Object get(Object key, int position){
		if(map.get(key) != null){
			values = map.get(key);
			
			return values[position - 1];
		} else {
			throw new NullPointerException("The key you search for is null");
		}		
	}
	
	public Object get(Object key){
		if(map.get(key) != null){
			return map.get(key);
		} else {
			throw new NullPointerException("The key you search for is null");
		}
	}
	
	public boolean containsKey(Object key){
		return map.containsKey(key);
	}
	
	public int size(){
		return map.size();
	}
}
