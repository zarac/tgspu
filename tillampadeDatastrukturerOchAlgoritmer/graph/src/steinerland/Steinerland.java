package steinerland;

import graph.Arc;
import graph.Graph;
import graph.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * A program to display a time-table. Used to demonstrate usage of the Graph
 * data structure.
 */
public class Steinerland
{
    /**
     * The cities. Used for finding a route.
     */
    protected TreeSet<String> cities;

    /**
     * The graph.
     */
    protected Graph<String> graph;

    /**
     * Instantiates the Steinerland program.
     */
    public Steinerland()
    {
        System.out.println("Creating a Steinerland instance.");
        graph = new Graph<String>();
        cities = new TreeSet<String>();
        // start gui
        new Gui();
        // load file
        readTimeTable("data/timetable-fixed-test_data.tbl");
        dumpGraph();
    }

    /**
     * Adds a leg / link / hop; i.e. an entry in the time-table. This means from
     * a city to a neighbouring city including the hours and minutes of
     * departure and arrival as well as the train.
     */
    public void addLeg(String from, short hoursDeparture, short
            minutesDeparture, String to, short hoursArrival, short
            minutesArrival, String train)
    {
        // Add city nodes to cities list
        cities.add(from);
        cities.add(to);
        // Add nodes (unless they exist).
        // - from
        TimeNode fromNode = new TimeNode(from);
        if (graph.getNode(fromNode) == null)
            graph.addNode(fromNode);
        // - fromTime
        TimeNode fromTimeNode = new TimeNode(from);
        if (graph.getNode(fromTimeNode) == null)
            graph.addNode(fromTimeNode);
        // - to
        TimeNode toNode = new TimeNode(to);
        if (graph.getNode(toNode) == null)
            graph.addNode(toNode);
        // - toTime
        TimeNode toTimeNode = new TimeNode(to);
        if (graph.getNode(toTimeNode) == null)
            graph.addNode(toTimeNode);

        // Add arcs
        //// Add arc.
        // - fromTime
        //      toTime
        graph.addArc(fromTimeNode, toTimeNode,
                getTimeDifference(fromTimeNode.getMinutesFromMidnight(),
                    toTimeNode.getMinutesFromMidnight())); ////      all from.to
        //for (Node<String> nodd : graph.getNeighbours(fromNode)) 
        //{
            //// TODO : uncomment
            ////SteinerlandNode<String> node = (SteinerlandNode<String>) nodd;
            //graph.insertArc(fromTimeNode, node, getTimeDifference(fromTimeNode.getMinutesFromMidnight(), node.getMinutesFromMidnight()));
            //graph.insertArc(node, fromTimeNode, getTimeDifference(node.getMinutesFromMidnight(), fromTimeNode.getMinutesFromMidnight()));
        //}

        //// - from
        ////      fromTime
        //graph.insertArc(fromNode, fromTimeNode, 0);

        //// - toTime
        ////      all to.to (and 
        //for (Node<String> nodd : graph.getNeighbours(toNode))
        //{
            //// TODO : uncomment
            ////SteinerlandNode<String> node = (SteinerlandNode<String>) nodd;
            //graph.insertArc(toTimeNode, node, getTimeDifference(toTimeNode.getMinutesFromMidnight(), node.getMinutesFromMidnight()));
            //graph.insertArc(node, toTimeNode, getTimeDifference(node.getMinutesFromMidnight(), toTimeNode.getMinutesFromMidnight()));
        //}

        //// - to
        ////      toTime
        ////graph.insertArc(toNode, toTimeNode, 0);
    }

    /**
     * Generates a key, given a city and a time.
     *
     * @param city The city.
     * @param hours The hours.
     * @param minutes The minutes.
     *
     * @return The key.
     */
    protected String generateKey(String city, short hours, short minute)
    {
        return city + hours + minute;
    }

