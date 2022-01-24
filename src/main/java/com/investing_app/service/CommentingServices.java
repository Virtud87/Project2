package com.investing_app.service;

import com.investing_app.entities.Commenting;

import java.util.List;

public interface CommentingServices {
    Commenting createCommentService(Commenting commenting);

    Commenting getCommentByIdService(int id);

    List<Commenting> getAllCommentsService();
}
