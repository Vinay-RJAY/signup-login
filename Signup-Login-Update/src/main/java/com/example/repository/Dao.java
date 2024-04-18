package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.User;

@Repository
public interface Dao extends JpaRepository<User, Integer> {

	User findByEmailid(String emailid);

	User findByEmailidAndPassword(String emailid, String password);

}
