package phonebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
    PhoneBook phoneBook;

    protected JPanel control;
    protected JPanel buttons;
    protected JPanel fields;
    protected JTextField name;
    protected JTextField number;
    protected JPanel entry;

    protected JPanel display;
    protected JScrollPane pane;
    protected JTextArea list;
    protected TreeView treeView;
    protected StatisticsView stats;
    protected int[] lastTestSizes;
    protected int view;
    protected int VIEW_LIST = 0;
    protected int VIEW_TREE = 1;
    protected int VIEW_STATS = 2;
    protected int VIEW_RESULT = 3;

    Log log;

    protected JMenuBar menuBar;
    protected JMenu menuFile;
    protected JMenu menuView;

    public GUI(PhoneBook phoneBook)
    {
        this.phoneBook = phoneBook;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50, 50, 800, 600);
        setLayout(new BorderLayout());

        // *entry* - the input fields and their actions
        entry = new JPanel(new BorderLayout());
        //entry = new JPanel(new GridLayout(1,2));

        // add
        entry.add(new AddEntry(), BorderLayout.EAST);

        // name
        name = new NameField();
        entry.add(name, BorderLayout.CENTER);

        entry.revalidate();
        add(entry, BorderLayout.NORTH);

        // *display*
        display = new JPanel(new GridLayout(1,1));

        // list
        list = new JTextArea();
        pane = new JScrollPane(list);
        pane.setAutoscrolls(true);
        treeView = new TreeView();
        display.add(pane);

        add(display, BorderLayout.CENTER);
        display.revalidate();

        // statistics
        stats = new StatisticsView();

        // *log*
        log = new Log();
        add(log, BorderLayout.SOUTH);
        log.revalidate();
        repaint();

        // *menu*
        menuBar = new JMenuBar();
        // file menu
        menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        menuBar.add(menuFile);
        // load
        menuFile.add(new LoadOption());
        // save
        menuFile.add(new SaveOption());

        // edit menu
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic('E');
        menuEdit.add(new AddRandomEntry());
        menuEdit.add(new AddRandomEntries());
        menuEdit.add(new RunTestsOption());
        menuEdit.add(new ReRunTestsOption());
        menuBar.add(menuEdit);

        // view menu
        menuView = new JMenu("View");
        menuView.setMnemonic('V');
        menuBar.add(menuView);
        menuView.add(new ResultOption());
        menuView.add(new AllByNameOption());
        menuView.add(new AllByNumberOption());
        menuView.addSeparator();
        menuView.add(new TreeOption());
        menuView.add(new StatisticsOption());
        menuView.addSeparator();
        menuView.add(new LogOption());

        setJMenuBar(menuBar);
        repaint();
        menuBar.revalidate();
    }

    protected void setView(int view)
    {
        this.view = view;
        display.removeAll();

        if (view == VIEW_TREE)
            display.add(treeView);
        else if (view == VIEW_LIST)
            display.add(list);
        else if (view == VIEW_STATS)
        {
            stats.tests = doTests();
            display.add(stats);
        }
        else if (view == VIEW_RESULT)
        {
            // not optimal it does new search on every view change, probably should remember last one... but do we care?! :7
            doSearch();
            display.add(list);
        }

        display.revalidate();
        display.repaint();
    }

    protected void doSearch()
    {
        String name = phoneBook.gui.name.getText();
        list.setText("finding '" + name + "'...\n\n");

        list.append("    * By Name *\n");
        PhoneBookEntry nameEntry = phoneBook.byName.find(name.toLowerCase());
        if (nameEntry != null)
            list.append(nameEntry.toString()+"\n");

        list.append("\n\n    * By Number *\n");
        PhoneBookEntry numberEntry = phoneBook.byNumber.find(name.toLowerCase());
        if (numberEntry != null)
            list.append(numberEntry.toString()+"\n");
    }

    protected SpeedTest[] doTests()
    {
        if (lastTestSizes == null)
            lastTestSizes = new int[]{1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};

        return doTests(lastTestSizes);
    }

    protected SpeedTest[] doTests(int[] sizes)
    {
        lastTestSizes = sizes;

        SpeedTest[] tests = new SpeedTest[sizes.length];

        for (int i = 0; i < sizes.length; i++) 
        {
            System.out.println("Generating file with '" + sizes[i] + "' entries.");
            tests[i] = phoneBook.generateTestFile("test" + sizes[i] + ".txt", sizes[i]);
            System.out.println(tests[i].toString());
            System.out.println("... total time: '" + tests[i].time + "'ms.");
        }

        return tests;
    }

    public class Log extends JPanel
    {
        protected JScrollPane logPane;
        protected JTextArea log;

        Log()
        {
            setVisible(false);
            setMaximumSize(new Dimension(100, 100));
            setPreferredSize(new Dimension(100, 100));
            setMinimumSize(new Dimension(100, 100));
            setLayout(new GridLayout(1,1));
            log = new JTextArea();
            append("TehE LOOOG\n\ntest\n\n");
            logPane = new JScrollPane(log);
            add(logPane);

            log.repaint();
            logPane.repaint();
            repaint();

            log.revalidate();
            logPane.revalidate();
            revalidate();
        }

        public void append(String message)
        {
            log.append(message + "\n");
            log.setCaretPosition(log.getText().length());
        }
    }

    protected class AddEntry extends JButton implements ActionListener
    {
        public AddEntry()
        {
            setText("Add");
            setMnemonic('A');
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            String[] search = GUI.this.name.getText().split("=");
            //String name = GUI.this.name.getText();
            //String number = GUI.this.number.getText();
            phoneBook.add(new PhoneBookEntry(search[0].trim(), search[1].trim()));
            phoneBook.showAll();

            display.repaint();
        }
    }

    protected class NameField extends JTextField implements KeyListener
    {
        public NameField()
        {
            setText("<name>");
            addKeyListener(this);
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyTyped(KeyEvent)
         */
        public void keyTyped(KeyEvent e)
        {
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyPressed(KeyEvent)
         */
        public void keyPressed(KeyEvent e)
        {
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyReleased(KeyEvent)
         */
        public void keyReleased(KeyEvent e)
        {
            setView(VIEW_RESULT);
        }
    }

    protected class NumberField extends JTextField implements ActionListener
    {
        public NumberField()
        {
            setText("<number>");
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            list.setText("");

            String number = phoneBook.gui.number.getText();
            list.append("    * By Number * (" + number + ")\n");
            PhoneBookEntry numberEntry = phoneBook.byNumber.find(number);
            if (numberEntry != null)
                list.append(numberEntry.toString()+"\n");

            setView(VIEW_LIST);
        }
    }

    protected class SaveOption extends JMenuItem implements ActionListener
    {
        public SaveOption()
        {
            setText("Save");
            setMnemonic('S');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            PhoneBook.saveToDisk("blah.txt", phoneBook.byName);
        }
    }

    protected class LoadOption extends JMenuItem implements ActionListener
    {
        public LoadOption()
        {
            setText("Load");
            setMnemonic('L');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            phoneBook.loadFromDisk("blah.txt");
            phoneBook.showAll();
            display.repaint();
        }
    }

    protected class AddRandomEntry extends JMenuItem implements ActionListener
    {
        public AddRandomEntry()
        {
            setText("Add Random");
            setMnemonic('R');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            phoneBook.addRandom();
        }
    }

    protected class AddRandomEntries extends JMenuItem implements ActionListener
    {
        public AddRandomEntries()
        {
            setText("Add Many Random");
            setMnemonic('M');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            int count = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter count"));
            int onePercent = count/100;
            int percent = 0;
            for (int i = 1; i <= count; i++) 
            {
                phoneBook.addRandom();
                if (i % onePercent == 0)
                    System.out.println(++percent + "% - " + i);
            }
            System.out.println("DONE!");

            phoneBook.showAll();
            display.repaint();
        }
    }

    protected class ReRunTestsOption extends JMenuItem implements ActionListener
    {
        public ReRunTestsOption()
        {
            setText("Re-run Tests");
            setMnemonic('T');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            //stats.tests = doTests();
            phoneBook.runTests(stats.tests);
            setView(VIEW_STATS);
        }
    }

    protected class RunTestsOption extends JMenuItem implements ActionListener
    {
        public RunTestsOption()
        {
            setText("Specify Tests");
            setMnemonic('S');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            String input = JOptionPane.showInputDialog(this, "Enter size of tests in a comma separated list.\n\nPress escape for default.\n\nExample: 1000,2000,3000");

            if (input == null)
                phoneBook.runTests(stats.tests);
            else
            {
                // generate new tests before running, perhaps use same test data?
                String[] counts = input.split(",");
                int[] intCounts = new int[counts.length];
                for (int i = 0; i < counts.length; i++) 
                    intCounts[i] = Integer.parseInt(counts[i].trim());
                Arrays.sort(intCounts);
                stats.tests = doTests(intCounts);
            }
            setView(VIEW_STATS);
        }
    }

    protected class ResultOption extends JMenuItem implements ActionListener
    {
        public ResultOption()
        {
            setText("Result");
            setMnemonic('R');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            setView(VIEW_RESULT);
        }
    }

    protected class AllByNameOption extends JMenuItem implements ActionListener
    {
        public AllByNameOption()
        {
            setText("All By Name");
            setMnemonic('N');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            phoneBook.allByName();
            setView(VIEW_LIST);
        }
    }

    protected class AllByNumberOption extends JMenuItem implements ActionListener
    {
        public AllByNumberOption()
        {
            setText("All By Number");
            setMnemonic('U');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            phoneBook.allByNumber();
            setView(VIEW_LIST);
        }
    }

    protected class StatisticsOption extends JMenuItem implements ActionListener
    {
        public StatisticsOption()
        {
            setText("Statistics");
            setMnemonic('S');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            setView(VIEW_STATS);
        }
    }

    protected class TreeOption extends JMenuItem implements ActionListener
    {
        public TreeOption()
        {
            setText("Tree");
            setMnemonic('T');
            setEnabled(true);
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            setView(VIEW_TREE);
        }
    }

    protected class LogOption extends JMenuItem implements ActionListener
    {
        public LogOption()
        {
            setText("Toggle Log");
            setMnemonic('L');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (log.isVisible() == true)
                log.setVisible(false);
            else
                log.setVisible(true);
        }
    }

    protected class TreeView extends JPanel
    {
        int width, height;
        int firstLevel = 1;
        int maxLevel = 9;
        int nodeSize = 30;
        int nodeWidth = 80;
        int nodeHeight = 20;
        int widthSpacing = 50;
        int heightSpacing = 50;

        int startX;

        Graphics g;

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            this.g = g;

            width = getWidth();
            height = getHeight();

            startX = getWidth()/2 - nodeWidth/2;
            removeAll();
            if (phoneBook.byName.root != null)
                drawNode(phoneBook.byName.root, 0, startX, 10, firstLevel);
        }

        public void drawNode(AVLTreeNode<PhoneBookEntry> node, int parentX, int
                x, int y, int level)
        {
            if (level < maxLevel)
            {
                level++;
                int offset = Math.abs(x - (parentX + x)/2);

                if (node.left != null)
                {
                    if (node != node.left.parent)
                        g.setColor(Color.RED);
                    else
                        g.setColor(Color.BLACK);
                    g.drawLine(x + nodeWidth/2, y + nodeHeight/2, x-offset +
                            nodeWidth/2, y + heightSpacing + nodeHeight/2);
                    drawNode(node.left, x, x-offset, y + heightSpacing, level);
                }

                if (node.right != null)
                {
                    if (node != node.right.parent)
                        g.setColor(Color.RED);
                    else
                        g.setColor(Color.BLACK);
                    g.drawLine(x + nodeWidth/2, y + nodeHeight/2, x+offset +
                            nodeWidth/2, y + heightSpacing + nodeHeight/2);
                    drawNode(node.right, x, x+offset, y + heightSpacing, level);
                }
            }

            TreeViewNode nodeButton = new TreeViewNode(node, x, y);
            add(nodeButton);
        }

        class TreeViewNode extends JButton implements MouseListener, MouseWheelListener
        {
            AVLTreeNode<PhoneBookEntry> node;

            TreeViewNode(AVLTreeNode<PhoneBookEntry> node, int x, int y)
            {
                this.node = node;
                addMouseListener(this);
                addMouseWheelListener(this);
                setMargin(new Insets(1,1,1,1));
                setBounds(x, y, nodeWidth, nodeHeight);
                setText(node.value.name + " - " + node.height);
            }

            /**
             * {@inheritDoc}
             * @see MouseListener#mouseClicked(MouseEvent)
             */
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    log.append(node.toString());
                }
                else if (e.getButton() == MouseEvent.BUTTON3
                        || e.getButton() == MouseEvent.BUTTON2)
                {
                    log.append("[remove] " + node.toString());
                    phoneBook.removeByName(node.key);
                    display.repaint();
                }
            }

            /**
             * {@inheritDoc}
             * @see MouseListener#mousePressed(MouseEvent)
             */
            public void mousePressed(MouseEvent e)
            {
            }

            /**
             * {@inheritDoc}
             * @see MouseListener#mouseReleased(MouseEvent)
             */
            public void mouseReleased(MouseEvent e)
            {
            }

            /**
             * {@inheritDoc}
             * @see MouseListener#mouseEntered(MouseEvent)
             */
            public void mouseEntered(MouseEvent e)
            {
            }

            /**
             * {@inheritDoc}
             * @see MouseListener#mouseExited(MouseEvent)
             */
            public void mouseExited(MouseEvent e)
            {
            }

            /**
             * {@inheritDoc}
             * @see MouseWheelListener#mouseWheelMoved(MouseWheelEvent)
             */
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                if (e.getWheelRotation() == 1)
                {
                    phoneBook.byName.rotateLeft(node);
                    display.repaint();
                }
                else if (e.getWheelRotation() == -1)
                {
                    phoneBook.byName.rotateRight(node);
                    display.repaint();
                }
            }
        }
    }

    protected class StatisticsView extends JPanel
    {
        int X = 0;
        int Y = 1;

        public SpeedTest[] tests;
        // limits of plotting area
        protected int width, height;
        protected int marginLeft = 50;
        protected int marginBottom = 50;
        protected int marginRight = 50;
        protected int marginTop = 50;
        protected int dotSize = 7;
        protected int textSize = 10;
        protected Graphics g;

        public StatisticsView()
        {
            getContentPane().setBackground(Color.WHITE);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            this.g = g;
            width = getWidth();
            height = getHeight();
            dotSize = (width+height)/180;
            System.out.println("paintComponent():");
            drawSpeedTests();
        }

        void drawSpeedTests()
        {
            double[][] values;

            // time over size
            values = new double[tests.length][2];
            for (int i = 0; i < tests.length; i++)
            {
                values[i][X] = tests[i].size;
                values[i][Y] = tests[i].time;
            }
            plot(1, "Total time (ms)", "Size", values, new Color(25, 25, 255),
                    new Color(20, 20, 100), new Color(100,100,255), new
                    Color(220,220,255));

            // avarage time over size
            values = new double[tests.length][2];
            for (int i = 0; i < tests.length; i++)
            {
                values[i][X] = tests[i].size;
                values[i][Y] = tests[i].avarageTime;
            }
            plot(2, "Time per post (ms)", "Size", values, new Color(255, 25,
                        25), new Color(100, 20, 20), new Color(255,100,100),
                    new Color(255,220,220));
        }

        public void plot(int id, String xLabel, String yLabel, double[][]
                values, Color lineColor, Color dotColor, Color valueColor,
                Color labelColor)
        {
            // TODO : Can this be prettier?
            long[][] positions = new long[values.length][values[0].length];
            double[] min = new double[]{Long.MAX_VALUE, Long.MAX_VALUE};
            double[] max = new double[]{0, 0};
            for (int i = 0; i < values.length; i++)
            {
                if (values[i][X] > max[X])
                    max[X] = values[i][X];
                if (values[i][Y] > max[Y])
                    max[Y] = values[i][Y];
                if (values[i][X] < min[X])
                    min[X] = values[i][X];
                if (values[i][Y] < min[Y])
                    min[Y] = values[i][Y];
            }
            System.out.println("plot(): maxX=" + max[X] + ", minX=" + min[X] +
                    ", maxY=" + max[Y] + ", minY=" + min[Y]);
            System.out.println("plot(): plot(xLabel='" + xLabel + "', yLabel='"
                    + yLabel + "',length='" + values.length + "'):");
            plot(id, xLabel, yLabel, min, max, values, positions, lineColor,
                    dotColor, valueColor, labelColor);
        }

        public void plot(int id, String xLabel, String yLabel, double[] min,
                double[] max, double[][] values, long[][] positions, Color
                lineColor, Color dotColor, Color valueColor, Color labelColor)
        {
            FontMetrics fontMetrics;
            // find min and max values

            // axis labels
            g.setFont(getFont().deriveFont((float)((width+height)/20)));
            fontMetrics = g.getFontMetrics();
            g.setColor(labelColor);
            System.out.println(fontMetrics.stringWidth(xLabel));
            g.drawString(xLabel, 3, height/2-fontMetrics.getHeight()/2 +
                    id*fontMetrics.getHeight());
            g.drawString(yLabel, (width/2-fontMetrics.stringWidth(yLabel)/2) +
                    id*fontMetrics.stringWidth(yLabel), (int)(height*0.95));

            // border
            g.setColor(labelColor);
            g.drawLine(marginLeft-1, marginTop-1, width-marginRight+1, marginTop-1);
            g.drawLine(marginLeft-1, marginTop-1, marginLeft, height-marginBottom);
            g.drawLine(width-marginRight+1, marginTop-1, width-marginRight+1, height-marginBottom+1);
            g.drawLine(marginLeft-1, height-marginBottom+1, width-marginRight+1, height-marginBottom+1);

            // plotting
            g.setFont(getFont().deriveFont((float)((width+height)/100)));
            fontMetrics = g.getFontMetrics();
            int lastX = -1, lastY = -1;
            int x,y;
            for (int i = 0; i < values.length; i++)
            {
                x = marginLeft + ((int)(((double)(values[i][X] - min[X]) /
                                (double)(max[X] - min[X])) * (width - dotSize -
                                marginLeft - marginRight)));
                y = height - (marginTop + (int)((double)(values[i][Y] - min[Y])
                            / (double)(max[Y] - min[Y]) * (height - dotSize -
                                marginTop - marginBottom)));
                // label
                String label = values[i][X] + "," + values[i][Y];
                g.setColor(valueColor);
                g.drawString(label, x-fontMetrics.stringWidth(label)/2,
                        y-dotSize/2);
                // dot
                g.setColor(dotColor);
                g.fillOval(x, y, dotSize, dotSize);
                // line
                g.setColor(lineColor);
                if (lastX > -1)
                    g.drawLine(lastX+dotSize/2, lastY+dotSize/2, x+dotSize/2,
                            y+dotSize/2);
                lastX = x;
                lastY = y;
            }
        }
    }
}
