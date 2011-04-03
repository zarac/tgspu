package labA;

public class Person {
	private String namn;
	private int alder;
	
	/**
     * Skapar ett person-objekt med bifogat namn och ålder
	 * @param namn
	 * @param ålder
	 */
	public Person(String namn, int ålder) {
		this.namn = namn;
		this.alder = ålder;
	}
	
	/**
	 * Returnerar personens namn
	 * @return personenes namn
	 */
	public String getNamn() {
		return namn;
	}
	
	/**
	 * Returnerar personens ålder
	 * @return personens ålder
	 */
	public int getÅlder() {
		return alder;
	}
	
	public String toString() {
		return namn + " " + alder;
	}
}
