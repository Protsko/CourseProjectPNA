package server.controller.command.impl;

import client.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.app.AppContext;
import server.controller.command.Command;
import server.exception.ServiceException;
import server.service.UserService;

import java.util.Map;

public class GetUserLoginCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService =(UserService) AppContext.getInstance().getBean(UserService.class);

    @Override
    public String execute(Map<String, String> params) {
        String response = "";
        Long id = Long.parseLong(params.get("userId"));
        try {
            User userById = userService.getUserById(id);
            response = userById.getLogin();
        } catch (ServiceException e) {
            LOGGER.error("Failed to get user!", e);
        }
        return response;
    }
}
