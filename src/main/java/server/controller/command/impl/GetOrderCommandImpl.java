package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import client.entity.Order;
import server.exception.ServiceException;
import server.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GetOrderCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final OrderService orderService = (OrderService) AppContext.getInstance().getBean(OrderService.class);

    @Override
    public String execute(Map<String, String> params) {
        try {
            List<Order> orders = orderService.getOrders(Long.parseLong(params.get("user_id")));
            String response = "";
            for (int i = 0; i < orders.size(); i++) {
                response += orders.get(i).toString();
                if (i != orders.size() - 1) {
                    response += "&";
                }
            }
            return response;
        } catch (ServiceException e) {
            LOGGER.error("Failed to get orders!", e);
        }
        return "code=error";
    }
}
