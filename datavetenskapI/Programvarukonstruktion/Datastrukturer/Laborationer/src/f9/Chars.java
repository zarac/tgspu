/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f9;

/**
 *
 * @author TSROAX
 */
public class Chars {

    private char[] value;

    public Chars(String str) {
        value = str.toCharArray();
    }
    
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj instanceof Chars) {
	    Chars anotherChars = (Chars)obj;
	    int n = value.length;
	    if (n == anotherChars.value.length) {
		while (n-- != 0) {
		    if (value[n] != anotherChars.value[n])
			return false;
		}
		return true;
	    }
	}
	return false;
    }

    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < value.length; i++) {
            hash = 31 * hash + value[i];
        }
        return hash;
    }
    
    public String toString() {
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        String[] s1 = {"a", "b", "c", "aa", "ab", "ac", "aaa", "aab", "aac"};
        Chars[] s2 = {new Chars("a"), new Chars("b"), new Chars("c"), 
                      new Chars("aa"), new Chars("ab"), new Chars("ac"), 
                      new Chars("aaa"), new Chars("aab"), new Chars("aac")};
        for (String s : s1) {
            System.out.println(s + ": " + s.hashCode() + ", " + s.hashCode() % 100);
        }
        for (Chars s : s2) {
            System.out.println(s + ": " + s.hashCode() + ", " + s.hashCode() % 100);
        }
    }
}

