package p2;

public class HashTable<K, V>
{
    // TODO : figure out why i can't just create Entries[] table (something is
    // strange with the generics)
    protected Object[] table;
    // This is the number of elements stored in the table.
    protected int size = 0;

    public HashTable(int size)
    {
        _init(size);
    }

    public void _init(int size)
    {
        table = new Object[size];
        for ( int i = 0; i < table.length; ++i )
            table[i] = new Entries();
    }

    public void clear()
    {
        _init(table.length);
    }
    
    private int hashIndex(K key)
    {
        int hashCode = key.hashCode() % table.length;
        return ( hashCode < 0 ) ? -hashCode : hashCode;
    }

    public boolean containsKey(K key)
    {
        if (((Entries)table[hashIndex(key)]).find(key) != null)
            return true;
        return false;
    }

    public V get(K key)
    {
        System.out.println("get() : hashIndex("+key+") = " + hashIndex(key));
        return ((Entries)table[hashIndex(key)]).find(key);
    }

    public void put(K key, V value)
    {
        System.out.println("put() : hashIndex("+key+") = " + hashIndex(key));
        ((Entries)table[hashIndex(key)]).add(new Entry(key, value));
    }

    public V remove(K key)
    {
        System.out.println("remove() : hashIndex("+key+") = " + hashIndex(key));
        return ((Entries)table[hashIndex(key)]).remove(key);
    }

    public int size()
    {
        return -1;
    }

    protected class Entries
    {
        Entry first;
        Entry pointer;
        Entry last;

        public void add(Entry entry)
        {
            if (first == null)
            {
                first = entry;
                pointer = entry;
                last = entry;
            }
            else
            {
                last.next = entry;
                last = entry;
            }
        }

        public V find(K key)
        {
            Entry entry = first;
            while (entry != null)
            {
                System.out.println("find("+key+") : entry.key="+entry.key);
                if (entry.key.equals(key))
                {
                    return entry.value;
                }
                entry = entry.next;
            }
            
            return null;
        }

        public V remove(K key)
        {
            // TODO: ? update pointer, or perhaps it shouldn't..
            Entry entry = first;
            while (entry != null)
            {
                if (entry.key.equals(key))
                {
                    if (entry == first)
                    {
                        System.out.println("first...");
                        first = entry.next;
                        if (first == null)
                            last = pointer = null;
                        else
                            first.previous = null;
                    }
                    else if (entry != last)
                    {
                        System.out.println("not last...");
                        entry.previous.next = entry.next;
                        entry.next.previous = entry.previous;
                    }
                    else
                    {
                        System.out.println("last...");
                        entry.previous.next = null;
                        last = entry.previous;
                    }

                    return entry.value;
                }

                entry = entry.next;
            }

            return null;
        }
    }

    protected class Entry
    {
        Entry previous;
        Entry next;

        K key;
        V value;

        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
