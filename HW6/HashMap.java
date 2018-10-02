import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry<?, ?>[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        V oldVal = null;
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("Value can not be null.");
        }
        //resizing
        if (((size + 1.f) / table.length) > HashMapInterface.MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int n = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> map = new MapEntry<>(key, value);
        MapEntry<K, V> cur = table[n];
        boolean dup = false;
        //Checking Duplicates
        while (cur != null) {
            if (key.hashCode() == cur.getKey().hashCode()) {
                oldVal = cur.getValue();
                cur.setValue(value);
                dup = true;
            }
            cur = cur.getNext();
        }
        cur = table[n];
        if (!dup) {
            if (cur == null) {
                table[n] = map;
            } else {
                map.setNext(table[n]);
                table[n] = map;
            }
            size++;
        }
        return oldVal;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null.");
        }
        int n = Math.abs(key.hashCode() % table.length);
        System.out.println(n);
        MapEntry<K, V> prev = table[n];
        V removed = null;
        MapEntry<K, V> cur;
        if (prev == null) {
            throw new NoSuchElementException("Key was not found in table.");
        }
        if (prev.getNext() == null) {
            removed = prev.getValue();
            table[n] = null;
        } else if (prev.getKey().equals(key)) {
            removed = prev.getValue();
            prev = prev.getNext();
            table[n] = prev;
        } else {
            cur = prev.getNext();
            boolean r = false;
            while (cur != null && !r) {
                System.out.println("~");
                if (cur.getKey().equals(key)) {
                    System.out.println("!");
                    removed = cur.getValue();
                    prev.setNext(cur.getNext());
                    r = true;
                } else {
                    prev = cur;
                }
                cur = cur.getNext();
            }
            table[n] = prev;
        }
        if (removed == null) {
            throw new NoSuchElementException("Key was not found in table.");
        }
        size--;
        return removed;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null.");
        }
        MapEntry<K, V> cur = table[Math.abs(key.hashCode() % table.length)];
        while (cur != null) {
            if (cur.getKey().equals(key)) {
                return cur.getValue();
            }
            cur = cur.getNext();
        }
        throw new NoSuchElementException("Key is not in map.");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can not be null.");
        }
        boolean contain = false;
        MapEntry<K, V> m = table[Math.abs(key.hashCode() % table.length)];
        while (m != null) {
            if (m.getKey().equals(key)) {
                contain = true;
            }
            m = m.getNext();
        }
        return contain;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry<?, ?>[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        HashSet set = new HashSet<>();
        for (MapEntry<K, V> cur: table) {
            while (cur != null) {
                set.add(cur.getKey());
                cur = cur.getNext();
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();
        for (MapEntry<K, V> cur: table) {
            while (cur != null) {
                list.add(cur.getValue());
                cur = cur.getNext();
            }
        }
        return list;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length can not be negative");
        } else if (length < size) {
            throw new IllegalArgumentException("Length can not "
                    + "be less than size of table");
        }
        MapEntry<K, V>[] newTable = (MapEntry<K, V>[])
                new MapEntry<?, ?>[length];
        for (MapEntry<K, V> cur: table) {
            while (cur != null) {
                int n = Math.abs(cur.getKey().hashCode() % newTable.length);
                MapEntry<K, V> map = new MapEntry<>(cur.getKey(),
                        cur.getValue());
                System.out.println(cur.getKey());
                if (newTable[n] == null) {
                    newTable[n] = map;
                } else {
                    map.setNext(newTable[n]);
                    newTable[n] = map;
                }
                cur = cur.getNext();
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
