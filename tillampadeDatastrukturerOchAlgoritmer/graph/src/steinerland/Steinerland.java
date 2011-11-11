package steinerland;

import graph.Arc;
import graph.Graph;
import graph.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A program to display a time-table. Used to demonstrate usage of the Graph
 * data structure.
 */
public class Steinerland
{
    /**
     * The cities. Used for finding a route.
     */
    protected Map<String, City> cities;

    /**
     * The graph.
     */
    protected Graph<String> graph;

    protected Gui gui;

    /**
     * Instantiates the Steinerland program.
     */
    public Steinerland()
    {
        //System.out.println("Creating a Steinerland instance.");
        graph = new Graph<String>();
        cities = new HashMap<String, City>();
        //departures = new HashMap<String, List<Node<String>>>();
        //arrivals = new HashMap<String, List<Node<String>>>();
        // load file
        readTimeTable("data/timetable-fixed.tbl");
        // start gui
        gui = new Gui(this);
        gui.updateLocations();
        gui.enableQuickMode();
        //System.out.println(graph);
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
        // Get / create cities
        City fromCity = getOrCreateCity(from);
        City toCity = getOrCreateCity(to);

        // Get / create nodes and add to departures / arrivals lists
        // - from
        Node<String> fromNode = getOrCreateNode(from);
        // - fromTime
        TimeNode fromTimeNode = getOrCreateNode(from, hoursDeparture, minutesDeparture);
        fromCity.addDeparture(fromTimeNode);
        // - to
        Node<String> toNode = getOrCreateNode(to);
        // - toTime
        TimeNode toTimeNode = getOrCreateNode(to, hoursArrival, minutesArrival);
        toCity.addArrival(toTimeNode);

        // Add arcs
        // arrivals > fromTime
        for (Map.Entry<String, TimeNode> kv : fromCity.getArrivals().entrySet())
        {
            TimeNode node = kv.getValue();
            if (node.equals(fromTimeNode))
            {
                //System.out.println("skipping arc : arrivals* > fromTime : " + fromTimeNode + " > " + kv.getValue());
                continue;
            }
            //System.out.println("adding arc : arrivals* > fromTime : " + fromTimeNode + " > " + kv.getValue());
            graph.addArc(kv.getValue(), fromTimeNode, getTimeDifference(
                        kv.getValue().getMinutesFromMidnight(),
                        fromTimeNode.getMinutesFromMidnight()));
        }
        // fromTime > toTime
        graph.addArc(fromTimeNode, toTimeNode,
                getTimeDifference(fromTimeNode.getMinutesFromMidnight(),
                    toTimeNode.getMinutesFromMidnight()));
        // toTime - goal
        graph.addArc(toTimeNode, toNode, 0);
        // toTime - departures
        for (Map.Entry<String, TimeNode> kv : toCity.getDepartures().entrySet())
        {
            TimeNode node = kv.getValue();
            if (node.equals(toTimeNode))
            {
                //System.out.println("skipping arc : toTime > departures* : " + toTimeNode + " > " + kv.getValue());
                continue;
            }
            //System.out.println("adding arc : toTime > departures* : " + toTimeNode + " > " + kv.getValue());
            graph.addArc(toTimeNode, kv.getValue(),
                    getTimeDifference(toTimeNode.getMinutesFromMidnight(),
                        kv.getValue().getMinutesFromMidnight()));
        }
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
     * Get a list of available cities / stations.
     */
    protected Map<String, City> getCities()
    {
        return cities;
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

    protected Node<String> getNextDeparture(String city, int hours, int minutes)
    {
        // TODO : implement
        //Map<String, Node<String>> departures = cities.get(city).getDepartures();
        // IAMHERE, kinda ; )
        // getMinutesFromMidnight() -
        //List<Node<String>> allDeparturesFromCity = departures.get(city);
        return null;
    }

    protected City getOrCreateCity(String name)
    {
        City city = cities.get(name);
        if (city == null)
        {
            city = new City(name);
            cities.put(name, city);
        }
        return city;
    }

    protected Node<String> getOrCreateNode(String name)
    {
        Node<String> node = graph.getNode(name);
        if (node == null)
        {
            node = new Node<String>(name);
            graph.addNode(node);
        }
        return node;
    }

    protected TimeNode getOrCreateNode(String name, int hours, int minutes)
    {
        String key = makeTimeNodeKey(name, hours, minutes);
        TimeNode node = (TimeNode)graph.getNode(key);
        if (node == null)
        {
            node = new Steinerland.TimeNode(name, hours, minutes);
            graph.addNode(node);
        }
        return node;
    }

    /**
     * Gets the time difference in minutes between two times, given minutes from
     * midnight of each time.
     */
    protected short getTimeDifference(short totalMinutesDeparture, short
            totalMinutesArrival)
    {
        short time = (short) (totalMinutesArrival - totalMinutesDeparture);
        if (time >= 0)
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

    public static String makeTimeNodeKey(String value, int hours, int minutes)
    {
        return value.toString() + hours + minutes;
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
                    //System.out.println(parts[0] + " " +
                            //getMinutesFromMidnight(Short.parseShort(parts[1].trim()),
                                //Short.parseShort(parts[2].trim())) + " -" +
                            //parts[3] + " " +
                            //getMinutesFromMidnight(Short.parseShort(parts[4].trim()),
                                //Short.parseShort(parts[5].trim())));
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
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    }


    protected TimeNode createStartNode(String start, int hours, int minutes)
    {
        TimeNode node = new TimeNode("start (" + start + ")", hours, minutes);
        graph.addNode(node);
        City city = getOrCreateCity(start);
        //city.addArrival(node);
        Node<String> possibleGoal = graph.getNode(start);
        //System.out.println("possibleGoal = " + possibleGoal);
        //* toTime - possibleGoal
        graph.addArc(node, possibleGoal, 0);
        //* toTime - departures
        for (Map.Entry<String, TimeNode> kv : city.getDepartures().entrySet())
        {
            //System.out.println("adding arc : toTime > departures* : " + node + " > " + kv.getValue());
            graph.addArc(node, kv.getValue(),
                    getTimeDifference(node.getMinutesFromMidnight(),
                        kv.getValue().getMinutesFromMidnight()));
        }
        return node;
    }

    public Graph<String>.Path<String> search(String from, String to, int hours, int minutes)
    {
        boolean deleteStartWhenDone = false;
        //System.out.println("Searching from " + from + " to " + to + " at " + hours + ":" + minutes + ".");
        // create a bogus arrival node so we can search from it
        TimeNode start = createStartNode(from, hours, minutes);
        Node<String> goal = graph.getNode(to);
        Graph<String>.Path<String> shortestPath = graph.shortestPath(start, goal);
        // clean up
        graph.removeNode(start.getValue());
        return shortestPath;
    }

    /**
     * Dumps the graph, its nodes and arcs, for debugging purpose.
     */
    public String toString()
    {
        String str = " *** " + cities.size() + " cities ***\n";
        int nodeCount = 0;
        int arcCount = 0;
        for (Map.Entry<String, City> kv : cities.entrySet())
        {
            City city = kv.getValue();
            str += " * " + city.name + " *\n";
            str += "   " + city.arrivals.size() + " arrivals\n";
            for (Map.Entry<String, TimeNode> kv2 : city.arrivals.entrySet())
            {
                ++nodeCount;
                str += "     " + kv2.getValue() + "\n";
                Map<String, Arc<String>> arcs = graph.getArcs(kv2.getKey());
                if (arcs == null)
                    continue;
                str += "       " + arcs.size() + " arcs (to all departures)\n";
                for (Map.Entry<String, Arc<String>> kv3 : arcs.entrySet())
                {
                    ++arcCount;
                    str += "      > " + kv3.getValue().getTo() + "\n";
                }
            }
            str += "   " + city.departures.size() + " departures\n";
            for (Map.Entry<String, TimeNode> kv2 : city.departures.entrySet())
            {
                ++nodeCount;
                str += "     " + kv2.getValue() + "\n";
                Map<String, Arc<String>> arcs = graph.getArcs(kv2.getKey());
                if (arcs == null)
                    continue;
                //str += "   " + arcs.size() + "\n";
                for (Map.Entry<String, Arc<String>> kv3 : arcs.entrySet())
                {
                    ++arcCount;
                    str += "      > " + kv3.getValue().getTo() + "\n";
                }
            }
        }
        str += " ... Counted " + nodeCount + " time nodes, " + cities.size() + " city nodes, " + arcCount + " arcs.\n";
        str += " . In graph: " + graph.getNodes().size() + " total nodes, " + graph.getArcs().size() + " total arcs.\n";
        return str;
    }

    public class City
    {
        protected String name;
        protected Map<String, TimeNode> departures;
        protected Map<String, TimeNode> arrivals;

        public City(String name)
        {
            this.name  = name;
            departures = new HashMap<String, TimeNode>();
            arrivals   = new HashMap<String, TimeNode>();
        }

        public void addArrival(TimeNode node)
        {
            arrivals.put(node.getValue(), node);
        }

        public void addDeparture(TimeNode node)
        {
            departures.put(node.getValue(), node);
        }

        public Map<String, TimeNode> getArrivals()
        {
            return arrivals;
        }

        public Map<String, TimeNode> getDepartures()
        {
            return departures;
        }

        public String toString()
        {
            return "City[name=" + name + ", " + departures.size() + " departures, " + arrivals.size() + " arrivals]";
        }
    }

    protected class TimeNode extends graph.Node<String>// implements Comparator<Node<String>>
    {
        protected int hours, minutes;

        protected TimeNode(String value, int hours, int minutes)
        {
            super(value);
            this.hours = hours;
            this.minutes = minutes;
        }

        public int getHours()
        {
            return hours;
        }

        public int getMinutes()
        {
            return minutes;
        }

        public String getName()
        {
            return super.getValue();
        }

        public String getValue()
        {
            return Steinerland.makeTimeNodeKey(value, hours, minutes);
        }

        public short getMinutesFromMidnight()
        {
            return (short)(minutes + hours*60);
        }

        public String toString()
        {
            return value.toString() + " @ " + hours + ":" + minutes;
        }

        public int compare(Node<String> o1, Node<String> o2)
        {
            // TODO : implement if we need, else remove
            // compare cities first then times
            //System.out.println("compaarrzz??" + o1.getValue() + " == " + o2.getValue());
            return 0;
        }

        public int hashCode()
        {
            //System.out.println("?hashc0ding ... value = " + value  + ", code = " + (value.toString() + getMinutesFromMidnight()).hashCode());
            return (value.toString() + getMinutesFromMidnight()).hashCode();
        }

        //public boolean equals(Node<String> node)
        //{
            //System.out.println("equalszzz??" + value + " == " + node.getValue());
            //return (value.equals(node.getValue()) && getMinutesFromMidnight() == ((TimeNode)node).getMinutesFromMidnight());
        //}
    }
}
