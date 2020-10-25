package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import client.entity.User;
import server.exception.ServiceException;
import server.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LoginCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService = (UserService) AppContext.getInstance().getBean(UserService.class);

    @Override
    public String execute(Map<String, String> params) {
        String login = params.get("login");
        String password = params.get("password");
        User user;
        try {
            user = userService.loginUser(login, password);
            if (user != null) {
                return "result=true&id=" + user.getId() + "&login=" + user.getLogin() + "&password=" + user.getPassword() + "&role=" + user.getUserRole().name();
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to login!", e);
        }
        return "result=false";
    }
}
