package p2;

import java.util.LinkedList;

public class HashTable<Key, Value>
{
    protected int size;
    protected LinkedList<Value>[] table;
    protected int count = 0;

    public HashTable(int size)
    {
        this.size = size;
        table = new LinkedList[size];
        for ( int i = 0; i < table.length; ++i )
        {
            table[i] = new LinkedList<Value>();
        }
    }

    public void clear()
    {
    }

    public boolean containsKey(Key key)
    {
        return false;
    }

    public Value get(Key key)
    {
        return null;
    }

    public void put(Key key, Value value)
    {
    }

    public void remove(Key key)
    {
    }

    public int size()
    {
        return -1;
    }

    protected class Entry<Key, Value>
    {
        Key key;
        Value value;

        public Entry(Key key, Value value)
        {
            this.key = key;
            this.value = value;
        }

        @Override public boolean equals(Object entry)
        {
            return key.equals(((Entry)entry).key);
        }
    }
}
