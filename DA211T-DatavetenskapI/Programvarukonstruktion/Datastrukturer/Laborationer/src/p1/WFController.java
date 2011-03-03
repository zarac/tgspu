package p1;

import java.io.*;

import java.text.Collator;
import java.util.*;

public class WFController {
    public static ArrayList<Road> readRoads(String filenamn) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filenamn));
        ArrayList<Road> roads = new ArrayList<Road>();
        String[] parts;
        String txt;
        txt = br.readLine();
        while (txt != null) {
            parts = txt.split(",");
            roads.add(new Road(parts[0], parts[1], Integer.parseInt(parts[2])));
            txt = br.readLine();
        }
        return roads;
    }

    //public static ArrayList<Road> roadsFrom(ArrayList<Road> roads, String place) {
        //ArrayList<Road> roadsFrom = new ArrayList<Road>();
        //// Uppgift 7, komplettera med kod
        //return roadsFrom;
    //}

    // Uppgift 8, komplettera med metoden places


    public static void testRoadsFrom(ArrayList<Road> roads, String place) {
        ArrayList<Road> roadsFromPlace = WFController.roadsFrom(roads, place);
        System.out.println("VÄGAR FRÅN " + place + ":");
        for (Road road : roadsFromPlace) {
            System.out.println(road);
        }
    }

   public static void testPlaces(ArrayList<Road> roads) {
       ArrayList<String> places = WFController.places(roads);
       System.out.println("Orter med vägar:");
       for (String place : places) {
           System.out.println(place);
       }
   }

    public static ArrayList<Road> roadsFrom(ArrayList<Road> roads, String place)
    {
        ArrayList<Road> matches = new ArrayList<Road>();
        for (int i = 0; i < roads.size(); i++)
            if (roads.get(i).getFrom().equals(place))
                matches.add(roads.get(i));

        return matches;
    }

    public static ArrayList<String> places(ArrayList<Road> roads)
    {
        ArrayList<String> places = new ArrayList<String>();

        for (int i = 0; i < roads.size(); i++)
        {
            if (!(places.contains(roads.get(i).getTo())))
                places.add(roads.get(i).getTo());

            if (!(places.contains(roads.get(i).getFrom())))
                places.add(roads.get(i).getFrom());
        }

        Collections.sort(places, Collator.getInstance());
        return places;
    }

    public static void main(String[] args) {
        try {
            //ArrayList<Road> roads = WFConTotroller.readRoads("C:/filer/roads.txt");
            ArrayList<Road> roads = WFController.readRoads("src/p1/roads.txt");
            WFController.testRoadsFrom(roads, "Lund");
            WFController.testRoadsFrom(roads, "Kristianstad");
            WFController.testRoadsFrom(roads, "Stockholm");
            WFController.testPlaces(roads);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
