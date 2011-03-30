package phonebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
    PhoneBook phoneBook;

    JPanel control;
    JPanel buttons;
    JPanel fields;
    JTextField name;
    JTextField number;
    JPanel entry;

    JPanel display;
    JScrollPane pane;
    JTextArea list;
    TreeView treeView;
    int view;
    int VIEW_LIST = 0;
    int VIEW_TREE = 1;

    Log log;

    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuView;

    public GUI(PhoneBook phoneBook)
    {
        this.phoneBook = phoneBook;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50, 50, 800, 600);
        setLayout(new BorderLayout());

        // *entry* - the input fields and their actions
        entry = new JPanel(new GridLayout(1,4));

        // add
        entry.add(new AddEntry());

        // name
        name = new NameField();
        entry.add(name);

        // number
        number = new NumberField();
        entry.add(number);

        // find
        entry.add(new FindButton());

        entry.revalidate();
        add(entry, BorderLayout.NORTH);

        // *display*
        display = new JPanel(new GridLayout(1,1));

        // list
        list = new JTextArea();
        pane = new JScrollPane(list);
        treeView = new TreeView();
        display.add(pane);

        add(display, BorderLayout.CENTER);
        display.revalidate();

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

        // view menu
        menuView = new JMenu("View");
        menuView.setMnemonic('V');
        menuBar.add(menuView);
        menuView.add(new LogOption());
        menuView.addSeparator();
        menuView.add(new ResultOption());
        menuView.add(new TreeOption());
        menuView.addSeparator();
        menuView.add(new AllByNameOption());
        menuView.add(new AllByNumberOption());

        setJMenuBar(menuBar);
        repaint();
        menuBar.revalidate();
    }

    void setView(int view)
    {
        this.view = view;
        display.removeAll();
        if (view == VIEW_TREE)
            display.add(treeView);
        else if (view == VIEW_LIST)
            display.add(list);
        display.revalidate();
        display.repaint();
    }

    class ResultOption extends JMenuItem implements ActionListener
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
            setView(VIEW_LIST);
        }
    }

    class TreeOption extends JMenuItem implements ActionListener
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

    class AllByNameOption extends JMenuItem implements ActionListener
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

    class AllByNumberOption extends JMenuItem implements ActionListener
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

    class FindButton extends JButton implements ActionListener
    {
        public FindButton()
        {
            setText("Find");
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("FindButton.actionPerformed():");
            list.setText("");

            list.append("    * By Name *\n");
            String name = phoneBook.gui.name.getText();
            PhoneBookEntry nameEntry = phoneBook.byName.find(name);
            if (nameEntry != null)
                list.append(nameEntry.toString()+"\n");

            list.append("\n\n    * By Number *\n");
            String number = phoneBook.gui.number.getText();
            PhoneBookEntry numberEntry = phoneBook.byNumber.find(number);
            if (numberEntry != null)
                list.append(numberEntry.toString()+"\n");

            setView(VIEW_LIST);
        }
    }

    class NameField extends JTextField implements ActionListener
    {
        public NameField()
        {
            setText("<name>");
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("NameField.actionPerformed():");
            list.setText("");

            String name = phoneBook.gui.name.getText();
            list.append("    * By Name * (" + name + ")\n");
            PhoneBookEntry nameEntry = phoneBook.byName.find(name);
            if (nameEntry != null)
                list.append(nameEntry.toString()+"\n");

            setView(VIEW_LIST);
        }
    }

    class NumberField extends JTextField implements ActionListener
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
            System.out.println("NumberField.actionPerformed():");
            list.setText("");

            String number = phoneBook.gui.number.getText();
            list.append("    * By Number * (" + number + ")\n");
            PhoneBookEntry numberEntry = phoneBook.byNumber.find(number);
            if (numberEntry != null)
                list.append(numberEntry.toString()+"\n");

            setView(VIEW_LIST);
        }
    }

    class AddEntry extends JButton implements ActionListener
    {
        public AddEntry()
        {
            setText("Add");
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            String name = GUI.this.name.getText();
            String number = GUI.this.number.getText();
            System.out.println(name);
            System.out.println(number);
            phoneBook.add(new PhoneBookEntry(name, number));
            if (phoneBook == null)
                System.out.println("jaa det e null");
            phoneBook.showAll();

            display.repaint();
        }
    }

    class SaveOption extends JMenuItem implements ActionListener
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
            System.out.println("actionPerformed():");
            PhoneBook.saveToDisk("blah.txt", phoneBook.byName);
        }
    }

    class LoadOption extends JMenuItem implements ActionListener
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

    class LogOption extends JMenuItem implements ActionListener
    {
        public LogOption()
        {
            setText("Log");
            setMnemonic('L');
            setEnabled(true);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            System.out.println("logger");
            if (log.isVisible() == true)
                log.setVisible(false);
            else
                log.setVisible(true);
        }
    }

    class TreeView extends JPanel
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

        //TreeViewNode root;

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            this.g = g;

            width = getWidth();
            height = getHeight();

            startX = getWidth()/2 - nodeWidth/2;
            //drawNode(phoneBook.byName.root, startX, 10, firstLevel);
            removeAll();
            if (phoneBook.byName.root != null)
                drawNode(phoneBook.byName.root, 0, startX, 10, firstLevel);
        }

        //public void drawNode(AVLTreeNode<PhoneBookEntry> node, int x, int y, int level)
        public void drawNode(AVLTreeNode<PhoneBookEntry> node, int parentX, int x, int y, int level)
        {
            //(maxLevel - level + 1) * (widthSpacing-1) - (widthSpacing/2)

            if (level < maxLevel)
            {
                level++;
                int offset = Math.abs(x - (parentX + x)/2);
                System.out.println(node.value.name + " offset = '" + offset + "'");

                if (node.left != null)
                {
                    //g.drawLine(x + nodeSize/2, y + nodeSize/2, x - widthSpacing + nodeSize/2, y + heightSpacing + nodeSize/2);
                    g.setColor(Color.BLACK);
                    g.drawLine(x + nodeWidth/2, y + nodeHeight/2, x-offset + nodeWidth/2, y + heightSpacing + nodeHeight/2);
                    //drawNode(node.left, x - widthSpacing, y + heightSpacing, level);
                    drawNode(node.left, x, x-offset, y + heightSpacing, level);
                }

                if (node.right != null)
                {
                    //g.drawLine(x + nodeSize/2, y + nodeSize/2, x + widthSpacing + nodeSize/2, y + heightSpacing + nodeSize/2);
                    g.setColor(Color.BLACK);
                    g.drawLine(x + nodeWidth/2, y + nodeHeight/2, x+offset + nodeWidth/2, y + heightSpacing + nodeHeight/2);
                    //drawNode(node.right, x + widthSpacing, y + heightSpacing, level);
                    drawNode(node.right, x, x+offset, y + heightSpacing, level);
                }
            }

            //g.setColor(Color.RED);
            //g.fillOval(x, y, nodeSize, nodeSize);
            TreeViewNode nodeButton = new TreeViewNode(node, x, y);
            add(nodeButton);
            //g.drawString(node.value.name + " " + node.height, x, y);

        }

        class TreeViewNode extends JButton implements MouseListener, MouseWheelListener
        {
            AVLTreeNode<PhoneBookEntry> node;

            TreeViewNode(AVLTreeNode<PhoneBookEntry> node, int x, int y)
            {
                this.node = node;
                addMouseListener(this);
                addMouseWheelListener(this);
                //setLocation(x, y);
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
                    //log.setCaretPosition(log.getText().length());
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
                //System.out.println("MouseWheelEvent = " + e);
                if (e.getWheelRotation() == 1)
                {
                    System.out.println("down");
                    phoneBook.byName.rotateLeft(node);
                    display.repaint();
                }
                else if (e.getWheelRotation() == -1)
                {
                    System.out.println("up");
                    phoneBook.byName.rotateRight(node);
                    display.repaint();
                }
            }
        }
    }

    class Log extends JPanel
    {
        JScrollPane logPane;
        JTextArea log;

        Log()
        {
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
}
