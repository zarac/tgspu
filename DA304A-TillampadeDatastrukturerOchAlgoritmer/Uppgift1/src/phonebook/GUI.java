package phonebook;

import java.awt.BorderLayout;
import java.awt.Color;

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

    JPanel logPanel;
    JFrame logFrame;
    JScrollPane logPane;
    JTextArea log;

    public GUI(PhoneBook phoneBook)
    {
        //GUI.phoneBook = phoneBook;
        this.phoneBook = phoneBook;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50, 50, 640, 480);
        setLayout(new BorderLayout());

        // *control*
        control = new JPanel(new GridLayout(2,1));

        // *buttons*
        buttons = new JPanel(new GridLayout(1,4));

        // load
        LoadButton loadButton = new LoadButton();
        buttons.add(loadButton);

        // save
        SaveButton saveButton = new SaveButton();
        buttons.add(saveButton);

        // all by name
        buttons.add(new AllByName());

        // all by number
        buttons.add(new AllByNumber());

        // tree view
        buttons.add(new ShowTree());

        control.add(buttons);
        buttons.revalidate();

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

        control.add(entry);
        entry.revalidate();

        //control.revalidate();
        add(control, BorderLayout.NORTH);

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
        log = new JTextArea();
        logPanel = new JPanel();
        logFrame = new JFrame();
        log.append("TehE LOOOG\n\ntest\n\n");
        logPane = new JScrollPane(log);
        logPane.setAutoscrolls(true);

        add(logPane, BorderLayout.SOUTH);
        //logPane.revalidate();
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

    class ShowTree extends JButton implements ActionListener
    {
        int TREE = 0;
        int LIST = 1;
        int mode = LIST;

        public ShowTree()
        {
            setText("Show Tree");
            addActionListener(this);
        }

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            //display.removeAll();

            //if (mode == TREE)
            if (view == VIEW_LIST)
            {
                setView(VIEW_TREE);
                //display.add(pane);
                setText("Show Tree");
                //mode = LIST;
            }
            else
            {
                //display.add(treeView);
                setView(VIEW_LIST);
                setText("Show List");
                //mode = TREE;
            }

            //display.repaint();
        }
    }

    class AllByName extends JButton implements ActionListener
    {
        public AllByName()
        {
            setText("All By Name");
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
            //display.removeAll();
            //display.add(pane);
            //display.revalidate();
        }
    }

    class AllByNumber extends JButton implements ActionListener
    {
        public AllByNumber()
        {
            setText("All By Number");
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
            //display.removeAll();
            //display.add(pane);
            //display.revalidate();
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

            display.revalidate();
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

            display.revalidate();
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

            display.revalidate();
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

    class SaveButton extends JButton implements ActionListener
    {
        public SaveButton()
        {
            setText("Save to disk");
            System.out.println("SaveButton():");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            System.out.println("actionPerformed():");
            PhoneBook.saveToDisk("blah.txt", phoneBook.byName);
        }
    }

    class LoadButton extends JButton implements ActionListener
    {
        public LoadButton()
        {
            setText("Load from disk");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            phoneBook.loadFromDisk("blah.txt");
            phoneBook.showAll();
            display.repaint();
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
                //System.out.println("MouseEvent: " + e);
                //if (e.getButton() == MouseEvent.BUTTON1)
                //{
                    //System.out.println("left button");
                    //phoneBook.byName.rotateLeft(node);
                    //display.repaint();
                //}
                //else if (e.getButton() == MouseEvent.BUTTON3)
                //{
                    //System.out.println("right button");
                    //phoneBook.byName.rotateRight(node);
                    //display.repaint();
                //}
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    log.append(node.toString() + "\n");
                    log.setCaretPosition(log.getText().length());
                }
                else if (e.getButton() == MouseEvent.BUTTON3
                        || e.getButton() == MouseEvent.BUTTON2)
                {
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
}
