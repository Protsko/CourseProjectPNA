package server.controller.command;

public class CommandProvider {
    private CommandProvider() {
    }

    public static String getCommandName(String[] params) {
        for (String param : params) {
            if(param.startsWith("command=")) {
                return param.replace("command=", "");
            }
        }
        return "default";
    }

    public static Command defineCommand(String commandName) {
        Command command = CommandType.DEFAULT.getCommand();
        CommandType[] types = CommandType.values();
        for (CommandType type : types) {
            String typeName = type.name();
            if(typeName.equalsIgnoreCase(commandName)) {
                command = type.getCommand();
            }
        }
        return command;
    }
}
