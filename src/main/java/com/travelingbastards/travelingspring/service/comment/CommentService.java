package com.travelingbastards.travelingspring.service.comment;

import com.travelingbastards.travelingspring.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    Comment findById(long id);
}
