package com.jens.ServerLibrary.Caches.Impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Cache<K, V> {
	
	protected ConcurrentHashMap<K, V> cache;
	protected ExecutorService exe;
	
	public Cache() {
		cache = new ConcurrentHashMap<K, V>();
		exe = Executors.newCachedThreadPool();
	}
	
	public abstract void add(K key, V value);
	
	public abstract void remove(K key);
	
	public abstract void clear();
	
	public abstract V get(K key);
	
	public abstract boolean contains(K key);
	
	public abstract int size();

}
