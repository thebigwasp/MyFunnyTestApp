package com.testtask.dto;

import javax.validation.constraints.NotEmpty;

public class CommentDto {
	
	@NotEmpty
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
