package com.jens.ServerLibrary.Caches;

import com.jens.ServerLibrary.Caches.Impl.Cache;

public class LoadingCache<K, V> extends Cache<K, V> {

	@Override
	public void add(K key, V value) {
		add(key, value, 60);
	}
	
	public void add(final K key, final V value, final int cacheingTime){
		cache.put(key, value);
		
		if(cacheingTime >= 1){
			
			exe.execute(new Runnable() {
				
				public void run() {
					try {
						Thread.sleep(cacheingTime * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					cache.remove(key);
				}
			});
		}
	}

	@Override
	public void remove(K key) {
		if(cache.containsKey(key)){
			cache.remove(key);
		} else {
			throw new NullPointerException("The key you try to remove is null");
		}
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public V get(K key) {
		if(cache.get(key) != null){
			return cache.get(key);
		} else {
			throw new NullPointerException("The key you search for is null");
		}
	}

	@Override
	public boolean contains(K key) {
		return cache.containsKey(key);
	}

	@Override
	public int size() {
		return cache.size();
	}

}
