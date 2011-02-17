/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiik
 */
public class MysqlDataBase {
    /**
     * Instas variablerna i klassen
     */
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;

    private String url = "jdbc:mysql://"+"195.178.232.7:"+"3306"+"/DA211T10C"+"4062119";
    private String usr = "DA211T10C"+"4062119";
    private String pwd = "4062119";

    /**
     * metoden connect kopplar upp mot sql databasen.
     *
     * @param url               vilken url databasen har
     * @param usr               användarnamnet
     * @param pw                lösenordet
     * @throws SQLException     Exception metoden kasstar
     */
    public void connect(String url, String usr, String pw) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usr, pw);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Can not connect: No driver found: "+ex);
        }
    }
    /**
     * disconnect metoden kopplar ner från sql databasen
     *
     * @throws SQLException     Exception metoden kasstar
     */
    public void disconnect() throws SQLException{
        statement.close();
        connection.close();
    }
    /**
     * executeSQLquerry metoden skickar en String till statement
     * som skickar en querry till databasen.
     *
     * @param querry    String variabel med querryn som ska skickas
     */
    public void executeSQLquery(String querry){
        try {
            result = statement.executeQuery(querry);
        } catch (SQLException ex) {
            System.out.println("SQL Exception: "+ex);
        }
    }
    /**
     * executeSQLUpdate metoden skickar en String till statement
     * som skickar en querry till databasen.
     *
     * @param sql    String variabel med querryn som ska skickas
     */
    public int executeSQLUpdate(String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }
    /**
     * getResultSet returnerar ett resultSet med innehållet svaret
     * på sql querryn.
     *
     * @return      ResultSet
     */
    public ResultSet getResultSet(){
        return result;
    }

    /**
     * getQuerry metoden tömer text arean skickar
     * en querry till sql databasen som lägger till en rad
     * med spelarens namn och poäng
     */
    public void insertRow(String query){
        try {
            connect(url, usr, pwd);
            executeSQLUpdate(query);
            disconnect();
        } catch (SQLException ex) {}
    }
    /**
     * fillArea metoden tömer text arean och fyller den
     * med ny information från databasen.
     */
    public String getResultSetString(String query){
        String temp = "";
        try {
            connect(url, usr, pwd);
            executeSQLquery(query);
            temp = showResultSet(getResultSet());
            disconnect();
        } catch (SQLException ex) {}
        return temp;
    }
    /**
     * showResultSet tar emot ett resultset och skriver ut
     * innehållet i textarean.
     *
     * @param resultSet         resultset från sql databas
     * @throws SQLException     vid problem med sql
     */
    public String showResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = (ResultSetMetaData) resultSet.getMetaData();
        String res = "";
        int colCount = meta.getColumnCount();
        for(int i=1; i<=colCount; i++)
            res += meta.getColumnLabel(i) + ", ";
        res += "\n";
        while(resultSet.next()) {
            for(int i=1; i<=colCount; i++)
                res += resultSet.getObject(i).toString() + ", ";
            res += "\n";
        }
        return res;
    }

    public String createInsert(String tabell, String name, int score){
            return "INSERT INTO "+tabell+" VALUES (\""+name+"\","+score+")";
        }

    public String getAllOrderByHighscoreDesc(String tabell){
            return "SELECT * FROM "+tabell + " ORDER BY highscore DESC";
        }

    public String getAll(String tabell){
            return "SELECT * FROM "+tabell;
        }

}
