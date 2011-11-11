package steinerland;

import graph.Arc;
import graph.Graph;
import graph.Node;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.Map;

/**
 * The graphical user interface for Steinerland timetable program.
 */
@SuppressWarnings("serial")
public class Gui extends JFrame 
{
    protected Steinerland steinerland;
    protected Menu menu;
    protected Result result;

    public Gui(Steinerland steinerland)
    {
        this.steinerland = steinerland;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(100, 100, 800, 600);
        setLayout(new BorderLayout());

        // menu / control / input
        menu = new Menu();
        add(menu, BorderLayout.NORTH);
        menu.revalidate();

        // result
        result = new Result();
        add(result, BorderLayout.CENTER);
        result.revalidate();
    }

    public String getPrettyTime(double minutes)
    {
        String hh, mm;
        int h = (int)minutes / 60;
        int m = (int)minutes % 60;
        if (h < 10)
            hh = "0" + h;
        else
            hh = "" + h;
        if (m < 10)
            mm = "0" + m;
        else
            mm = "" + m;
        return hh+":"+mm;
    }

    public void search(String from, String to, int hours, int minutes)
    {
        Graph<String>.Path<String> path = steinerland.search(from, to, hours, minutes);
        double distance = 0;
        String str = " * Suggested Route *\n";
        Steinerland.TimeNode current = (Steinerland.TimeNode)path.getStart();
        //* Skip start node.
        Map<String, graph.Arc<String>> startArcs = path.getArcs(current.getValue());
        if (startArcs == null)
        {
            result.set("Uhmm.. where are you heading to?");
            return;
        }
        for (Map.Entry<String, graph.Arc<String>> kv : startArcs.entrySet())
        {
            graph.Arc<String> arc = kv.getValue();
            if (!(arc.getTo() instanceof Steinerland.TimeNode))
            {
                result.set("Uhmm.. where are you heading to?");
                return;
            }
            current = (Steinerland.TimeNode)arc.getTo();
        }
        while (current != null)
        {
            //* Should only be one arc.
            Map<String, graph.Arc<String>> arcs = path.getArcs(current.getValue());
            if (arcs == null)
                break;
            for (Map.Entry<String, graph.Arc<String>> kv : arcs.entrySet())
            {
                graph.Arc<String> arc = kv.getValue();
                //* Skip goal node.
                if (!(arc.getTo() instanceof Steinerland.TimeNode))
                {
                    current = null;
                    break;
                }
                str += "\n[" + getPrettyTime(current.getHours()*60 + current.getMinutes()) + "] " + current.getName();
                Steinerland.TimeNode next = (Steinerland.TimeNode)arc.getTo();
                double weight = arc.getWeight();
                distance += weight;
                str += " -->-->--" + getPrettyTime(distance) + " -->-->--";
                str += next.getName() + " [" + getPrettyTime(next.getHours()*60 + next.getMinutes()) + "]";
                current = next;
            }
        }
        str += "\n\n * Total traveling time: " + getPrettyTime(distance) + " *";
        result.set(str);
    }

    public void updateLocations()
    {
        menu.updateLocations();
    }

    public void enableQuickMode()
    {
        menu.from.addActionListener(menu);
        menu.hours.addActionListener(menu);
        menu.minutes.addActionListener(menu);
        menu.to.addActionListener(menu);
    }

    protected class Menu extends JPanel implements ActionListener
    {
        protected JLabel fromLabel, toLabel, timeLabel;
        protected JComboBox from, hours, minutes, to;

