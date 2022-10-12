package com.testtask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.testtask.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}