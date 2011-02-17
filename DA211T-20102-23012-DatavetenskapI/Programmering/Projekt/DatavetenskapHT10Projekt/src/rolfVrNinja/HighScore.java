/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rolfVrNinja;

import com.mysql.jdbc.ResultSetMetaData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import sql.MysqlDataBase;

/**
 *
 * @author wiik
 */
public class HighScore extends JPanel {
    /**
     * Klassens instans variabler de första lagrar information
     * relevant för datbas hanteringen. De andra för att utforma
     * jpanel objektet.
     */
    private MysqlDataBase sql;
    private JTextArea txt = new JTextArea();
    private JLabel label = new JLabel();
    /**
     * HighScore konstruktorn den har en jPanel som inparameter
     * för att kunna ändra panel. I konstruktorn utformas
     * även HighScore JPanelen.
     */
    public HighScore(){
        sql = new MysqlDataBase();
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setText("High Score");
        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.NORTH);
        txt.setEditable(false);
        this.add(txt, BorderLayout.CENTER);
    }
    /**
     * fillArea metoden tömer text arean och fyller den
     * med ny information från databasen.
     */
    public String fillArea(){
        String txt = "";
        txt = sql.getResultSetString(sql.getAll("rolfvrninja"));
        return txt;
    }
    /**
     * sendScore metoden skicker en querry till databasen.
     */
    public void sendScore(String name, int score){
        sql.insertRow(sql.createInsert("rolfvrninja", name, score));
    }
}
