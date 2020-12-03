package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.ServiceException;
import server.service.ProductService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class AddProductCommandImpl implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProductService productService = (ProductService) AppContext.getInstance().getBean(ProductService.class);

    @Override
    public String execute(Map<String, String> params) {
        String name = params.get("product_name");
        String price = params.get("product_price");
        String description = params.get("product_description");
        boolean isAvailable = Boolean.parseBoolean(params.get("is_available"));
        try {
            productService.addProduct(name, new Double(price), description, isAvailable);
            return "result=true";
        } catch (ServiceException e) {
            LOGGER.error("Failed to add product!", e);
        }
        return "result=false";
    }
}
