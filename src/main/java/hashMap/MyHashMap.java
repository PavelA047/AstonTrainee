package hashMap;

public interface MyHashMap<K, V> {

    boolean put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    void display();

    void clear();

    Object[] sort();

    interface MyEntry<K, V> {
        K getKey();

        V getValue();

        void setValue(V value);
    }
}
