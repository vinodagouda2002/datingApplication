package com.mydating.dating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mydating.dating.dao.USerDao;
import com.mydating.dating.dto.MAtchingUser;
import com.mydating.dating.entity.User;
import com.mydating.dating.util.USerGender;
import com.mydating.dating.util.UserSorting;

@Service
public class UserService {


	@Autowired
	USerDao userDao;
	
    
    //Saving 
	public ResponseEntity<?> saveUsers(User user) {
		User saveUsers = userDao.saveUsers(user);
		return ResponseEntity.status(200).body(saveUsers);
	}

	//FindAll users
	public ResponseEntity<?> usersall() {
		List<User> usersall = userDao.usersall();
		return ResponseEntity.status(200).body(usersall);
	}

	//Male Users
	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers = userDao.findAllMaleUsers();
		if(maleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No male Users Present in this base table");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
		}
	}

	//Female Users
	public ResponseEntity<?> findAllFemaleUsers() {
		
		List<User> femaleUsers = userDao.findAllFemaleUsers();
		
		if(femaleUsers.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Female users are present in this base table");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(femaleUsers);
		}
	}

	
	//finding opposite gender
	public ResponseEntity<?> findBestMatch(int id, int top) {	
		
		Optional<User> optional = userDao.findUserById(id,top);
		
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id Unable to find Match");
		}
		
		User user = optional.get();
		List<User> users = null;
		
		if(user.getGender().equals(USerGender.MALE)) {	
			users = userDao.findAllFemaleUsers();
		}
		else {
			users = userDao.findAllMaleUsers();
		}
		
		List<MAtchingUser> matchingUsers = new ArrayList<>();
		
//		for(User u :users)
//			System.out.println(u);
		
		
		for(User u :users) {
			MAtchingUser mu = new MAtchingUser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setAge(u.getAge());
			mu.setInterests(u.getInterests());
			mu.setGender(u.getGender());
			mu.setAgeDiff(Math.abs(user.getAge() -u.getAge()));
			
			List<String> interests1 = user.getInterests();
			List<String> interests2 = u.getInterests();
			
			int mic=0;
			
			for(String s : interests1) {
				if(interests2.contains(s))
					mic++;
			}
		mu.setMic(mic);
		matchingUsers.add(mu);
		}
	
		Comparator<MAtchingUser> c = new UserSorting();
		Collections.sort(matchingUsers,c);
		
//		for(MAtchingUser mu : matchingUsers )
//			System.out.println(mu);
		
		
		List<MAtchingUser> result = new ArrayList<>();
		
		for(MAtchingUser mu : matchingUsers ) {
			
			if(top==0) {
				break;
			}else {
				result.add(mu);
				top--;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
