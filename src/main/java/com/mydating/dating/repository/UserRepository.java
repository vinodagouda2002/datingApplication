package com.mydating.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mydating.dating.entity.User;
import com.mydating.dating.util.USerGender;

public interface UserRepository  extends JpaRepository<User,Integer>{

	List<User> findByGender(USerGender male);

}
