package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.ServiceException;
import server.service.OrderService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DeleteOrderCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final OrderService orderService = (OrderService) AppContext.getInstance().getBean(OrderService.class);

    @Override
    public String execute(Map<String, String> params) {
        Integer orderId = Integer.parseInt(params.get("id"));
        try {
            orderService.deleteOrder(orderId);
            return "code=ok";
        } catch (ServiceException e) {
            LOGGER.error("Failed to delete order!", e);
        }
        return "code=error";
    }
}
