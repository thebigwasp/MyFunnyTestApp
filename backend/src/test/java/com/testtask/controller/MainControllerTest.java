package com.testtask.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.dto.CommentDto;
import com.testtask.dto.UsernameDto;
import com.testtask.model.Comment;
import com.testtask.service.CommentService;

@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

	@MockBean
	private CommentService commentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@WithMockUser
	public void authenticate() throws Exception {
		String username = "abcdef";
		UsernameDto usernameDto = new UsernameDto();
		usernameDto.setUsername(username);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/authenticate")
					.with(SecurityMockMvcRequestPostProcessors.csrf())
					.content(objectMapper.writeValueAsBytes(usernameDto))
					.contentType(MediaType.APPLICATION_JSON)
				)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.cookie().exists("Auth"))
			.andExpect(MockMvcResultMatchers.cookie().value("Auth", username));
	}
	
	@Test
	@WithMockUser("Vasya")
	public void postComment() throws Exception {
		String text = "1st comment";
		CommentDto commentDto = new CommentDto();
		commentDto.setText(text);
		
		Mockito.doNothing().when(commentService).addComment(ArgumentMatchers.any(), ArgumentMatchers.any());
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/comment")
					.with(SecurityMockMvcRequestPostProcessors.csrf())
					.content(objectMapper.writeValueAsBytes(commentDto))
					.contentType(MediaType.APPLICATION_JSON)
				)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getComments() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		Comment comment = new Comment();
		comment.setId("1");
		comment.setText("1st comment");
		comment.setAuthor("Vasya");
		comment.setCreatedAt(now);
		List<Comment> comments = new ArrayList<>();
		comments.add(comment);
		
		String expected = objectMapper.writeValueAsString(comments);
		
		Mockito.when(commentService.getComments()).thenReturn(comments);
		
		mockMvc.perform(
					MockMvcRequestBuilders.get("/comment")
				)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(expected));
	}
}
