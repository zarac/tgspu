package blackJack;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
class Card extends JPanel
{
    private Logger logger = new Logger(this);

    // H - hearts
    // S - spades
    // D - diamonds
    // C - clubs
    private char suite;

    // 2,3,4,5,6,7,8,9,10,J,Q,K,A
    private String face;

    // 1 through 11, where J,Q and K are 10 and A is 11
    private int value;

    private JLabel label;

    public Card(char p_suite, String p_face, int p_value)
    {
        logger.log(p_value + "" + p_suite);
        suite = p_suite;
        face = p_face;
        value = p_value;

        setBackground(new Color(255, 255, 255));

        setLayout(new FlowLayout());

        label = new JLabel();
        add(label);

        setHidden(false);
    }

    /**
     * Gets the face for this instance.
     *
     * @return The face.
     */
    public String getFace()
    {
        return this.face;
    }

    /**
     * Sets the value for this instance.
     *
     * @param value The value.
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Value of card in a Black Jack game. Suits should be 10 and ace 11.
     *
     * @return Value of card.
     */
    public int getValue()
    {
        //if (value == 14)
            //return 11;
        //else if (value >= 10 && value <= 13)
            //return 10;
        //else
            return value;
    }

    /**
     * Set if hidden or not.
     * 
     * @param isHidden Hidden or not.
     */
    public void setHidden(boolean isHidden)
    {
        if (isHidden)
        {
            label.setText("? ?");
        }
        else
        {
            label.setText(suite + " " + face);
        }
    }

    public String toString()
    {
        return "suite='" + suite + "', face='" + face + "', value='" + value + "'";
        //return suite + "" + value;
    }
}