        public Menu()
        {
            setLayout(new FlowLayout());

            fromLabel = new JLabel("From");
            add(fromLabel);

            from = new JComboBox();
            //from.addActionListener(this);
            add(from);

            timeLabel = new JLabel("At");
            add(timeLabel);

            hours = new JComboBox(new String[]
                    {
                        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                  "11", "12", "13", "14", "15", "16", "17", "18", "20", "21",
                  "22", "23", } );
            //hours.addActionListener(this);
            add(hours);

            minutes = new JComboBox(new String[]
                    {
                        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                    "10", "11", "12", "13", "14", "15", "16", "17", "18", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                    "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                    "51", "52", "53", "54", "55", "56", "57", "58", "59", });
            //minutes.addActionListener(this);
            add(minutes);

            toLabel = new JLabel("To");
            add(toLabel);

            to = new JComboBox();
            //to.addActionListener(this);
            add(to);

            JButton search = new JButton("Search");
            search.addActionListener(this);
            add(search);

            add(new DumpButtom());
        }

        protected void updateLocations()
        {
            //System.out.println("Updating locations...");
            // for each 
            for (Map.Entry<String, Steinerland.City> keyValue : steinerland.getCities().entrySet())
            {
                //System.out.println("city = " + keyValue.getValue());
                //  add to both dropDowns
                from.addItem(keyValue.getKey());
                to.addItem(keyValue.getKey());
            }
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            String from = (String)this.from.getSelectedItem();
            String to = (String)this.to.getSelectedItem();
            int hours = Integer.parseInt((String)this.hours.getSelectedItem());
            int minutes = Integer.parseInt((String)this.minutes.getSelectedItem());
            search(from, to, hours, minutes);
        }

        protected class DumpButtom extends JButton implements ActionListener
        {
            public DumpButtom()
            {
                super("Dump");
                addActionListener(this);
            }

            /**
             * {@inheritDoc}
             * @see ActionListener#actionPerformed(ActionEvent)
             */
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    FileWriter stream = new FileWriter("steinerland-dump.txt");
                    BufferedWriter buffer = new BufferedWriter(stream);
                    buffer.write(" *** " + steinerland.getCities().size() + " cities ***\n");
                    int nodeCount = 0;
                    int arcCount = 0;
                    for (Map.Entry<String, Steinerland.City> kv : steinerland.getCities().entrySet())
                    {
                        Steinerland.City city = kv.getValue();
                        buffer.write(" * " + city.name + " *\n");
                        buffer.write("   " + city.arrivals.size() + " arrivals\n");
                        for (Map.Entry<String, Steinerland.TimeNode> kv2 : city.arrivals.entrySet())
                        {
                            ++nodeCount;
                            buffer.write("     " + kv2.getValue() + "\n");
                            Map<String, Arc<String>> arcs = steinerland.graph.getArcs(kv2.getKey());
                            if (arcs == null)
                                continue;
                            buffer.write("       " + arcs.size() + " arcs (to all departures)\n");
                            for (Map.Entry<String, Arc<String>> kv3 : arcs.entrySet())
                            {
                                ++arcCount;
                                buffer.write("      > " + kv3.getValue().getTo() + " weighing " + kv3.getValue().getWeight() + "\n");
                            }
                        }
                        buffer.write("   " + city.departures.size() + " departures\n");
                        for (Map.Entry<String, Steinerland.TimeNode> kv2 : city.departures.entrySet())
                        {
                            ++nodeCount;
                            buffer.write("     " + kv2.getValue() + "\n");
                            Map<String, Arc<String>> arcs = steinerland.graph.getArcs(kv2.getKey());
                            if (arcs == null)
                                continue;
                            //str += "   " + arcs.size() + "\n";
                            for (Map.Entry<String, Arc<String>> kv3 : arcs.entrySet())
                            {
                                ++arcCount;
                                buffer.write("      > " + kv3.getValue().getTo() + " weighing " + kv3.getValue().getWeight() + "\n");
                            }
                        }
                    }
                    buffer.write(" ... Counted " + nodeCount + " time nodes, " + steinerland.getCities().size() + " city nodes, " + arcCount + " arcs.\n");
                    buffer.write(" . In graph: " + steinerland.graph.getNodes().size() + " total nodes, " + steinerland.graph.getArcs().size() + " total arcs.\n");
                    buffer.close();
                    //System.out.println(steinerland.toString());
                }
                catch (IOException e)
                {
                    System.out.println("Error writing dump file : " + e.getMessage());
                }
            }
        }
    }

    protected class Result extends JPanel
    {
        protected JScrollPane scrollPane;
        protected JTextArea text;

        public Result()
        {
            setLayout(new GridLayout(1,1));
            text = new JTextArea();
            scrollPane = new JScrollPane(text);
            add(scrollPane);
        }

        public void set(String result)
        {
            text.setText(result);
        }
    }
}
