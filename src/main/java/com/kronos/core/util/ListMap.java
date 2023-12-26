package com.kronos.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ListMap<K, V> {
	HashMap<K, List<V>> values;

	public ListMap() {
		values = new HashMap<K, List<V>>();
	}

	public void put(K key, V val) {
		List<V> v = values.get(key);
		if (v == null) {
			create(key);
			put(key, val);
			return;
		}
		v.add(val);

		values.put(key, v);

	}

	public void create(K k) {
		values.put(k, new ArrayList<V>());
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.AbstractMap#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return values.equals(o);
	}

	/**
	 * @return
	 * @see java.util.AbstractMap#hashCode()
	 */
	@Override
	public int hashCode() {
		return values.hashCode();
	}

	/**
	 * @return
	 * @see java.util.AbstractMap#toString()
	 */
	@Override
	public String toString() {
		return values.toString();
	}

	/**
	 * @return
	 * @see java.util.HashMap#size()
	 */
	public int size() {
		return values.size();
	}

	/**
	 * @return
	 * @see java.util.HashMap#isEmpty()
	 */
	public boolean isEmpty() {
		return values.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public List<V> get(Object key) {
		return values.get(key);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return values.containsKey(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public List<V> put(K key, List<V> value) {
		return values.put(key, value);
	}

	/**
	 * @param m
	 * @see java.util.HashMap#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends K, ? extends List<V>> m) {
		values.putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	public List<V> remove(Object key) {
		return values.remove(key);
	}

	/**
	 * 
	 * @see java.util.HashMap#clear()
	 */
	public void clear() {
		values.clear();
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.HashMap#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return values.containsValue(value);
	}

	/**
	 * @return
	 * @see java.util.HashMap#keySet()
	 */
	public Set<K> keySet() {
		return values.keySet();
	}

	/**
	 * @return
	 * @see java.util.HashMap#values()
	 */
	public Collection<List<V>> values() {
		return values.values();
	}

	/**
	 * @return
	 * @see java.util.HashMap#entrySet()
	 */
	public Set<Entry<K, List<V>>> entrySet() {
		return values.entrySet();
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 * @see java.util.HashMap#getOrDefault(java.lang.Object, java.lang.Object)
	 */
	public List<V> getOrDefault(Object key, List<V> defaultValue) {
		return values.getOrDefault(key, defaultValue);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#putIfAbsent(java.lang.Object, java.lang.Object)
	 */
	public List<V> putIfAbsent(K key, List<V> value) {
		return values.putIfAbsent(key, value);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#remove(java.lang.Object, java.lang.Object)
	 */
	public boolean remove(Object key, Object value) {
		return values.remove(key, value);
	}

	/**
	 * @param key
	 * @param oldValue
	 * @param newValue
	 * @return
	 * @see java.util.HashMap#replace(java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public boolean replace(K key, List<V> oldValue, List<V> newValue) {
		return values.replace(key, oldValue, newValue);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#replace(java.lang.Object, java.lang.Object)
	 */
	public List<V> replace(K key, List<V> value) {
		return values.replace(key, value);
	}

	/**
	 * @param key
	 * @param mappingFunction
	 * @return
	 * @see java.util.HashMap#computeIfAbsent(java.lang.Object,
	 *      java.util.function.Function)
	 */
	public List<V> computeIfAbsent(K key, Function<? super K, ? extends List<V>> mappingFunction) {
		return values.computeIfAbsent(key, mappingFunction);
	}

	/**
	 * @param key
	 * @param remappingFunction
	 * @return
	 * @see java.util.HashMap#computeIfPresent(java.lang.Object,
	 *      java.util.function.BiFunction)
	 */
	public List<V> computeIfPresent(K key,
			BiFunction<? super K, ? super List<V>, ? extends List<V>> remappingFunction) {
		return values.computeIfPresent(key, remappingFunction);
	}

	/**
	 * @param key
	 * @param remappingFunction
	 * @return
	 * @see java.util.HashMap#compute(java.lang.Object,
	 *      java.util.function.BiFunction)
	 */
	public List<V> compute(K key, BiFunction<? super K, ? super List<V>, ? extends List<V>> remappingFunction) {
		return values.compute(key, remappingFunction);
	}

	/**
	 * @param key
	 * @param value
	 * @param remappingFunction
	 * @return
	 * @see java.util.HashMap#merge(java.lang.Object, java.lang.Object,
	 *      java.util.function.BiFunction)
	 */
	public List<V> merge(K key, List<V> value,
			BiFunction<? super List<V>, ? super List<V>, ? extends List<V>> remappingFunction) {
		return values.merge(key, value, remappingFunction);
	}

	/**
	 * @param action
	 * @see java.util.HashMap#forEach(java.util.function.BiConsumer)
	 */
	public void forEach(BiConsumer<? super K, ? super List<V>> action) {
		values.forEach(action);
	}

	/**
	 * @param function
	 * @see java.util.HashMap#replaceAll(java.util.function.BiFunction)
	 */
	public void replaceAll(BiFunction<? super K, ? super List<V>, ? extends List<V>> function) {
		values.replaceAll(function);
	}

	/**
	 * @return
	 * @see java.util.HashMap#clone()
	 */
	@Override
	public Object clone() {
		return values.clone();
	}

}
