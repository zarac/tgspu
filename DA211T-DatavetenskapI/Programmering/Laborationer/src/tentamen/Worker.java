package tentamen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Worker extends Person
{
    private int wage;
    private JLabel label = new JLabel();

    private String defaultText;
    private JLabel lblRubrik;

    public Worker()
    {
    }

    public Worker(String name, String socSecNbr, double length, int wage)
    {
        super(name, socSecNbr, length);

        this.wage = wage;

        defaultText = "Jag Visar Text";
        lblRubrik = new JLabel(defaultText);
        
        //lblRubrik.addActionListener(new JLabelLyssnare());
    }

    /**
     * Gets the wage for this instance.
     *
     * @return The wage.
     */
    public int getWage()
    {
        return this.wage;
    }

    /**
     * Sets the wage for this instance.
     *
     * @param wage The wage.
     */
    public void setWage(int wage)
    {
        this.wage = wage;
    }

    public String toString()
    {
        return super.toString() + wage;
    }

    public static void main(String[] argv)
    {
        Worker w1 = new Worker(); 
        Worker w2 = new Worker("Sven Alberg", "690816-1234", 182.5, 22750); 
        System.out.println("-----------------------------"); 
        System.out.println(w1.toString()); 
        System.out.println("Namn = " + w1.getName()); 
        System.out.println("Längd = " + w1.getLength()); 
        System.out.println("Ålder = " + w1.getAge(2010)); 
        //System.out.println("Kön = " + w1.getSex()); 
        System.out.println("Lön = " + w1.getWage()); 
        System.out.println("-----------------------------"); 
        System.out.println(w2.toString()); 
        System.out.println("Namn = " + w2.getName()); 
        System.out.println("Längd = " + w2.getLength()); 
        System.out.println("Ålder = " + w2.getAge(2010)); 
        //System.out.println("Kön = " + w2.getSex()); 
        System.out.println("Lön = " + w2.getWage()); 
    }

    public class JLabelLyssnare implements ActionListener
    {

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            lblRubrik.setText(defaultText);
        }
    }
}
