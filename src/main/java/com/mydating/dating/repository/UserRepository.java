package com.mydating.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mydating.dating.entity.User;
import com.mydating.dating.util.USerGender;

public interface UserRepository  extends JpaRepository<User,Integer>{

	List<User> findByGender(USerGender male);
	@Query("select u from User u where u.name like ?1")
	List<User> searchByName(String letters);

}
