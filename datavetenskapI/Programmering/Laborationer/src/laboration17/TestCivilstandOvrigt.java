package laboration17;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestCivilstandOvrigt implements ActionListener {
    private JFrame frame = new JFrame();
    private JButton update = new JButton("Uppdatera textytan");
    private JTextArea textyta = new JTextArea();
    private CivilstandOvrigt co = new CivilstandOvrigt();

    /** Creates a new instance of TestCivilstandOvrigt */
    public TestCivilstandOvrigt() {
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textyta.setEditable(false);
        update.addActionListener(this);

        frame.add(co, BorderLayout.NORTH);
        frame.add(textyta, BorderLayout.CENTER);
        frame.add(update, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        textyta.setText("Gift: " + co.getCivilstand() +"\nHobby: " +
                        co.getHobby());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TestCivilstandOvrigt tny = new TestCivilstandOvrigt();
            }
        });
    }
}
