package com.investing_app.dao;

import com.investing_app.entities.Commenting;

import java.util.List;

public interface CommentingDAO {
    Commenting createComment(Commenting commenting);

    Commenting getCommentById(int id);

    List<Commenting> getAllComments();

}
