package steinerland;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.TreeSet;

import graf.Node;
import graf.WDGimpl;
import graf.WeightedDirectedGraph;

public class Steinerland
{
    protected WeightedDirectedGraph<String, Node<String>> graph;
    //protected LinkedList<SteinerlandNode> locations;
    protected TreeSet<String> locations;
    protected Gui gui;

    public Steinerland()
    {
        //graph = new WDGDijkstra();
        //graph = new WDGimpl<String>();
        //graph = new WDGimpl<SteinerlandNode>();
        // TODO : ? Why can we not use generics here
        graph = new WDGimpl<String>();
        locations = new TreeSet<String>();
        //locations = new LinkedList<SteinerlandNode>();

        System.out.println("BAJS!!!");

        gui = new Gui(this);

        loadFile("data/timetable-fixed.tbl");
    }

    public String search(String from, String hours, String minutes, String to)
    {
        // TODO : IAMHERE
        return "Just go to the station and wait for the next train. It'll be there \"soon\".";
    }

    public void addLink(String from, short hoursDeparture, short minutesDeparture, String to, short hoursArrival, short minutesArrival, String train)
    {
        // TODO : ? Do "city nodes" really need to be in the graph
        // Add city nodes to locations list
        locations.add(from);
        locations.add(to);
        // Add nodes (unless they exist).
        SteinerlandNode<String> fromNode = new SteinerlandNode<String>(from, from);
        SteinerlandNode<String> toNode = new SteinerlandNode<String>(to, to);
        SteinerlandNode<String> fromTimeNode = new SteinerlandNode<String>(from, from+hoursDeparture+minutesDeparture, getMinutesFromMidnight(hoursDeparture, minutesDeparture));
        SteinerlandNode<String> toTimeNode = new SteinerlandNode<String>(to, to+hoursArrival+minutesArrival, getMinutesFromMidnight(hoursArrival, minutesArrival));
        // - from
        if (graph.findNode(from) == null)
            graph.insertNode(from, fromNode);
        // - fromTime
        if (graph.findNode(from+hoursDeparture+minutesDeparture) == null)
            graph.insertNode(from+hoursDeparture+minutesDeparture, fromTimeNode);
        // - to
        if (graph.findNode(to) == null)
            graph.insertNode(to, toNode);
        // - toTime
        if (graph.findNode(to+hoursArrival+minutesArrival) == null)
            graph.insertNode(to+hoursArrival+minutesArrival, toTimeNode);

        // Add arc.
        // - from
        //  fromTime
        graph.insertArc(fromNode, toNode, 0);
        // - fromTime
        //  toTime
        graph.insertArc(fromTimeNode, toTimeNode, getTimeDifference(hoursDeparture, minutesDeparture, hoursArrival, minutesArrival));
        //  all from.to
        for (Node<String> nodd : graph.getNeighbours(fromNode))
        {
            SteinerlandNode<String> node = (SteinerlandNode<String>)nodd;
            graph.insertArc(fromTimeNode, node, getTimeDifference((short)(hoursDeparture*60 + minutesDeparture), node.getMinutesFromMidnight()));
        }
        // - to
        //  toTime
        graph.insertArc(toNode, toTimeNode, 0);
        // - toTime
        //  all to.to
        for (Node<String> nodd : graph.getNeighbours(toNode))
        {
            SteinerlandNode<String> node = (SteinerlandNode<String>)nodd;
            graph.insertArc(toTimeNode, node, getTimeDifference((short)(hoursArrival*60 + minutesArrival), node.getMinutesFromMidnight()));
        }

        System.out.println(from + ", " + hoursDeparture + ", " + minutesDeparture + ", " + to + ", " + hoursArrival + ", " + minutesArrival + ", " + train);
    }

    protected short getMinutesFromMidnight(short hours, short minutes)
    {
        return (short)(1440-(hours*60 + minutes));
    }

    protected short getTimeDifference(short totalMinutesDeparture, short totalMinutesArrival)
    {
        short time = (short)(totalMinutesArrival - totalMinutesDeparture);
        if (time > 0)
            return time;
        else
        {
            return (short)(1440+time);
        }
    }

    protected short getTimeDifference(short hoursDeparture, short minutesDeparture, short hoursArrival, short minutesArrival)
    {
        return getTimeDifference(((short)(hoursArrival*60 + minutesArrival)), (short)((hoursDeparture*60 + minutesDeparture)));
    }

    public void loadFile(String path)
    {
        try
        {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // the formatting in the time table file is shady, it's not uniformed..
            // assuming format:
            // <train>
            // <departureCity>:<departureTime>-<arrivalCity>:<arrivalTime>
            // REGEXP to match a line: [^:]*: *[^ ]* *- *[^:]*: *[^$]*
            // <part0>: <part1>:<part2>- <part3>: <part4>:<part5>
            //
            // example:
            // high-speed commuter train 0501
            // Steinerstadt: 05.40 - El Seco: 06.03
            // El Seco: 06.05 - Neubergstadt: 06.39
            // Neubergstadt: 06.41 - Steinerstadt: 06:58
            String line = bufferedReader.readLine();
            String train = "Unknown train";
            while (line != null) 
            {
                System.out.println("line=" + line);
                // Link (an arc)
                if (line.matches("[^:]*: *[^ ]* *- *[^:]*: *[^$]*"))
                {
                    String[] parts = line.split("[:.-]");
                    //System.out.println("From" + parts[0].trim() + ",h" + parts[1].trim() + ",min" + parts[2].trim() + ",To" + parts[3].trim() + ",h" + parts[4].trim() + ",min" + parts[5].trim() + ",train" + train);
                    addLink(parts[0].trim(), Short.parseShort(parts[1].trim()), Short.parseShort(parts[2].trim()), parts[3].trim(), Short.parseShort(parts[4].trim()), Short.parseShort(parts[5].trim()), train);
                }
                else // Train name (or blank line, but who-the-heck cares)
                    train = line.trim();

                line = bufferedReader.readLine();
            }

            bufferedReader.close();

            gui.updateLocations();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    }

    public static void main( String[] args ) {
        new Steinerland();
    }

    /**
     * Gets the locations for this instance.
     *
     * @return The locations.
     */
    public TreeSet<String> getLocations()
    {
        return this.locations;
    }
}
