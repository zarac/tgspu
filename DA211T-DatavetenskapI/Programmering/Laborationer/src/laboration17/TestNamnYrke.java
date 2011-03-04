package laboration17;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestNamnYrke extends JFrame implements ActionListener {
    private JButton update = new JButton("Uppdatera textytan");
    private JTextArea textyta = new JTextArea();
    private NamnYrke namnYrke = new NamnYrke();

    public TestNamnYrke() {
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textyta.setEditable(false);
        update.addActionListener(this);

        this.add(namnYrke, BorderLayout.NORTH);
        this.add(textyta, BorderLayout.CENTER);
        this.add(update, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        textyta.setText("Namn: " + namnYrke.getNamn() +"\nYrke: " +
                        namnYrke.getYrke());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TestNamnYrke tny = new TestNamnYrke();
            }
        });
    }
}

