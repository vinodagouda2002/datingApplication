package com.mydating.dating.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mydating.dating.entity.User;
import com.mydating.dating.repository.UserRepository;
import com.mydating.dating.util.USerGender;

@Repository
public class USerDao {

	@Autowired
	UserRepository userRepository;

	public User saveUsers(User user) {
		return userRepository.save(user);
	}

	public List<User> usersall() {
		return userRepository.findAll();
	}

	public List<User> findAllMaleUsers() {
		return userRepository.findByGender(USerGender.MALE);
	}

	public List<User> findAllFemaleUsers() {
		return userRepository.findByGender(USerGender.FEMALE);
	}

	public Optional<User> findUserById(int id, int top) {
		return userRepository.findById(id);
	}	
	
	
}
	


	
