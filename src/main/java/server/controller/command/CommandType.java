package server.controller.command;

import server.controller.command.impl.*;

public enum CommandType {

    DEFAULT(new DefaultCommandImpl()),
    LOGIN(new LoginCommandImpl()),
    REGISTRATION(new RegistrationCommandImpl()),
    GET_PRODUCTS(new GetProductsCommandImpl()),
    ORDER(new OrderCommandImpl()),
    GET_ORDERS(new GetOrderCommandImpl()),
    ADD_PRODUCT(new AddProductCommandImpl()),
    DELETE_PRODUCT(new DeleteProductCommandImpl()),
    ADD_COMMENT(new AddCommentCommandImpl()),
    DELETE_COMMENT(new DeleteCommentCommandImpl()),
    GET_COMMENT(new GetCommentCommandImpl()),
    PAY_ORDER(new PayCommandImpl()),
    DELETE_ORDER(new DeleteOrderCommandImpl()),
    GET_USER_LOGIN(new GetUserLoginCommandImpl());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
