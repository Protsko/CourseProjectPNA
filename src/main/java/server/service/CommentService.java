package server.service;

import server.dao.CommentDao;
import client.entity.Comment;
import server.exception.DaoException;

import java.util.List;

public class CommentService {

    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void addComment(Comment comment) throws DaoException {
        commentDao.save(comment);
    }

    public List<Comment> getComments(Integer productId) throws DaoException {
        return commentDao.getCommentsByProductId(productId);
    }

    public void deleteComment(Integer id) throws DaoException {
        commentDao.delete(id);
    }
}
