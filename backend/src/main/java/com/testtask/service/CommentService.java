package com.testtask.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testtask.dto.CommentDto;
import com.testtask.model.Comment;
import com.testtask.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	public void addComment(CommentDto commentDto, String author) {
		Comment comment = new Comment();
		comment.setAuthor(author);
		comment.setText(commentDto.getText());
		comment.setCreatedAt(LocalDateTime.now());
		commentRepository.save(comment);
	}
	
	public List<Comment> getComments() {
		return commentRepository.findAll();
	}
}
