package phonebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.text.Position;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
    PhoneBook phoneBook;

    JPanel control;
    JPanel display;
    JPanel entry;

    JScrollPane pane;
    JTextArea list;
    JTextField name;
    JTextField number;
    TreeView treeView;

    public GUI(PhoneBook phoneBook)
    {
        //GUI.phoneBook = phoneBook;
        this.phoneBook = phoneBook;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50, 50, 500, 400);
        setLayout(new BorderLayout());

        // *control*
        control = new JPanel(new GridLayout(1,4));

        // load
        LoadButton loadButton = new LoadButton();
        control.add(loadButton);

        // save
        SaveButton saveButton = new SaveButton();
        control.add(saveButton);

        // all by name
        control.add(new AllByName());

        // all by number
        control.add(new AllByNumber());

        // tree view
        control.add(new ShowTree());

        add(control, BorderLayout.SOUTH);
        control.revalidate();

        // *display*
        display = new JPanel(new GridLayout(1,1));

        // list
        list = new JTextArea();
        pane = new JScrollPane(list);
        treeView = new TreeView();
        display.add(pane);

        add(display, BorderLayout.CENTER);
        display.revalidate();

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

        add(entry, BorderLayout.NORTH);
        entry.revalidate();
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
            display.removeAll();

            if (mode == TREE)
            {
                display.add(pane);
                setText("Show Tree");
                mode = LIST;
            }
            else
            {
                display.add(treeView);
                setText("Show List");
                mode = TREE;
            }

            display.repaint();
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
            setText("Save from disk");
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
        }
    }

    class TreeView extends JPanel
    {
        int width, height;
        int firstLevel = 1;
        int maxLevel = 9;
        int nodeSize = 30;
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

            startX = getWidth()/2 - nodeSize/2;
            //drawNode(phoneBook.byName.root, startX, 10, firstLevel);
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
                    g.drawLine(x + nodeSize/2, y + nodeSize/2, x-offset + nodeSize/2, y + heightSpacing + nodeSize/2);
                    //drawNode(node.left, x - widthSpacing, y + heightSpacing, level);
                    drawNode(node.left, x, x-offset, y + heightSpacing, level);
                }

                if (node.right != null)
                {
                    //g.drawLine(x + nodeSize/2, y + nodeSize/2, x + widthSpacing + nodeSize/2, y + heightSpacing + nodeSize/2);
                    g.setColor(Color.BLACK);
                    g.drawLine(x + nodeSize/2, y + nodeSize/2, x+offset + nodeSize/2, y + heightSpacing + nodeSize/2);
                    //drawNode(node.right, x + widthSpacing, y + heightSpacing, level);
                    drawNode(node.right, x, x+offset, y + heightSpacing, level);
                }
            }

            g.setColor(Color.RED);
            g.fillOval(x, y, nodeSize, nodeSize);
            g.drawString(node.value.name, x, y);
        }
    }
}
