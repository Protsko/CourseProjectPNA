package server.dao;

import client.entity.Comment;
import server.exception.DaoException;

import java.util.List;

public interface CommentDao {
    void save (Comment comment) throws DaoException;

    List<Comment> getCommentsByProductId(Integer orderId) throws DaoException;

    void delete(Integer commentId) throws DaoException;
}
