package com.jinu.interview.dto.request;

import java.util.List;

import lombok.Data;
@Data
public class AddUserRequest {
	private String username;
	private String password;
	private String email;
	private List<String> roles;
}
