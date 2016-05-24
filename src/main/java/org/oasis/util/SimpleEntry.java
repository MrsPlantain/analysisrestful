package org.oasis.util;

import java.util.Map;

/**
 * Created by chao on 2016/5/6.
 */
public  class SimpleEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    public SimpleEntry() {
        super();
    }

    public SimpleEntry(K key,V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        return this.value = value;
    }
}
