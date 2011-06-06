package graf;

/**
 *
 * @author Spellabbet
 */
public class SuperQueue<T>
{
    Entry first;

    public void add(T obj, double priority)
    {
        System.out.println("add()");
        Entry newEntry = new Entry(obj, priority);

        // special case: empty queue
        if(first == null)
        {
            first = newEntry;
        }
        else
        {
            Entry current = first;
            // special case: if new entry has less priority than first
            if (priority <= current.priority)
            {
                newEntry.next = current;
                first = newEntry;
                return;
            }
            // else: checks NEXT item (we don't have a previous pointer)
            while(current != null)
            {
                // if we're at end of queue
                if (current.next == null)
                    break;

                if(priority <= current.next.priority)
                {
                    newEntry.next = current.next;
                    current.next = newEntry;
                    return;
                }
                else
                {
                    current = current.next;
                }
            }
            current.next = newEntry;
        }
    }

    /**
     * Removes obj.
     * Gives NullPointerException if obj is null!
     * 
     * @param obj The object to remove.
     * @return If an object was removed or not.
     */
    public boolean remove(T obj)
    {
        System.out.println("remove(" + obj + ")");
        Entry current = first;

        // special case: if first item
        if (((Object)current).hashCode() == ((Object)obj).hashCode())
        {
            first = current.next;
            return true;
        }

        // checks NEXT item (we don't have a previous pointer)
        while (current != null)
        {
            System.out.println("loooo000p1ng!" + (Object)current.hashCode());
            Entry next = current.next;

            if (next == null)
                return false;

            if (((Object)next).hashCode() == ((Object)obj).hashCode())
            {
                current.next = next.next;
                return true;
            }
            else
            {
                current = current.next;
            }
        }

        return false;
    }

    public T remove()
    {
        System.out.println("remove()");
        // special case: if empty
        if (first == null)
            return null;

        Entry returnMe = first;
        first = first.next;

        return returnMe.value;
    }

    private class Entry
    {
        T value;
        double priority;
        Entry next;

        public Entry(T obj, double priority)
        {
            this.value = obj;
            this.priority = priority;
        }
    }

    public boolean isEmpty()
    {
        return (first == null);
    }
}
