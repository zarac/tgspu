package blackJack;

class Logger
{
    // The object were the logging is coming from.
    private Object object;

    /**
     * @param p_object A reference to the object we are doing the logging for (usually this).
     */
    public Logger(Object p_object)
    {
        object = p_object;
        System.out.println("[LOG INIT] " + object.getClass().getName() + "{" + object.hashCode() + "}");
        //System.out.println("Logger(object='" + object.toString() + "')");
    }

    /**
     * Logs a message with debug level.
     * 
     * @param p_message The debug message to be logged.
     */
    public void debug(String p_message)
    {
        System.out.println("[DEBUG] " + object.getClass().getName() + "{" + object.hashCode() + "}\n\t" + p_message);
    }

    /**
     * Logs a message with error level.
     * 
     * @param p_message The error message.
     */
    public void error(String p_message)
    {
        System.out.println("[ERROR] " + object.getClass().getName() + "{" + object.hashCode() + "}\n\t" + p_message);
    }

    /**
     * Logs a message with info / normal level.
     * 
     * @param p_message The message to be logged.
     */
    public void info(String p_message)
    {
        System.out.println("[INFO] " + object.getClass().getName() + "{" + object.hashCode() + "}\n\t" + p_message);
    }

    /**
     * Logs a message with "normal" level.
     * 
     * @param p_message The message to be logged.
     */
    public void log(String p_message)
    {
        System.out.println("[ ] " + object.getClass().getName() + "{" + object.hashCode() + "}\n\t" + p_message);
    }
}
