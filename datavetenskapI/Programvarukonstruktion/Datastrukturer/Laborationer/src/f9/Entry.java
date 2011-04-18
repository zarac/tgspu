package f9;

class Entry {
    Object key;
    Object value;
    
    public Entry( Object key, Object value ) {
        this.key = key;
        this.value = value;
    }
    
    // j�mf�r tv� nycklar, returnerar true om lika
    public boolean equals( Object obj ) {
        Entry keyValue = ( Entry )obj;
        return key.equals( keyValue.key );
    }
}
