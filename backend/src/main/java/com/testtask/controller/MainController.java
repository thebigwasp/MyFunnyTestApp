package com.testtask.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testtask.dto.CommentDto;
import com.testtask.dto.UsernameDto;
import com.testtask.model.Comment;
import com.testtask.service.CommentService;

@RestController
public class MainController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<Void> authenticate(@RequestBody @Valid UsernameDto usernameDto, HttpServletResponse response) {
		Cookie cookie = new Cookie("Auth", usernameDto.getUsername());
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plusMonth = now.plusMonths(1);
		cookie.setMaxAge((int) Duration.between(now, plusMonth).getSeconds()); // set cookie for a month
		response.addCookie(cookie);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/comment")
	public ResponseEntity<String> comment(@RequestBody @Valid CommentDto commentDto, Authentication authentication) {
		commentService.addComment(commentDto, authentication.getName());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/comment")
	public ResponseEntity<List<Comment>> getComments() {
		List<Comment> comments = commentService.getComments();
		return ResponseEntity.ok().body(comments);
	}
}
