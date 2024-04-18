package com.example.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserResponse extends CommonApiResponse {

	private List<User> list = new ArrayList<>();
	
	private User user;
}
