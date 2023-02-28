package com.fdmgroup.CharCloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CharCloud.exceptions.CommentValidationException;
import com.fdmgroup.CharCloud.model.Comment;
import com.fdmgroup.CharCloud.repository.CommentRepository;

@Service
public class CommentService implements ICommentService{

	    @Autowired
	    private CommentRepository commentRepository;
	    
	    @Override
	    public List<Comment> findByCollectionId(Integer collectionId) {
	        return commentRepository.findByCollectionId(collectionId);
	    }
	    @Override
	    public void saveComment(Comment comment) throws CommentValidationException {
	        // Perform validation checks on the comment
	        if (comment.getText() == null || comment.getText().isEmpty()) {
	            throw new CommentValidationException("Comment content cannot be empty");
	        }
	        // Save the comment
	        commentRepository.save(comment);
	    }
	    @Override
	    public void deleteComment(Comment comment) {
	        commentRepository.delete(comment);
	    }

	}