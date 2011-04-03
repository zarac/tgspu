package p4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CountryViewer extends JFrame
{
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel title;

    public CountryViewer()
    {
        // Title
        // Set up this window / frame
        this.setBounds(30, 30, 330, 700);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // The title
        title = new JLabel("Länder och deras befolkning.", JLabel.CENTER);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        this.add(title, BorderLayout.NORTH);

        // The text for countries
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void showCountries(Country[] p_countries)
    {
        // Clear text first so we can append.
        textArea.setText("");
        for (Country country : p_countries)
            textArea.append(country.toString() + "\n");
    }
}
