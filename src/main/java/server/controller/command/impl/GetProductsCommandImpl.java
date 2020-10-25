package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import client.entity.Product;
import server.exception.ServiceException;
import server.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GetProductsCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProductService productService = (ProductService) AppContext.getInstance().getBean(ProductService.class);

    @Override
    public String execute(Map<String, String> params) {
        try {
            List<Product> products = productService.getAllProducts();
            String response = "";
            for (int i = 0; i < products.size(); i++) {
                response += products.get(i).toString();
                if (i != products.size() - 1) {
                    response += "&";
                }
            }
            return response;
        } catch (ServiceException e) {
            LOGGER.error("Failed to get products!", e);
        }
        return "code=error";
    }
}
