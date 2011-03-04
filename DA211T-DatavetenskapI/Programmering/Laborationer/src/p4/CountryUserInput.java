package p4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//import java.awt.*;

public class CountryUserInput implements ActionListener
{
    private JFrame frame = new JFrame();
    private CountryController controller;
    private JButton btnAlla = new JButton("Visa alla l�nder");
    private JButton btnH�mta = new JButton("H�mta l�nder");
    private JButton btnSpara = new JButton("Spara l�nder");
    private JButton btnUrval = new JButton("G�r urval");
    private JButton btn�ndra = new JButton("�ndra");
    private JTextField tfBefolkning = new JTextField();
    private JTextField tfLand = new JTextField();
    private JTextField tfMaximum = new JTextField();
    private JTextField tfMinimum = new JTextField();

    /**
     * Bygger upp ett f�nster och g�r det synligt
     */
    public CountryUserInput(CountryController controller) {
        this.controller = controller;
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("CountryUserInput");
        frame.setBounds(380, 30, 399, 212);

        btnH�mta.setBounds(10, 150, 180, 23);
        btnSpara.setBounds(200, 150, 180, 23);

        frame.getContentPane().add(urvalPanel());
        frame.getContentPane().add(�ndraPanel());
        frame.getContentPane().add(btnH�mta);
        frame.getContentPane().add(btnSpara);
        frame.setVisible(true);

        btnAlla.addActionListener(this);
        btnH�mta.addActionListener(this);
        btnSpara.addActionListener(this);
        btnUrval.addActionListener(this);
        btn�ndra.addActionListener(this);
    }

    /*
     * Skapar panelen i vilken anv�ndaren kan ange minsta resp st�rsta befolkning, och
     * klicka p� knapparna "G�r urval" och "Visa alla l�nder". N�r panelen med komponenter
     * �r f�rdig returneras panelen av metoden.
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
     * Skapar panelen i vilken anv�ndaren kan ange ett land och ny befolkning, och
     * klicka p� knappen "�ndra". N�r panelen med komponenter �r f�rdig returneras
     * panelen av metoden.
     */
    private JPanel �ndraPanel() {
        JPanel jPanel2 = new JPanel(null);
        JLabel lblLand = new JLabel("Land");
        JLabel lblBefolkning = new JLabel("Befolkning");
        // lblLand.setFont(sansSerif12);
        lblLand.setBounds(10, 20, 110, 14);
        // lblBefolkning.setFont(sansSerif12);
        lblBefolkning.setBounds(130, 20, 110, 14);
        tfLand.setBounds(10, 40, 110, 20);
        tfBefolkning.setBounds(130, 40, 110, 20);
        btn�ndra.setBounds(250, 40, 130, 23);
        jPanel2.setBorder(BorderFactory
                .createTitledBorder("\u00c4ndra befolkning"));
        jPanel2.setBounds(0, 70, 390, 70);
        jPanel2.add(lblLand);
        jPanel2.add(lblBefolkning);
        jPanel2.add(tfLand);
        jPanel2.add(tfBefolkning);
        jPanel2.add(btn�ndra);

        return jPanel2;
    }

    // Endast f�r att se f�nstrets utseende
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
        else if (e.getSource() == btnH�mta)
            controller.loadCountries();
        else if (e.getSource() == btnSpara)
            controller.saveCountries();
        else if (e.getSource() == btnUrval)
            controller.showSelection(Long.parseLong(tfMinimum.getText()), Long.parseLong(tfMaximum.getText()));
        else if (e.getSource() == btn�ndra)
            controller.changePopulation(tfLand.getText(), Long.parseLong(tfBefolkning.getText()));
    }
}
