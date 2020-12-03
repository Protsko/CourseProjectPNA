package server.controller.command.impl;

import server.app.AppContext;
import server.controller.command.Command;
import server.exception.DaoException;
import server.service.CommentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DeleteCommentCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CommentService commentService = (CommentService) AppContext.getInstance().getBean(CommentService.class);
    @Override
    public String execute(Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        try {
            commentService.deleteComment(id);
            return "code=ok";
        } catch (DaoException e) {
            LOGGER.error("Failed to delete comment!", e);
        }
        return "code=error";
    }
}
