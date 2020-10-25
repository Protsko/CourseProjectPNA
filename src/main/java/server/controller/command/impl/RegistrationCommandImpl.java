package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.ServiceException;
import server.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class RegistrationCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService = (UserService) AppContext.getInstance().getBean(UserService.class);

    @Override
    public String execute(Map<String, String> params) {
        String login = params.get("login");
        String password = params.get("password");
        try {
            userService.addUser(login, password);
            return "result=true";
        } catch (ServiceException e) {
            LOGGER.error("Failed to registration!", e);
        }
        return "result=false";
    }
}
