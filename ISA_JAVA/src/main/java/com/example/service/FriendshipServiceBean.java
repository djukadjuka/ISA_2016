package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.FriendshipBean;
import com.example.domain.UserBean;
import com.example.repository.FriendshipRepository;

@Service
public class FriendshipServiceBean implements FriendshipService{

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	@Override
	public FriendshipBean findOne(Long id) {
		// TODO Auto-generated method stub
		return friendshipRepository.findOne(id);
	}

	@Override
	public Collection<FriendshipBean> findAll() {
		// TODO Auto-generated method stub
		return friendshipRepository.findAll();
	}

	@Override
	public FriendshipBean update(FriendshipBean friendship) {
		// TODO Auto-generated method stub
		FriendshipBean f = friendshipRepository.findOne(friendship.getId());
		if(f == null)
			return null;
		return friendshipRepository.save(friendship);
	}

	@Override
	public FriendshipBean create(FriendshipBean friendship) {
		// TODO Auto-generated method stub
		if(friendship.getId() != null){
			return null;
		}
		return friendshipRepository.save(friendship);
	}

	@Override
	public void delete(Long id) {
		friendshipRepository.delete(id);
	}

}
