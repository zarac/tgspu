
package rolfVrNinja;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameBoard extends JPanel implements ActionListener {
    /**
     * Variabler som används till grafiken.
     */
    private JPanel cardsMain;
    private CardLayout cardlayout;
    private JPanel grid = new JPanel(new GridLayout(5, 1));
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel lblMsg = new JLabel(" Först till 3! ");
    private JLabel lblHuman = new JLabel("Rolf ");
    private JLabel lblHval = new JLabel("");
    private JLabel lblComputer = new JLabel("Ninja ");
    private JLabel lblCval = new JLabel("");
    private JLabel lblHpoint = new JLabel(" 0");
    private JLabel lblCpoint = new JLabel("0 ");
    private JTextField TFusername = new JTextField("Skriv ditt namn här!");
    private Font lblFont = new Font("SansSerif", Font.PLAIN, 18);
    private JButton bältdjur = new JButton("High kick");
    private JButton igelkott = new JButton("HammerPunch");
    private JButton nät = new JButton("Ducka");
    private JButton nyttSpel = new JButton("Nytt spel");
    private JButton avsluta = new JButton("Avsluta");
    private Controller controller;
    private DuellViewer duellViewer;
    private HighScore highScore;

    /**
     * Konstruktor som innehåller kod till grafiken.
     */
    public GameBoard(DuellViewer race, Controller controller, HighScore highScore) {
        duellViewer = race;
        this.controller = controller;
        this.duellViewer = race;
        this.highScore = highScore;

//        setSize(800, 500);
        this.setLayout(new BorderLayout());

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblMsg.setBounds(0, 0, 0, 0);
        lblHuman.setBounds(30, 80, 100, 30);
        lblComputer.setBounds(200, 80, 150, 30);
        lblHpoint.setBounds(30, 110, 100, 30);
        lblCpoint.setBounds(200, 110, 100, 30);
        lblHval.setBounds(30, 140, 100, 30);
        lblCval.setBounds(200, 140, 100, 30);

        panel.add(lblMsg);
        panel.add(lblHuman);
        panel.add(lblHpoint);
        panel.add(lblComputer);
        panel.add(lblCpoint);
        panel.add(lblHval);
        panel.add(lblCval);

        lblMsg.setFont(lblFont);
        lblHuman.setFont(lblFont);
        lblComputer.setFont(lblFont);
        lblHpoint.setFont(lblFont);
        lblCpoint.setFont(lblFont);
        lblHval.setFont(lblFont);
        lblCval.setFont(lblFont);

        add(panel, BorderLayout.SOUTH);
        add(panel2, BorderLayout.CENTER);
        add(TFusername, BorderLayout.NORTH);

        bältdjur.setBounds(10, 10, 100, 50);
        igelkott.setBounds(120, 10, 100, 50);
        nät.setBounds(230, 10, 100, 50);
        nyttSpel.setBounds(10, 70, 320, 50);
        avsluta.setBounds(10, 130, 320, 50);

        grid.add(bältdjur);
        grid.add(igelkott);
        grid.add(nät);
        grid.add(nyttSpel);
        grid.add(avsluta);

        bältdjur.addActionListener(this);
        igelkott.addActionListener(this);
        nät.addActionListener(this);
        nyttSpel.addActionListener(this);
        avsluta.addActionListener(this);
        add(duellViewer.getWindow(), BorderLayout.CENTER);
        add(grid, BorderLayout.WEST);

//        setResizable(false);
        setVisible(true);
    }

    public void setCards(JPanel cards, CardLayout cardlayout){
        this.cardsMain = cards;
        this.cardlayout= cardlayout;
    }

    /**
     * Skickar username och highscore till MYSQL databasen
     */
    public void sendScore(int score){
      String userName = TFusername.getText();
      
      highScore.sendScore(userName, score);
      JOptionPane.showMessageDialog(null, highScore.fillArea());
    }
    /**
     *Visar vad spelaren väljer för attack.
     */
    public void human(int val) {
        if (val == 0) {
            lblHval.setText("High Kick");
        } else if (val == 1) {
            lblHval.setText("HammerPunch");
        } else if (val == 2) {
            lblHval.setText("Duckade");
        }
    }
    /**
     *Visar vad datorn väljer för attack.
     */
    public void computer(int val) {
        if (val == 0) {
            lblCval.setText("High Kick");
        } else if (val == 1) {
            lblCval.setText("HammerPunch");
        } else if (val == 2) {
            lblCval.setText("Duckade");
        }
    }

    //om spelaren vinner så visas texten "Du vann!"
    public void sethScore(int res) {
        if (res == 3) {
            lblMsg.setText("Du vann!");
        }
        this.lblHpoint.setText("" + res);
    }
    //om datorn får 3 poäng så visas meddelandet "Datorn Vann!"
    public void setcScore(int res) {
        if (res == 3) {
            lblMsg.setText("Ninjan Vann!");
        }
        this.lblCpoint.setText("" + res);
    }

    // Visar allt som det var från början.
    public void newGame() {
        duellViewer.start();
        lblMsg.setText(" Först till 3! ");
        lblHuman.setText("Human ");
        lblHval.setText("");
        lblHpoint.setText(" 0");
        lblComputer.setText("Computer ");
        lblCval.setText("");
        lblCpoint.setText("0 ");

    }

    // Gråar ut knapparna
    public void disableButtons() {
        bältdjur.setEnabled(false);
        igelkott.setEnabled(false);
        nät.setEnabled(false);
    }
    // Gör knapparna användbara igen.
    public void enableButtons() {
        bältdjur.setEnabled(true);
        igelkott.setEnabled(true);
        nät.setEnabled(true);
    }

    //Metod som styr vad som händer när man trycker på knapparna.
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == bältdjur) {
            controller.nyttVal(0);
        } else if (e.getSource() == igelkott) {
            controller.nyttVal(1);
        } else if (e.getSource() == nät) {
            controller.nyttVal(2);
        } else if (e.getSource() == nyttSpel) {
            controller.nyttSpel();
        } else if (e.getSource() == avsluta) {
            this.cardlayout.show(this.cardsMain, (String)"Index");
        }
    }
}
