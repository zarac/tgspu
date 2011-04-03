package laboration17;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class CivilstandOvrigt extends JPanel {
    private JRadioButton rbOgift = new JRadioButton("ogift");
    private JRadioButton rbGift = new JRadioButton("gift");
    private JComboBox cbHobbies;

    public CivilstandOvrigt() {
        // komplettera med kod
        setLayout(new BorderLayout());
        add(getCivilstandPanel(), BorderLayout.WEST);
        add(getOvrigtPanel(), BorderLayout.CENTER);
    }

    public JPanel getCivilstandPanel() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        ButtonGroup grupp = new ButtonGroup();
        rbOgift.setSelected(true);
        grupp.add(rbOgift);
        grupp.add(rbGift);
        panel.setBorder(BorderFactory.createTitledBorder("Civilstånd"));
        panel.add(rbOgift);
        panel.add(rbGift);
        return panel;
    }

    public JPanel getOvrigtPanel() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel lblHobby = new JLabel("Hobby:");
        String[] hobbies = {"familjen", "idrott", "vävning", "programmering",
            "huset", "frimärken"};
        lblHobby.setFont(new Font("Dialog", Font.BOLD, 12));
        cbHobbies = new JComboBox(hobbies);
        try {
            cbHobbies.setSelectedIndex(0);
        } catch (IllegalArgumentException e) { }
        panel.setBorder(BorderFactory.createTitledBorder("Övrigt"));
        panel.add(lblHobby);
        panel.add(cbHobbies);
        return panel;
    }

    public String getHobby()
    {
        return (String)cbHobbies.getSelectedItem();
    }

    public boolean getCivilstand()
    {
        return rbGift.isSelected();
        //return "<civilstand>";
    }

    // komplettera med nödvändiga get-metoder för att avläsa värden i radiobuttons (isSelected())
    // och comboboxen (getSelectedItem()). Studera testprogrammet!
}
