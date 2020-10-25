package server.controller.command.impl;

import server.controller.command.Command;

import java.util.Map;

public class DefaultCommandImpl implements Command {
    @Override
    public String execute(Map<String, String> params) {
        return "Wrong query. Use command parameter (command=) for calling a necessary Command";
    }
}
