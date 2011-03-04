/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package laboration10;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author zarac
 */
public class Kalkylator implements ActionListener
{
    private JFrame frame;

    private Font buttonFont;
    private JButton plus;
    private JButton minus;

    private Font labelFont;

    private double number1;
    private String number1String;
    private JLabel number1Label;
    private JTextField number1Field;

    private double number2;
    private String number2String;
    private JLabel number2Label;
    private JTextField number2Field;

    private double result;
    private String resultText;
    private JLabel resultLabel;


    public Kalkylator()
    {
        // The window
        frame = new JFrame("Kalkylator");
        frame.setBounds(50, 50, 250, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);

        // Font for buttons
        buttonFont = new Font("SansSerif", Font.PLAIN, 24);

        // + (plus) button
        plus = new JButton("+");
        plus.setBounds(10, 10, 50, 50);
        plus.setFont(buttonFont);
        plus.addActionListener(this);
        frame.add(plus);

        // - (minus) button
        minus = new JButton("-");
        minus.setBounds(10, 70, 50, 50);
        minus.setFont(buttonFont);
        minus.addActionListener(this);
        frame.add(minus);

        // first number, label
        number1Label = new JLabel("Tal 1");
        number1Label.setFont(labelFont);
        number1Label.setBounds(70, 10, 50, 20);
        frame.add(number1Label);

        // first number, input field
        number1Field = new JTextField();
        number1Field.setBounds(130, 10, 102, 20);
        frame.add(number1Field);

        // second number, label
        number2Label = new JLabel("Tal 2");
        number2Label.setFont(labelFont);
        number2Label.setBounds(70, 40, 50, 20);
        frame.add(number2Label);

        // second number, input field
        number2Field = new JTextField();
        number2Field.setBounds(130, 40, 102, 20);
        frame.add(number2Field);

        // result, label / answer
        resultLabel = new JLabel("Resultat");
        resultLabel.setFont(labelFont);
        resultLabel.setBounds(70, 70, 120, 20);
        frame.add(resultLabel);
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("actionPerformed");
        number1String = number1Field.getText();
        number2String = number2Field.getText();
        number1 = Double.parseDouble(number1Field.getText());
        number2 = Double.parseDouble(number2Field.getText());

        if (e.getSource() == plus)
        {
            System.out.println("+");
            result = number1 + number2;
            resultText = number1String + " + " + number2String + " = " + result;
            System.out.println(result);
            System.out.println(resultText);
        }
        else if(e.getSource() == minus)
        {
            System.out.println("+");
            result = number1 - number2;
            resultText = number1String + " - " + number2String + " = " + result;
            System.out.println(result);
            System.out.println(resultText);
        }

        resultLabel.setText(resultText);
    }

    public static void main(String[] args)
    {
        Kalkylator kalk = new Kalkylator();
    }
}
