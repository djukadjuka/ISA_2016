package com.example.service;

import java.util.Collection;

import com.example.domain.FriendshipBean;

public interface FriendshipService {

	FriendshipBean findOne(Long id);
	
	Collection<FriendshipBean> findAll();
	
	FriendshipBean update(FriendshipBean friendship);
	
	FriendshipBean create(FriendshipBean friendship);
	
	void delete(Long id);
	
}
