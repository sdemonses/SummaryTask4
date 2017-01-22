package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 *
 * @author D.Biblyi
 *
 */
public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("language", new LanguageCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("tour", new TourCommand());
        commands.put("password", new PasswordCommand());
        commands.put("filter", new FilterCommand());
        commands.put("changeStatus", new StatusCommand());
        commands.put("management", new ManagerCommand());
        commands.put("creator", new CreatorCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("users", new UserCommand());
        commands.put("banned", new BannedCommand());

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
