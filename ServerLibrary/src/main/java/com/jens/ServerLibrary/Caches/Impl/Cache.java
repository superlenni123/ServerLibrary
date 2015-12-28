package com.jens.ServerLibrary.Caches.Impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Cache<K, V> {
	
	protected ConcurrentHashMap<K, V> cache;
	protected ExecutorService exe;
	
	/**
	 * Initializes a new Cache.
	 */
	
	public Cache() {
		cache = new ConcurrentHashMap<K, V>();
		exe = Executors.newCachedThreadPool();
	}
	
	/**
	 * Adds a new key and value into the given cache
	 * @param key gives the key that should save the value
	 * @param value gives the value that should be saved
	 */
	
	public abstract void add(K key, V value);
	
	/**
	 * Removes the specified key out of the
	 * Cache.
	 * @param key gives the key that should be removed
	 */
	
	public abstract void remove(K key);
	
	/**
	 * Clears the given Cache.
	 */
	
	public abstract void clear();
	
	/**
	 * Gets the value out of the given
	 * Cache.
	 * @param key gives the key that should be searched for
	 * @return an Object (value of the given key)
	 */
	
	public abstract V get(K key);
	
	/**
	 * Checks if the Cache contains the given key.
	 * @param key gives the key that should be searched for
	 * @return true/false
	 */
	
	public abstract boolean contains(K key);
	
	/**
	 * Returns the size of the given
	 * Cache.
	 * @return size (Integer)
	 */
	
	public abstract int size();

}
