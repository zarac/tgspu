/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PersonSurvey
 * ============
 *  o JFrame frame
 *      * implements ActionListener
 *          ; actionPerformed()
 *              ; sammanfatta()
 *                  ; res =
 *                      ; "Namn: " + tfNamn.getText()
 *                      ; "Ålder: " + rb0_17 / rb18_64 / rb65 .isSelected()
 *                      ; "Hobby: " + ...
 *                  ; taSammanfattning.setText(res)
 *      * 5 panels
 *      o JPanel pnlNorth
 *          < BorderLayout.NORTH
 *              : frame.add(pnlNorth, BorderLayout.NORTH)
 *                  * NORTH, SOUTH, EAST, WEST, CENTER
 *          * BorderLayout
 *          o JPanel pnlNorthNorth
 *              < BorderLayout.NORTH
 *              * BorderLayout
 *              o JLabel nameLabel
 *                  * text = "Namn"
 *                  * WEST
 *              o JTextField nameField
 *                  * CENTER
 *          o JPanel pnlNorthCenter
 *              < BorderLayout.CENTER
 *              * GridLayout(1,2)
 *              o JPanel pnlAlder
 *                  * GridLayout(5,1)
 *                  ; ButtonGroup buttonGroup
 *                  ; setBorder(BorderFactory.createTitledBorder("Ålder"))
 *                  o JRadioButton rb0_17
 *                      * in buttonGroup
 *                  o JRadioButton rb18_64
 *                      * in buttonGroup
 *                  o JRadioButton rb65
 *                      * in buttonGroup
 *                      ; setSelected(true)
 *                  o empty
 *                  o empty
 *              o JPanel pnlHobby
 *                  * GridLayout(5,1)
 *                  ; setBorder(BorderFactory.createTitledBorder("Hobby")
 *                  o JCheckBox cbIdrott("Idrott")
 *                  o JCheckBox cbFolkdans("Folkdans")
 *                  o JCheckBox cbFagelskadning("Fågelsådning")
 *                  o JCheckBox cbBridge("Bridge")
 *                  o JCheckBox cbKorsang("Körsång)
 *          o JButton btnSammanfattning("Sammanfattning")
 *              ; btnSammanfattning.addActionListener(this)
 *              < BorderLayout.SOUTH
 *      o JTextArea taSammanfattning
 *          < BorderLayout.CENTER
 */

package laboration11;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author zarac
 * Person survey, swing practice.
 */
class PersonSurvey implements ActionListener
{
    private JFrame frame;
    private JPanel pnlNorth;
    private JPanel pnlNorthNorth;
    private JPanel pnlNorthCenter;
    private JPanel pnlAlder;
    private JPanel pnlHobby;
    private JButton btnSammanfattning;
    private JTextArea taSammanfattning;
    private JLabel nameLabel;
    private JTextField nameField;
    private ButtonGroup buttonGroupAlder;
    private JRadioButton rb0_17;
    private JRadioButton rb18_64;
    private JRadioButton rb65;
    private JCheckBox cbIdrott;
    private JCheckBox cbFolkdans;
    private JCheckBox cbFagelskadning;
    private JCheckBox cbBridge;
    private JCheckBox cbKorsang;
    private String sammanfattning;

    public PersonSurvey()
    {
        // Main window / container
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(50, 50, 300, 360);
        frame.setTitle("Personundersökning");
        frame.setLayout(new BorderLayout());

        // North
        pnlNorth = new JPanel(new BorderLayout());
        frame.add(pnlNorth, BorderLayout.NORTH);

        // NorthNorth
        pnlNorthNorth = new JPanel(new BorderLayout());
        pnlNorth.add(pnlNorthNorth, BorderLayout.NORTH);

        // Name
        nameLabel = new JLabel("Namn: ");
        pnlNorthNorth.add(nameLabel, BorderLayout.WEST);
        nameField = new JTextField();
        pnlNorthNorth.add(nameField, BorderLayout.CENTER);

        // NorthCenter
        pnlNorthCenter = new JPanel(new GridLayout(1,2));
        pnlNorth.add(pnlNorthCenter, BorderLayout.CENTER);

        // NorthCenter > pnlAlder
        pnlAlder = new JPanel(new GridLayout(5,1));
        pnlAlder.setBorder(BorderFactory.createTitledBorder("Ålder"));

        buttonGroupAlder = new ButtonGroup();

        rb0_17 = new JRadioButton(" 0 - 17");
        buttonGroupAlder.add(rb0_17);
        pnlAlder.add(rb0_17);

        rb18_64 = new JRadioButton("18 - 64");
        buttonGroupAlder.add(rb18_64);
        pnlAlder.add(rb18_64);

        rb65 = new JRadioButton("65 + ");
        rb65.setSelected(true);
        buttonGroupAlder.add(rb65);
        pnlAlder.add(rb65);

        pnlNorthCenter.add(pnlAlder);

        // NorthCenter > pnlHobby
        pnlHobby = new JPanel(new GridLayout(5,1));
        pnlHobby.setBorder(BorderFactory.createTitledBorder("Hobby"));

        cbIdrott = new JCheckBox("Idrott");
        pnlHobby.add(cbIdrott);

        cbFolkdans = new JCheckBox("Folkdans");
        pnlHobby.add(cbFolkdans);

        cbFagelskadning = new JCheckBox("Fågelskådning");
        pnlHobby.add(cbFagelskadning);

        cbBridge = new JCheckBox("Bridge");
        pnlHobby.add(cbBridge);

        cbKorsang = new JCheckBox("Körsång");
        pnlHobby.add(cbKorsang);

        pnlNorthCenter.add(pnlHobby);

        // Result Button
        btnSammanfattning = new JButton("Sammanfattning");
        btnSammanfattning.addActionListener(this);
        pnlNorth.add(btnSammanfattning, BorderLayout.SOUTH);

        // Result
        taSammanfattning = new JTextArea();
        frame.add(taSammanfattning, BorderLayout.CENTER);
    }

    private void sammanfatta()
    {
        // Namn
        sammanfattning = "Namn: " + nameField.getText();
        
        // Ålder
        sammanfattning += "\nÅlder: ";
        if (rb0_17.isSelected())
        {
            sammanfattning += "0 - 17";
        }
        else if (rb18_64.isSelected())
        {
            sammanfattning += "18 - 64";
        }
        else if (rb65.isSelected())
        {
            sammanfattning += "65 +";
        }

        // Hobby
        sammanfattning += "\nHobby:";
        if (cbIdrott.isSelected())
        {
            sammanfattning += " Idrott";
        }
        if (cbFolkdans.isSelected())
        {
            sammanfattning += " Folkdans";
        }
        if (cbFagelskadning.isSelected())
        {
            sammanfattning += " Fågelskådning";
        }
        if (cbBridge.isSelected())
        {
            sammanfattning += " Bridge";
        }
        if (cbKorsang.isSelected())
        {
            sammanfattning += " Körsång";
        }

        taSammanfattning.setText(sammanfattning);
    }

    public void actionPerformed(ActionEvent e)
    {
        this.sammanfatta();
    }

    public static void main(String[] args)
    {
        // ToDo: What does SwingUtilities.invokeLater(Runnable) do?
        //PersonSurvey personSurvey = new PersonSurvey();
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    new PersonSurvey();
                }
            }
        );
    }
}
