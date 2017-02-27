package com.example.service;

import java.util.Collection;

import com.example.domain.FriendshipBean;

public interface FriendshipService {

	FriendshipBean findOne(Long id);
	
	Collection<FriendshipBean> findByRecipient_id(Long recipient_id);
	
	Collection<FriendshipBean> findByRecipient_idAndStatusPending(Long recipient_id);
	
	Collection<FriendshipBean> findByRecipient_idOrOriginator_idAndStatusAccepted(Long id);
	
	Collection<FriendshipBean> findByOriginator_id(Long originator_id);
	
	FriendshipBean findByRecipientOrOriginatorCombination(Long orig, Long rec);
	
	Collection<FriendshipBean> findAll();
	
	FriendshipBean update(FriendshipBean friendship);
	
	FriendshipBean create(FriendshipBean friendship);
	
	void delete(Long id);
	
}
