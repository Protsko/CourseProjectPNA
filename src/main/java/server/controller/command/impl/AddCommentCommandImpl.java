package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.DaoException;
import server.service.CommentService;
import client.entity.Comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class AddCommentCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CommentService commentService = (CommentService) AppContext.getInstance().getBean(CommentService.class);
    @Override
    public String execute(Map<String, String> params) {
        int productId = Integer.parseInt(params.get("productId"));
        long userId = Long.parseLong(params.get("userId"));
        String commentText = params.get("commentText");
        Comment comment = new Comment(null, productId, userId, commentText);
        try {
            commentService.addComment(comment);
            comment.setId(1);
            return comment.toString();
        } catch (DaoException e) {
            LOGGER.error("Failed to add comment!", e);
        }
        return "result=false";
    }
}
