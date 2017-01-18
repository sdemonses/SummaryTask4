package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dmitry on 16.01.17.
 */
public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }


}
