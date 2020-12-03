package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import client.entity.Comment;
import server.exception.DaoException;
import server.service.CommentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GetCommentCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CommentService commentService = (CommentService) AppContext.getInstance().getBean(CommentService.class);
    @Override
    public String execute(Map<String, String> params) {
        try {
            List<Comment> comments = commentService.getComments(Integer.parseInt(params.get("productId")));
            String response = "";
            for (int i = 0; i < comments.size(); i++) {
                response += comments.get(i).toString();
                if (i != comments.size() - 1) {
                    response += "&";
                }
            }
            return response;
        } catch (DaoException e) {
            LOGGER.error("Failed to get comments!", e);
        }
        return "code=error";
    }
}
