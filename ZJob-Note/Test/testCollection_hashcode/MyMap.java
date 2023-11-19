package testCollection_hashcode;

import java.util.*;
import java.util.Map;
import java.util.Set;

/**
 * achieve put and get function
 * date:2011-03-11
 *
 * @param <K> key
 * @param <V> value
 * @author Administrator
 */
public class MyMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] table;

    private int arrayLenth;

    public MyMap() {

    }

    public MyMap(int length) {
        this.arrayLenth = length;
        table = new Entry[length];
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return null;
        }
        int hash = key.hashCode();
        //calculate the array's index as hash
        int finalHash = hashAlgorithm(hash);
        Entry<K, V> entry = table[finalHash];
        while (entry != null) {
            if (key.equals(entry.key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new RuntimeException("you can't put a null key");
        int hash = key.hashCode();
        //calculate the array's index as hash
        int finalHash = hashAlgorithm(hash);
        for (Entry<K, V> entry = table[finalHash]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                V origValue = entry.value;
                entry.value = value;
                return origValue;
            }
        }
        table[finalHash] = new Entry<K, V>(key, value, table[finalHash]);
        return null;
    }

    private int hashAlgorithm(Integer hash) {
        return hash % (table.length - 1);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public V remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            if (entry == null)
                continue;
            sb.append("[" + entry.key + ":" + entry.value);
            entry = entry.next;
            while (entry != null) {
                sb.append("-->" + entry.key + ":" + entry.value);
                entry = entry.next;
            }
            sb.append("],");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next = null;

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }

        Entry(K key, V value, Entry<K, V> n) {
            this.key = key;
            this.value = value;
            this.next = n;
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
            // TODO Auto-generated method stub
            return this.value = value;
        }

    }

}
