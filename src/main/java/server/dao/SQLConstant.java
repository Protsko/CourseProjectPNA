package server.dao;

public class SQLConstant {
    public static final String INSERT_USER = "INSERT INTO user_account ({0}) VALUES ({1})";
    public static final String SELECT_USER = "SELECT * FROM user_account WHERE user_login=?";

    public static final String INSERT_PRODUCT = "INSERT INTO product ({0}) VALUES ({1})";
    public static final String SELECT_PRODUCT = "SELECT * FROM product";
    public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id=?";

    public static final String INSERT_ORDER = "INSERT INTO user_order ({0}) VALUES ({1})";
    public static final String SELECT_ORDER = "SELECT * FROM user_order WHERE user_account_id=?";
    public static final String UPDATE_ORDER = "UPDATE user_order SET is_payed=1 WHERE id=?";
    public static final String DELETE_ORDER = "DELETE FROM user_order WHERE id=?";

    public static final String INSERT_COMMENT = "INSERT INTO comment ({0}) VALUES ({1})";
    public static final String SELECT_COMMENT = "SELECT * FROM comment WHERE product_id=?";
    public static final String DELETE_COMMENT = "DELETE FROM comment WHERE id=?";
}
