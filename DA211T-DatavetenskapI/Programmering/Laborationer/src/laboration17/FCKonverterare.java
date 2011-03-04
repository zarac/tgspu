package laboration17;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FCKonverterare extends JFrame implements ActionListener {
    //private JFrame frame = new JFrame();
    private JButton fToC = new JButton("Fahrenheit till Celsius");
    private JButton cToF = new JButton("Celsius till Fahrenheit");
    private JButton exit = new JButton("Avsluta");
    private JLabel label = new JLabel("Grader ");
    private JLabel result = new JLabel("Resultat: ");
    private JTextField degrees = new JTextField();

    public FCKonverterare() {
        this.setSize(350,130);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout( new GridLayout( 4, 1 ) );
        JPanel firstRow = new JPanel( new BorderLayout() );
        JPanel thirdRow = new JPanel( new GridLayout( 1, 2 ) );
        cToF.addActionListener(this);
        fToC.addActionListener(this);
        exit.addActionListener(this);
        firstRow.add( label, BorderLayout.WEST );
        firstRow.add( degrees, BorderLayout.CENTER );
        thirdRow.add( fToC );
        thirdRow.add( cToF );
        this.add( firstRow );
        this.add( result );
        this.add( thirdRow );
        this.add( exit );
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        double deg, res;
        if(e.getSource()==cToF) {
            deg = Double.parseDouble(degrees.getText());
            res = 32+1.8*deg;
            result.setText("Resultat: "+deg+" C är "+res+" F");
        }
        else if(e.getSource()==fToC) {
            deg = Double.parseDouble(degrees.getText());
            res = (deg-32)/1.8;
            result.setText("Resultat: "+deg+" F är "+res+" C");
        }
        else if(e.getSource()==exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        FCKonverterare fck = new FCKonverterare();
    }
}
