package p2;
import java.util.*;

public class TestProgP2 {
    public static void main(String[] args) {
        HashMap tabell = new HashMap(5); // Ersätt denna rad med nästa rad när du är färdig med Uppgift 1
//        Hashtabell tabell = new Hashtabell(5); // färdig med klassen Hashtabell
        System.out.println("---------------------------------------------");
        tabell.put("hej", "hallo");
        tabell.put("röd", "red");
        tabell.put("vit", "white");
        tabell.put("säng", "bed");
        tabell.put("svart", "black");
        tabell.put("gul", "yellow");
        tabell.put("dator", "computer");
        tabell.put("snö", "snow");
        tabell.put("blå", "blue");
        tabell.put("grön", "green");
        tabell.put("hus", "house");
        tabell.put("springa", "run");
        System.out.println("Antal lagrade värden: " + tabell.size());
        System.out.println("hej på engelska är " + tabell.get("hej"));
        System.out.println("programmera på engelska är " + tabell.get("programmera"));
        System.out.println("svart på engelska är " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
        System.out.println("---------------------------------------------");
        tabell.remove("svart");
        tabell.remove("rosa");
        tabell.remove("röd");
        tabell.remove("säng");
        System.out.println("Antal lagrade värden: " + tabell.size());
        System.out.println("hej på engelska är " + tabell.get("hej"));
        System.out.println("programmera på engelska är " + tabell.get("programmera"));
        System.out.println("svart på engelska är " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
        System.out.println("---------------------------------------------");
        tabell.clear();
        System.out.println("Antal lagrade värden: " + tabell.size());
        System.out.println("hej på engelska är " + tabell.get("hej"));
        System.out.println("programmera på engelska är " + tabell.get("programmera"));
        System.out.println("svart på engelska är " + tabell.get("svart"));
        System.out.println("svart finns i lexikonet: " + tabell.containsKey("svart"));
    }
}
