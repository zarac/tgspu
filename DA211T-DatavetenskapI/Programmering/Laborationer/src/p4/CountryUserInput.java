package p4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//import java.awt.*;

public class CountryUserInput implements ActionListener
{
    private JFrame frame = new JFrame();
    private CountryController controller;
    private JButton btnAlla = new JButton("Visa alla länder");
    private JButton btnHämta = new JButton("Hämta länder");
    private JButton btnSpara = new JButton("Spara länder");
    private JButton btnUrval = new JButton("Gör urval");
    private JButton btnÄndra = new JButton("Ändra");
    private JTextField tfBefolkning = new JTextField();
    private JTextField tfLand = new JTextField();
    private JTextField tfMaximum = new JTextField();
    private JTextField tfMinimum = new JTextField();

    /**
     * Bygger upp ett fönster och gör det synligt
     */
    public CountryUserInput(CountryController controller) {
        this.controller = controller;
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("CountryUserInput");
        frame.setBounds(380, 30, 399, 212);

        btnHämta.setBounds(10, 150, 180, 23);
        btnSpara.setBounds(200, 150, 180, 23);

        frame.getContentPane().add(urvalPanel());
        frame.getContentPane().add(ändraPanel());
        frame.getContentPane().add(btnHämta);
        frame.getContentPane().add(btnSpara);
        frame.setVisible(true);

        btnAlla.addActionListener(this);
        btnHämta.addActionListener(this);
        btnSpara.addActionListener(this);
        btnUrval.addActionListener(this);
        btnÄndra.addActionListener(this);
    }

    /*
     * Skapar panelen i vilken användaren kan ange minsta resp största befolkning, och
     * klicka på knapparna "Gör urval" och "Visa alla länder". När panelen med komponenter
     * är färdig returneras panelen av metoden.
     */
    private JPanel urvalPanel() {
        JPanel panelUrval = new JPanel(null);
        JLabel lblMin = new JLabel("Minimal befolkning");
        JLabel lblMax = new JLabel("Maximal befolkning");

        lblMin.setBounds(10, 20, 110, 14);
        lblMax.setBounds(130, 20, 110, 14);
        btnUrval.setBounds(250, 40, 130, 23);
        btnAlla.setBounds(250, 14, 130, 23);
        tfMinimum.setBounds(10, 40, 110, 20);
        tfMaximum.setBounds(130, 40, 110, 20);
        panelUrval.setBounds(0, 0, 390, 70);
        panelUrval.setBorder(BorderFactory.createTitledBorder("Urval"));
        panelUrval.add(lblMin);
        panelUrval.add(lblMax);
        panelUrval.add(btnUrval);
        panelUrval.add(btnAlla);
        panelUrval.add(tfMinimum);
        panelUrval.add(tfMaximum);

        return panelUrval;
    }

    /*
     * Skapar panelen i vilken användaren kan ange ett land och ny befolkning, och
     * klicka på knappen "Ändra". När panelen med komponenter är färdig returneras
     * panelen av metoden.
     */
    private JPanel ändraPanel() {
        JPanel jPanel2 = new JPanel(null);
        JLabel lblLand = new JLabel("Land");
        JLabel lblBefolkning = new JLabel("Befolkning");
        // lblLand.setFont(sansSerif12);
        lblLand.setBounds(10, 20, 110, 14);
        // lblBefolkning.setFont(sansSerif12);
        lblBefolkning.setBounds(130, 20, 110, 14);
        tfLand.setBounds(10, 40, 110, 20);
        tfBefolkning.setBounds(130, 40, 110, 20);
        btnÄndra.setBounds(250, 40, 130, 23);
        jPanel2.setBorder(BorderFactory
                .createTitledBorder("\u00c4ndra befolkning"));
        jPanel2.setBounds(0, 70, 390, 70);
        jPanel2.add(lblLand);
        jPanel2.add(lblBefolkning);
        jPanel2.add(tfLand);
        jPanel2.add(tfBefolkning);
        jPanel2.add(btnÄndra);

        return jPanel2;
    }

    // Endast för att se fönstrets utseende
    public static void main(String args[]) {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                new CountryUserInput(null);
            }
        });
    }

    /**
     * {@inheritDoc}
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("CountryUserInput.actionPerformed();");

        if (e.getSource() == btnAlla)
            controller.showAllCountries();
        else if (e.getSource() == btnHämta)
            controller.loadCountries();
        else if (e.getSource() == btnSpara)
            controller.saveCountries();
        else if (e.getSource() == btnUrval)
            controller.showSelection(Long.parseLong(tfMinimum.getText()), Long.parseLong(tfMaximum.getText()));
        else if (e.getSource() == btnÄndra)
            controller.changePopulation(tfLand.getText(), Long.parseLong(tfBefolkning.getText()));
    }
}
