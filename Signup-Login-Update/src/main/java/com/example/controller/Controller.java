package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.User;
import com.example.loginDTO.LoginDTO;
import com.example.response.CommonApiResponse;
import com.example.response.UserResponse;
import com.example.service.Services;

@RestController
public class Controller {

	@Autowired
	private Services s;

	@PostMapping(value = "/signUp")
	public ResponseEntity<CommonApiResponse> registerSeller(@RequestBody User user) {
		return s.register(user);
	}

	@GetMapping(value = "/login")
	public ResponseEntity<CommonApiResponse> login(@RequestBody LoginDTO ldto) {
		return s.login(ldto);
	}

	@PutMapping("/update")
	public ResponseEntity<CommonApiResponse> update(@RequestBody User user) {
		return s.update(user);
	}

	@GetMapping("/getAllUserDetails")
	public ResponseEntity<UserResponse> getUsersDetails() {

		return s.getAllUserDetails();
	}

	
	@GetMapping("/getById")
	public ResponseEntity<UserResponse> getById(@RequestParam("id") int id) {
		return s.getById(id);
	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<CommonApiResponse> deleteById(@RequestParam("id") int id) {
		return s.deleteById(id);
	}
	
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<CommonApiResponse> deleteAllUsers() {
		return s.deleteAllUsers();

	}
	
	

}
