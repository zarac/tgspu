package blackJack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlackJackDatabase extends MySQLDatabase
{
    private Logger logger = new Logger(this);

    private String playerTable = "blackJackPlayer";

    private int defaultStack = 100;

    /**
     * Constructs a new instance.
     */
    public BlackJackDatabase(String p_host, int p_port, String p_username, String p_password, String p_databaseName)
    {
        super(p_host, p_port, p_username, p_password, p_databaseName);
    }

    public Player getPlayerByName(String p_name)
    {
        logger.debug("getPlayerByName(" + p_name + "):");

        String query = "SELECT name,stack FROM " + playerTable + " WHERE name='" + p_name + "'";
        ResultSet resultSet = doSelect(query);

        int numRows = getNumRows(resultSet);

        logger.debug("numRows='" + numRows + "'");

        //Player player = new Player();
        //Player player;

        // make new player
        if (numRows == 0)
        {
            logger.log("player not found: creating new");
            //player.setStack(1000);
            //player.setName(p_name);
            return new Player(p_name, defaultStack);
        }
        else
        {
            try
            {
                // there should only be one player (and he is first)
                resultSet.first();
                logger.debug("player found: name='" + resultSet.getString("name") + "',stack='" + resultSet.getInt("stack") + "'");
                //logger.debug("player found: name='" + player.getName() + "',stack='" + player.getStack() + "'");

                String name = resultSet.getString("name");
                // +5 login bonus, mostly for testing but also a friendly feature.
                int stack = resultSet.getInt("stack") + 5;

                return new Player(name, stack);
            }
            catch (SQLException e)
            {
                logger.error(e.toString());
                return null;
            }
        }

        //return player;
    }

    /**
     * Give a name, return a player and his stack. Returns null when player
     * does not exist.
     * 
     * @param p_name The name of the player.
     * @return The player if found, else null.
     */
    public Player getPlayer(String p_name)
    {
        ResultSet resultSet = doSelect("SELECT name, stack FROM " + playerTable + " WHERE name='" + p_name + "'");

        try
        {
            resultSet.first();
            if (resultSet.getRow() == 1)
            {
                logger.log("getPlayer(" + p_name + "): found");
                return new Player(p_name, resultSet.getInt("stack"));
            }
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }

        return null;
    }

    /**
     * Make sure a player exists. Create one if not. Update if found.
     * 
     * @param p_player The player to store.
     */
    public void storePlayer(Player p_player)
    {
        logger.debug("storePlayer(name='" + p_player.getName() + "',stack='" + p_player.getStack() + "'):");

        Player player = getPlayer(p_player.getName());
        if (player == null)
        {
            logger.info("storePlayer(..): creating player");
            doInsert("INSERT INTO " + playerTable + " VALUES('" + p_player.getName() + "', " + p_player.getStack() + ")");
        }
        else
        {
            logger.info("storePlayer(..): updating player");
            doUpdate("UPDATE " + playerTable + " SET stack=" + p_player.getStack() + " WHERE name='" + p_player.getName() + "'");
        }
    }

    public void test()
    {
        logger.log("test():");
    }

    public void dumpPlayerTable()
    {
        logger.debug("dumpPlayerTable():");
        String query = "SELECT * FROM " + playerTable;

        //executeSQLquery(query);
        //String resultSet = getResultSet().toString();

        ResultSet resultSet = doSelect(query);
        logger.log("dumpPlayerTable(): " + resultSet);

        int numRows = getNumRows(resultSet);

        logger.debug("numRows=" + numRows);

        try
        {
            while (resultSet.next())
            {
                logger.log("resultSet.getString('name'): " + resultSet.getString("name"));
            }
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }
    }
}
