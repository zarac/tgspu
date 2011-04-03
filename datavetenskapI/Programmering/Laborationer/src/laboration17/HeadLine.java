package laboration17;
import javax.swing.*;
import java.awt.*;

public class HeadLine extends JLabel {
    public HeadLine(String content, int style, int fontsize, Color text,
            Color background) {
        Font font = new Font("Dialog", style, fontsize);
        setText(content);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(font);
        setBackground(background);
        setOpaque(true);
        setForeground(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                HeadLine rubrik1 = new HeadLine("Rött och svart", Font.BOLD,
                    24, Color.red, Color.black);
                HeadLine rubrik2 = new HeadLine("Blå text - gul bakgrund",
                    Font.PLAIN, 16, Color.blue,
                    Color.yellow);
                HeadLine rubrik3 = new HeadLine("Denna rubrik är i SOUTH",
                    Font.ITALIC + Font.BOLD, 12,
                    Color.white, Color.black);
                frame.setSize(500,150);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(rubrik1, BorderLayout.NORTH);
                frame.add(rubrik2, BorderLayout.CENTER);
                frame.add(rubrik3, BorderLayout.SOUTH);
                frame.setVisible(true);
            }
        });
    }
}
