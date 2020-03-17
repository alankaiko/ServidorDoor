package com.laudoecia.api.worklistes;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> {

	public static final class Entry<V> {
		final V value;
		final long fetchTime;

		Entry(V value, long fetchTime) {
			this.value = value;
			this.fetchTime = fetchTime;
		}

		public V value() {
			return value;
		}
	}

	private int maxSize;
	private long staleTimeout;

	private final LinkedHashMap<K, Entry<V>> cache = new LinkedHashMap<K, Entry<V>>() {
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, Cache.Entry<V>> eldest) {
			return maxSize > 0 && size() > maxSize;
		}
	};

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		if (maxSize > 0) {
			int remove = cache.size() - maxSize;
			if (remove > 0) {
				Iterator<Map.Entry<K, Entry<V>>> iter = cache.entrySet().iterator();
				do {
					iter.next();
					iter.remove();
				} while (--remove > 0);
			}
		}
		this.maxSize = maxSize;
	}

	public long getStaleTimeout() {
		return staleTimeout;
	}

	public void setStaleTimeout(long staleTimeout) {
		this.staleTimeout = staleTimeout;
	}

	public Entry<V> getEntry(K key) {
		if (staleTimeout <= 0)
			return cache.get(key);

		long minFetchTime = System.currentTimeMillis() - staleTimeout;
		for (Iterator<Entry<V>> iter = cache.values().iterator(); iter.hasNext(); iter.remove())
			if (iter.next().fetchTime > minFetchTime)
				return cache.get(key);
		return null;
	}

	public V get(K key) {
		Entry<V> entry = getEntry(key);
		return entry != null ? entry.value : null;
	}

	public V put(K key, V value) {
		Entry<V> entry = cache.put(key, new Entry<V>(value, System.currentTimeMillis()));
		return entry != null ? entry.value : null;
	}

	public V remove(K key) {
		Entry<V> entry = cache.remove(key);
		return entry != null ? entry.value : null;
	}

	public void clear() {
		cache.clear();
	}
}
