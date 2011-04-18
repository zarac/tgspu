package f9;

class Bucket {
    static final int empty = 0, occupied = 1, removed = 2;
    int state = empty;
    Object key;
    Object value;
}