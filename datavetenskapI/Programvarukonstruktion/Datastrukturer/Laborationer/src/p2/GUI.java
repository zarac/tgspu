package p2;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class GUI extends JFrame
{
    protected Capital capital;

    protected Search search;
    protected Answer answer;

    public GUI(Capital capital)
    {
        this.capital = capital;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(100, 100, 400, 200);
        setVisible(true);
        
        // search
        search = new Search();
        add(search, BorderLayout.NORTH);
        
        // answer
        answer = new Answer();
        add(answer, BorderLayout.CENTER);
    }

    protected class Search extends JTextField implements KeyListener
    {
        protected Search()
        {
            addKeyListener(this);
            setBackground(new Color(255,230,230));
            revalidate();
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyTyped(KeyEvent)
         */
        public void keyTyped(KeyEvent e)
        {
            System.out.println("keyTyped():");
        }

        /**
         * {@inheritDoc}
         * addKeyListener(this);
         * @see KeyListener#keyPressed(KeyEvent)
         */
        public void keyPressed(KeyEvent e)
        {
            System.out.println("keyPressed():");
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyReleased(KeyEvent)
         */
        public void keyReleased(KeyEvent e)
        {
            System.out.println("keyReleased():");
            String key = search.getText();

            if (key.equals("listall"))
            {
                // TODO : ? implement
                return;
            }

            answer.setText("Searching for \"" + key + "\"...");

            Country byName = capital.findByCountry(key);
            if (byName != null)
                answer.append("\n\nBy name\n - " + byName.toString());
            else
                answer.append("\n\nBy name\n - nothing found.");

            Country byCapital = capital.findByCapital(key);
            if (byCapital != null)
                answer.append("\n\nBy capital\n - " + byCapital.toString());
            else
                answer.append("\n\nBy capital\n - nothing found.");
        }
    }

    protected class Answer extends JTextArea
    {
        protected Answer()
        {
            setText("Type something in the box above.\n\n e.g. Sweden\n.e.g. Stockholm");
            setBackground(new Color(230,255,230));
            revalidate();
        }
    }
}
