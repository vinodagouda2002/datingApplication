package com.mydating.dating.dto;

import java.util.List;

import com.mydating.dating.util.USerGender;

import lombok.Data;

@Data
public class MAtchingUser {

	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private USerGender gender;
	private List<String> interests;
	private int mic;
	private int ageDiff;
}
