package laboration17;
import javax.swing.*;
import java.awt.*;

public class Personuppgifter extends JPanel
{
    private HeadLine rubrik = new HeadLine("Personuppgifter", Font.BOLD, 24, Color.RED, Color.BLACK);
    //private HeadLine rubrik = new HeadLine();
    private NamnYrke ny = new NamnYrke();
    private CivilstandOvrigt co = new CivilstandOvrigt();

    public Personuppgifter() {
        setLayout(new BorderLayout());
        add(rubrik, BorderLayout.NORTH);
        add(ny, BorderLayout.CENTER);
        add(co, BorderLayout.SOUTH);
    }

    public String getNamn() {
        return ny.getNamn();
    }

    public String getYrke() {
        return ny.getYrke();
    }

    public boolean getCivilstand() {
        return co.getCivilstand();
    }

    public String getHobby() {
        return co.getHobby();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                Personuppgifter personuppgifter = new Personuppgifter();
                frame.setSize(300,180);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(personuppgifter, BorderLayout.CENTER);
                frame.setVisible(true);
            }
        });
    }
}
