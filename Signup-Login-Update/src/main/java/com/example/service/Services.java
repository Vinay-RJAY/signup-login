package com.example.service;

import org.springframework.http.ResponseEntity;

import com.example.Model.User;
import com.example.loginDTO.LoginDTO;
import com.example.response.CommonApiResponse;
import com.example.response.UserResponse;

public interface Services {

	 ResponseEntity<CommonApiResponse> register(User user);

	ResponseEntity<CommonApiResponse> login(LoginDTO ldto);

	ResponseEntity<UserResponse> getAllUserDetails();

	ResponseEntity<UserResponse> getById(int id);

	ResponseEntity<CommonApiResponse> update(User user);

	ResponseEntity<CommonApiResponse> deleteById(int id);

	ResponseEntity<CommonApiResponse> deleteAllUsers();

	

}
