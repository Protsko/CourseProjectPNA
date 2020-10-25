package server.controller.command;

public enum CommandType {
    DEFAULT(new DefaultCommandImpl()),
    LOGIN(new LoginCommandImpl()),
    REGISTRATION(new RegistrationCommandImpl()),
    GET_PRODUCTS(new GetProductsCommandImpl()),
    ORDER(new OrderCommandImpl()),
    GET_ORDERS(new GetOrdersCommandImpl()),
    DELETE_PRODUCT(new DeleteProductCommandImpl()),
    PAY_ORDER(new PayCommandImpl());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}