package server.dao.mysql;

import server.dao.CommentDao;
import server.dao.ConnectionManager;
import server.dao.SQLConstant;
import client.entity.Comment;
import server.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MySQLComeentDao implements CommentDao {
    @Override
    public void save(Comment comment) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = MessageFormat.format(SQLConstant.INSERT_COMMENT, "product_id, user_account_id, comment_id", "?, ?, ?");
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, comment.getProductId());
                statement.setLong(2, comment.getUserId());
                statement.setString(3, comment.getCommentText());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in saving comment", e);
        }
    }

    @Override
    public List<Comment> getCommentsByProductId(Integer productId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.SELECT_COMMENT)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Comment comment = new Comment(
                            resultSet.getInt("id"),
                            resultSet.getInt("product_id"),
                            resultSet.getLong("user_account_id"),
                            resultSet.getString("comment_text")
                    );
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getting comments", e);
        }
        return comments;
    }

    @Override
    public void delete(Integer commentId) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_COMMENT)) {
                statement.setInt(1, commentId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in deleting comment", e);
        }
    }
}
