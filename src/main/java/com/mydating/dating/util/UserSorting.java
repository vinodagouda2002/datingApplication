package com.mydating.dating.util;

import java.util.Comparator;

import com.mydating.dating.dto.MAtchingUser;

public class UserSorting implements Comparator<MAtchingUser> {

	
	@Override
	public int compare(MAtchingUser o1,MAtchingUser o2) {
		
		if(o1.getAgeDiff()<o2.getAgeDiff()) {
			return -1;
		}
		else if(o1.getAge()>o2.getAgeDiff()) {
			return 1;
		}
		else if(o1.getMic()<o2.getMic()) {
		
			if(o1.getMic()<o2.getMic()) {
				return 1;
			}else if (o1.getMic()>o2.getMic()) {
				return -1;
			}
		}
		return 0;
	}
}
