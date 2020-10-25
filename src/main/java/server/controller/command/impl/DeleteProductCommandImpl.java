package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.ServiceException;
import server.service.ProductManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DeleteProductCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ProductService productService = (ProductService) AppContext.getInstance().getBean(ProductService.class);

    @Override
    public String execute(Map<String, String> params) {
        Integer productId = Integer.parseInt(params.get("id"));
        try {
            productService.deleteProduct(productId);
            return "code=ok";
        } catch (ServiceException e) {
            LOGGER.error("Failed to delete product!", e);
        }
        return "code=error";
    }
}
