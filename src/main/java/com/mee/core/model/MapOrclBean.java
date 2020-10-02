package com.mee.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description DAO Mapping by myself,this Bean just for oracle
 *
 */
public class MapOrclBean<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	public MapOrclBean() {
		super();
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public MapOrclBean(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * @param initialCapacity
	 */
	public MapOrclBean(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param m
	 */
	public MapOrclBean(Map<? extends K, ? extends V> m) {
		super(m);
	}
	
	/**
	 * Only for castor
	 * @return
	 */
	public Map getMap(){
		return this;
	}


	public V get(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.get(key1);
	}

	public boolean containsKey(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.containsKey(key1);
	}

	public V put(K key, V value) {
		K key1 = key; 
		if (key instanceof String){
			key1 = (K)((String)key).toLowerCase();
		}
		return super.put(key1, value);
	}

	public V remove(Object key) {
		Object key1 = key; 
		if (key instanceof String){
			key1 = ((String)key).toLowerCase();
		}
		return super.remove(key1);
	}
}
