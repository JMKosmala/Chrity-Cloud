package com.fdmgroup.CharCloud.service;

import java.util.List;

import com.fdmgroup.CharCloud.model.Comment;

public interface ICommentService  {
	List<Comment> findByCollectionId(Integer collectionId);

	void saveComment(Comment comment) throws Exception;

	void deleteComment(Comment comment);

}
