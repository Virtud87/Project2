package com.investing_app.service;

import com.investing_app.customexceptions.CommentNotFound;
import com.investing_app.dao.CommentingDAO;
import com.investing_app.entities.Commenting;

import java.util.List;

public class CommentingServicesImp implements CommentingServices {
    CommentingDAO commentingDAO;
    public CommentingServicesImp (CommentingDAO commentingDAO){
        this.commentingDAO = commentingDAO;
    }

    @Override
    public Commenting createCommentService(Commenting commenting) {
        return this.commentingDAO.createComment(commenting);
    }

    @Override
    public Commenting getCommentByIdService(int id) {
        try {
            Commenting commenting = this.commentingDAO.getCommentById(id);
            return commenting;
        }
        catch (CommentNotFound e){
            throw new CommentNotFound("Comment not found");
        }
    }

    @Override
    public List<Commenting> getAllCommentsService() {
        return this.commentingDAO.getAllComments();
    }
}

