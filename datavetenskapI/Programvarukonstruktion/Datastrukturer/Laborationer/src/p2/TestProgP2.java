package p2;
import java.util.*;

public class TestProgP2 {
    public static void main(String[] args) {
        HashMap tabell = new HashMap(5); // Ers�tt denna rad med n�sta rad n�r du �r f�rdig med Uppgift 1
//        Hashtabell tabell = new Hashtabell(5); // f�rdig med klassen Hashtabell
        System.out.println("---------------------------------------------");
        tabell.put("hej", "hallo");
        tabell.put("r�d", "red");
        tabell.put("vit", "white");
        tabell.put("s�ng", "bed");
        tabell.put("svart", "black");
        tabell.put("gul", "yellow");
        tabell.put("dator", "computer");
        tabell.put("sn�", "snow");
        tabell.put("bl�", "blue");
        tabell.put("gr�n", "green");
        tabell.put("hus", "house");
        tabell.put("springa", "run");
        System.out.println("Antal lagrade v�rden: " + tabell.size());
        System.out.println("hej p� engelska �r " + tabell.get("hej"));
        System.out.println("programmera p� engelska �r " + tabell.get("programmera"));
        System.out.println("svart p� engelska �r " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
        System.out.println("---------------------------------------------");
        tabell.remove("svart");
        tabell.remove("rosa");
        tabell.remove("r�d");
        tabell.remove("s�ng");
        System.out.println("Antal lagrade v�rden: " + tabell.size());
        System.out.println("hej p� engelska �r " + tabell.get("hej"));
        System.out.println("programmera p� engelska �r " + tabell.get("programmera"));
        System.out.println("svart p� engelska �r " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
        System.out.println("---------------------------------------------");
        tabell.clear();
        System.out.println("Antal lagrade v�rden: " + tabell.size());
        System.out.println("hej p� engelska �r " + tabell.get("hej"));
        System.out.println("programmera p� engelska �r " + tabell.get("programmera"));
        System.out.println("svart p� engelska �r " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
    }
}
