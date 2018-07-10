package github.io.volong.tlojp.chapter17;

public class Entry<K, V> {

    final K key;
    V value;
    Entry<K,V> next;

    /**
     * Creates new entry.
     */
    Entry(K k, V v, Entry<K,V> n) {
        value = v;
        next = n;
        key = k;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }


}