    /**
     * Gets the number of minutes from midnight, given hours and minutes.
     *
     * @param hours The hours.
     * @param minutes the minutes;
     *
     * @return The minutes from midnight.
     */
    protected short getMinutesFromMidnight(short hours, short minutes)
    {
        return (short) (hours * 60 + minutes);
    }

    /**
     * Gets the time difference in minutes between two times, given minutes from
     * midnight of each time.
     */
    protected short getTimeDifference(short totalMinutesDeparture, short
            totalMinutesArrival)
    {
        short time = (short) (totalMinutesArrival - totalMinutesDeparture);
        if (time > 0)
            return time;
        else
            return (short) (1440 + time);
    }

    /**
     * Gets the time difference in minutes between two times, given the hours
     * and minutes of each.
     */
    protected short getTimeDifference(short hoursDeparture, short
            minutesDeparture, short hoursArrival, short minutesArrival)
    {
        return getTimeDifference(((short) (hoursArrival * 60 + minutesArrival)),
                (short) ((hoursDeparture * 60 + minutesDeparture)));
    }

    /**
     * Dumps the graph, its nodes and arcs, for debugging purpose.
     */
    public void dumpGraph()
    {
        int nodeCount = 0;
        int arcCount = 0;
        for (Node<String> node : graph.getNodes())
        {
            System.out.println("[" + ++nodeCount + "]node = " + node);
            for (Arc arc : graph.getArcs(node))
            {
                System.out.println("[" + ++arcCount + "]arc = " + arc);
            }
        }
    }

    /**
     * Read a timetable file. Opens a file, reads each line and handles it like
     * it should.
     *
     * The formatting in the time table file is shady, it's not uniformed.
     * assuming format:
     * <train>
     * <departureCity>:<departureTime>-<arrivalCity>:<arrivalTime>
     * REGEXP to match a line: [^:]*: *[^ ]* *- *[^:]*: *[^$]*
     * <part0>: <part1>:<part2>- <part3>: <part4>:<part5>
     *
     * example:
     * high-speed commuter train 0501
     * Steinerstadt: 05.40 - El Seco: 06.03
     * El Seco: 06.05 - Neubergstadt: 06.39
     * Neubergstadt: 06.41 - Steinerstadt: 06:58
     */
    protected void readTimeTable(String path)
    {
        try
        {
            FileReader file = new FileReader(path);
            BufferedReader buffer = new BufferedReader(file);
            String line = buffer.readLine();
            String train = "Unknown train";
            while (line != null)
            {
                // Link (an arc)
                //  add city node(s) that don't exist
                if (line.matches("[^:]*: *[^ ]* *- *[^:]*: *[^$]*"))
                {
                    String[] parts = line.split("[:.-]");
                    System.out.println(parts[0] + " " +
                            getMinutesFromMidnight(Short.parseShort(parts[1].trim()),
                                Short.parseShort(parts[2].trim())) + " -" +
                            parts[3] + " " +
                            getMinutesFromMidnight(Short.parseShort(parts[4].trim()),
                                Short.parseShort(parts[5].trim())));
                    String fromCity = parts[0].trim();
                    short fromHours = Short.parseShort(parts[1].trim());
                    short fromMinutes =  Short.parseShort(parts[2].trim());
                    String toCity = parts[3].trim();
                    short toHours = Short.parseShort(parts[4].trim());
                    short toMinutes =  Short.parseShort(parts[5].trim());
                    addLeg(fromCity, fromHours, fromMinutes, toCity, toHours, toMinutes, train);
                }
                else // Train name (or blank line, but who-the-heck cares?)
                {
                    train = line.trim();
                }
                line = buffer.readLine();
            }
            buffer.close();
            file.close();
            //gui.updateLocations();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    }

    protected class TimeNode extends graph.Node<String>
    {
        protected TimeNode(String value)
        {
            super(value);
        }

        public short getMinutesFromMidnight()
        {
            // TODO : implement
            return 0;
        }
    }
}
