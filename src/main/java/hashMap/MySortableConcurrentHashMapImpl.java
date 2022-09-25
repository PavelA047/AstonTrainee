package hashMap;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySortableConcurrentHashMapImpl<K extends Comparable<K>, V> implements MyHashMap<K, V>, Iterable<K> {

    private final CopyOnWriteArrayList<MyEntryImpl<K, V>>[] data;
    private int size;

    static class MyEntryImpl<K, V> implements MyEntry<K, V> {
        private final K key;
        private volatile V value;

        public MyEntryImpl(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MyEntryImpl{" + "key=" + key + ", value=" + value + '}';
        }
    }

    public MySortableConcurrentHashMapImpl(int initialCapacity) {
        this.data = new CopyOnWriteArrayList[initialCapacity];
        for (int i = 0; i < data.length; i++) {
            data[i] = new CopyOnWriteArrayList<>();
        }
    }

    public MySortableConcurrentHashMapImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        if (data[index].size() == 0) {
            data[index].add(new MyEntryImpl<>(key, value));
        } else {
            for (MyEntryImpl<K, V> item : data[index]) {
                if (isKeysEquals(item, key)) {
                    item.value = value;
                    return true;
                }
            }
            data[index].add(new MyEntryImpl<>(key, value));
        }
        size++;
        return true;
    }

    private boolean isKeysEquals(MyEntryImpl<K, V> item, K key) {
        return item.getKey().equals(key);
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        } else {
            for (MyEntryImpl<K, V> item : data[index]) {
                if (isKeysEquals(item, key)) {
                    return item.getValue();
                }
            }
        }
        return null;
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        if (data[index].size() == 0) {
            return -1;
        }
        return index;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        } else {
            for (MyEntryImpl<K, V> item : data[index]) {
                if (isKeysEquals(item, key)) {
                    data[index].remove(item);
                    size--;
                    return item.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        for (CopyOnWriteArrayList<MyEntryImpl<K, V>> innerList : data) {
            innerList.clear();
        }
    }

    @Override
    public Object[] sort() {
        List<K> keyList = getKeyList();
        keyList.sort(Comparator.naturalOrder());
        return keyList.toArray();
    }

    private List<K> getKeyList() {
        List<K> keyList = new ArrayList<>();
        for (CopyOnWriteArrayList<MyEntryImpl<K, V>> innerList : data) {
            for (MyEntryImpl<K, V> key : innerList) {
                keyList.add(key.getKey());
            }
        }
        return keyList;
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
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s = [%s]%n", i, data[i]));
        }
        return sb.toString();
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator<K>(getKeyList());
    }
}
