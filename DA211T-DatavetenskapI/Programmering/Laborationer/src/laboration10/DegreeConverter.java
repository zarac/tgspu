/*
 * ToDo
 *  actionPerformed seems to only react to return... perhaps use KeyListener instead ?
 */

package laboration10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author zarac
 * A GUI Celsius/Fahrenheit converter.
 */
class DegreeConverter implements ActionListener
{
    private JFrame frame;
    private JLabel instructions;
    private JTextField celsius;
    private JLabel celsiusLabel;
    private JTextField fahrenheit;
    private JLabel fahrenheitLabel;
    private JButton closeButton;


    public DegreeConverter()
    {
        frame = new JFrame("Degree Converter");
        frame.setBounds(50, 50, 290, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);

        // Instructions
        instructions = new JLabel("Skriv in önskat tal och tryck sedan på retur.");
        instructions.setBounds(10, 10, 260, 20);
        frame.add(instructions);

        // Celsius, label
        celsiusLabel = new JLabel("Celsius");
        celsiusLabel.setBounds(10, 40, 100, 20);
        frame.add(celsiusLabel);
        // Celsius, input
        celsius = new JTextField();
        celsius.addActionListener(this);
        celsius.setBounds(120, 40, 150, 20);
        frame.add(celsius);

        // Fahrenheit, label
        fahrenheitLabel = new JLabel("Fahrenheit");
        fahrenheitLabel.setBounds(10, 70, 100, 20);
        frame.add(fahrenheitLabel);
        // Fahrenheit, input
        fahrenheit = new JTextField();
        fahrenheit.addActionListener(this);
        fahrenheit.setBounds(120, 70, 150, 20);
        frame.add(fahrenheit);

        // Close button
        closeButton = new JButton("Avsluta");
        closeButton.setBounds(10, 100, 100, 20);
        closeButton.addActionListener(this);
        frame.add(closeButton);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == celsius)
        {
            fahrenheit.setText(Double.toString(Double.parseDouble(celsius.getText()) * 1.8 + 32));
        }
        else if(e.getSource() == fahrenheit)
        {
            celsius.setText(Double.toString((Double.parseDouble(fahrenheit.getText()) - 32) / 1.8));
        }
        else if (e.getSource() == closeButton)
        {
            System.exit(0);
        }
    }


    public static void main(String[] args)
    {
        DegreeConverter degreeConverter = new DegreeConverter();
    }
}
