package com.travelingbastards.travelingspring.service.comment;

import com.travelingbastards.travelingspring.model.Comment;
import com.travelingbastards.travelingspring.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;


    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid comment ID: " + id));
        return comment;
    }
}
