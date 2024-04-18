package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Model.User;
import com.example.loginDTO.LoginDTO;
import com.example.repository.Dao;
import com.example.response.CommonApiResponse;
import com.example.response.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServicesImpl implements Services {

	@Autowired
	private Dao d;

	@Override
	public ResponseEntity<CommonApiResponse> register(User user) {

		log.info("Request Received for Registration");

		CommonApiResponse cr = new CommonApiResponse();

		User byEmailId = d.findByEmailid(user.getEmailid());

		if (byEmailId != null) {
			log.info("Email Already Registered, Please Login");
			cr.setResponse("Email Already Registered, Please Register with new EmailId");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);

		} else if (user.getEmailid() == null || user.getPassword() == null || user.getName() == null) {
			log.info("Something Went Wrong");
			cr.setResponse("Fill all Feilds");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);
		} else {
			log.info("Registration Successfull...");
			System.out.println(d.save(user));
			cr.setResponse("Registration Successfull...");
			cr.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<CommonApiResponse> login(LoginDTO s) {

		CommonApiResponse cr = new CommonApiResponse();

		log.info("Request Received for Login");

		User byEmailidAndPassword = d.findByEmailidAndPassword(s.getEmailid(), s.getPassword());

		if (byEmailidAndPassword == null) {
			log.info("Invalid Email or Password");
			cr.setResponse("Invalid Email or Password");
			cr.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.NOT_ACCEPTABLE);

		} else {
			log.info("Login Success");
			cr.setResponse("Login Success");
			cr.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(cr, HttpStatus.ACCEPTED);

		}

	}

	@Override
	public ResponseEntity<UserResponse> getAllUserDetails() {

		log.info("Request Received to get all Details");

		UserResponse cr = new UserResponse();

		List<User> all = d.findAll();

		if (all.size() == 0) {
			cr.setResponse("No Users Avaible");
			cr.setResult("Failed");
			return new ResponseEntity<UserResponse>(cr, HttpStatus.BAD_GATEWAY);
		} else {
			cr.setList(all);
			cr.setResponse("Details of Users");
			cr.setResult("Success");
			return new ResponseEntity<UserResponse>(cr, HttpStatus.ACCEPTED);
		}
	}

	public ResponseEntity<UserResponse> getById(int id) {
		UserResponse ur = new UserResponse();
		User user = d.findById(id).get();
		if (id == 0) {
			ur.setResponse("Inputs Missing");
			ur.setResult("Failed");
			return new ResponseEntity<UserResponse>(ur, HttpStatus.BAD_GATEWAY);

		} else if (user == null) {
			ur.setResponse("No Users Avaible");
			ur.setResult("Failed");
			return new ResponseEntity<UserResponse>(ur, HttpStatus.BAD_GATEWAY);
		} else {
			ur.setUser(user);
			ur.setResponse("Details of User By Given Id");
			ur.setResult("Success");
			return new ResponseEntity<UserResponse>(ur, HttpStatus.ACCEPTED);

		}
	}

	@Override
	public ResponseEntity<CommonApiResponse> update(User user) {
		log.info("Request Received to update User");
		CommonApiResponse ur = new CommonApiResponse();
		int id = user.getId();

		/*
		 * User user2 = d.findById(id).get(); System.out.println(user2);
		 */
		Optional<User> byId = d.findById(user.getId());

		if (id == 0) {
			log.info("Missed id");
			ur.setResponse("Enter Id to update Record");
			ur.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.BAD_REQUEST);
		} else if (byId.isPresent()) {
			d.save(user);
			log.info("User Details Updated");
			ur.setResponse("User Details Updated ");
			ur.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.ACCEPTED);
		} else {

			ur.setResponse("Record Not Avaible");
			ur.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.ACCEPTED);

		}

	}

	@Override
	public ResponseEntity<CommonApiResponse> deleteById(int id) {
		CommonApiResponse ur = new CommonApiResponse();

		Optional<User> byId = d.findById(id);

		if (id == 0) {
			log.info("Missed id");
			ur.setResponse("Enter Id to Delete Record");
			ur.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.BAD_REQUEST);
		} else if (byId.isPresent()) {
			d.deleteById(id);
			log.info("User Deleted");
			ur.setResponse("User Deleted ");
			ur.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.ACCEPTED);
		} else {

			ur.setResponse("Record Not Avaible");
			ur.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.ACCEPTED);

		}

	}
    
	@Override
	public ResponseEntity<CommonApiResponse> deleteAllUsers() {
		CommonApiResponse ur = new CommonApiResponse();
		List<User> all = d.findAll();
		if (all.size() == 0) {
			log.info("Database Is Empty");
			ur.setResponse("Database Is Empty");
			ur.setResult("Failed");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.BAD_REQUEST);
		} else {
			d.deleteAll();
			ur.setResponse("Deleted All Users");
			ur.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(ur, HttpStatus.ACCEPTED);

		}
	}
}
