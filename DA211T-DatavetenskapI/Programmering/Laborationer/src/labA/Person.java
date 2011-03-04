package labA;

public class Person {
	private String namn;
	private int alder;
	
	/**
     * Skapar ett person-objekt med bifogat namn och �lder
	 * @param namn
	 * @param �lder
	 */
	public Person(String namn, int �lder) {
		this.namn = namn;
		this.alder = �lder;
	}
	
	/**
	 * Returnerar personens namn
	 * @return personenes namn
	 */
	public String getNamn() {
		return namn;
	}
	
	/**
	 * Returnerar personens �lder
	 * @return personens �lder
	 */
	public int get�lder() {
		return alder;
	}
	
	public String toString() {
		return namn + " " + alder;
	}
}
