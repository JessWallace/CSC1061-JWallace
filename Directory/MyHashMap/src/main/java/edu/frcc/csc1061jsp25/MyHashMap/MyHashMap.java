package edu.frcc.csc1061jsp25.MyHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

	private static final int INITIAL_NUM_BUCKETS = 4;
	private static final double LOAD_FACTOR_THRESHOLD = 0.5;
	private List<Entry<K, V>>[] buckets;
	private int size = 0;

	private class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;

		// Entry constructor
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;

		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}
	}

	// Constructor
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		buckets = new LinkedList[INITIAL_NUM_BUCKETS];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if(get(key) != null) {
			return true;
		}
		return false;
	}


	@Override
	public boolean containsValue(Object value) {
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				List<Entry<K, V>> bucket = buckets[i];
				for (Entry<K, V> entry : bucket) {
					if (entry.getValue().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
		List<Entry<K, V>> bucket = buckets[bucketIndex];
		for (Entry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;

		if (buckets[bucketIndex] == null) {
			buckets[bucketIndex] = new LinkedList<Entry<K, V>>();
		}
		// If Key already exists in Map, replace value and return old value.
		for (Entry<K, V> entry : buckets[bucketIndex]) {
			if (entry.key.equals(key)) {
				V oldVal = entry.getValue();
				entry.value = value;
				return oldVal;
			}
		}
		// Check Load factor has been exceeded and take action
		// Load factor = num entries in map / number of buckets
		// int loadFactor =
		// if(loadFactor > LOAD_FACTOR_THRESHOLD) rehash();
		double loadFactor = size/buckets.length;
			if(loadFactor > LOAD_FACTOR_THRESHOLD) {
				rehash();
			}
		
		// If key does not exist, insert key and value
		buckets[bucketIndex].add(new Entry<K, V>(key, value));
		size++;
		return value;
	}
//	private void rehash() {}
	// create a new map that's twice as big and then remap the values.
	private void rehash() {
		List<Entry<K, V>>[] oldbuckets = buckets;
		buckets = new LinkedList[oldbuckets.length * 2];

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}

		for (List<Entry<K, V>> bucket : oldbuckets) {
			if (bucket != null) {
				for (Entry<K, V> entry : bucket) {
					put(entry.getKey(), entry.getValue());
				}
			}
		}

	}



	@Override
	public V remove(Object key) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
		if (buckets[bucketIndex] != null) {
			int listIndex = -1;
			for (Entry<K, V> entry : buckets[bucketIndex]) {
				listIndex++;
				if (entry.key.equals(key)) {
					break;

				}
			}
			V val = buckets[bucketIndex].get(listIndex).value;
			buckets[bucketIndex].remove(buckets[bucketIndex].get(listIndex));
			size--;
			return val;
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		size = 0;
		for(List<Entry<K,V>> bucket : buckets) {
			bucket = null;
		}
	}

	@Override
	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				List<Entry<K, V>> bucket = buckets[i];
				for (Entry<K, V> entry : bucket) {
					keySet.add(entry.getKey());
				}
			}
		}
		return keySet;
	}

	@Override
	public Collection<V> values() {
		List<V> values = new ArrayList<>();
		for (List<Entry<K, V>> bucket : buckets) {
			if (bucket != null) {
				for (Entry<K, V> entry : bucket) {
					values.add(entry.getValue());
				}
			}
		}
		return null;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
