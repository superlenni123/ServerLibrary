package com.jens.ServerLibrary.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlotMap {
	
	protected Map<Object, Object[]> map;
	protected Object[] values;
	
	/**
	 * Initializes a new Multimap/Slotmap with given
	 * maximum Objects.
	 * @param maxSlots gives the maximum Object Array
	 */
	
	public SlotMap(int maxSlots) {
		map = new ConcurrentHashMap<Object, Object[]>();
		values = new Object[maxSlots];
	}
	
	/**
	 * Puts a key with a value into the Multimap
	 * to store him with more than one value.
	 * @param key gives the key to save the value
	 * @param position gives the position where the value should be saved in Array
	 * @param value gives the value that should be saved
	 */
	
	public void put(Object key, int position, Object value){
		if(map.get(key) != null){
			values = map.get(key);
		}
		
		values[position - 1] = value;
		
		map.put(key, values);
	}
	
	/**
	 * Puts an Array with a key into the Multimap.
	 * @param key gives the key to save the Array
	 * @param value needs to be an Array to save
	 */
	
	public void put(Object key, Object... value){
		values = value;
		
		map.put(key, values);
	}
	
	/**
	 * Removes a specified key out of
	 * the Multimap.
	 * @param key gives the key that should be removed
	 */
	
	public void remove(Object key){
		if(map.get(key) != null){
			map.remove(key);
		} else {
			throw new NullPointerException("The key that should be removed is null");
		}
	}
	
	/**
	 * Removes a specified Object out of the saved
	 * Array from a key. 
	 * @param key gives the key to get the saved Array
	 * @param position gives the position which Object should be removed
	 */
	
	public void remove(Object key, int position){
		if(map.get(key) != null){
			values = map.get(key);
			values[position - 1] = null;
			map.put(key, values);
		} else {
			throw new NullPointerException("The key you search for is null");
		}
	}
	
	/**
	 * Clears the complete Multimap.
	 */
	
	public void clear(){
		map.clear();
	}
	
	/**
	 * Checks if the Multimap is Empty.
	 * @return true/false
	 */
	
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	/**
	 * Gets a specified Object out of an Array
	 * with a specified key.
	 * @param key gives the key of the Array
	 * @param position gives the position of the Object that should be searched for
	 * @return Object on the specified postion
	 */
	
	public Object get(Object key, int position){
		if(map.get(key) != null){
			values = map.get(key);
			
			return values[position - 1];
		} else {
			throw new NullPointerException("The key you search for is null");
		}		
	}
	
	/**
	 * Returns the saved Array of the given
	 * key.
	 * @param key gives the key where the Array is saved
	 * @return Object Array
	 */
	
	public Object get(Object key){
		if(map.get(key) != null){
			return map.get(key);
		} else {
			throw new NullPointerException("The key you search for is null");
		}
	}
	
	/**
	 * Checks if the Multimap contains the given key.
	 * @param key gives the key that should be checked for
	 * @return true/false
	 */
	
	public boolean containsKey(Object key){
		return map.containsKey(key);
	}
	
	/**
	 * Returns the current size of the Multimap.
	 * @return size of Multimap (Integer)
	 */
	
	public int size(){
		return map.size();
	}
}
