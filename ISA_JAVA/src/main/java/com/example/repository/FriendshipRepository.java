package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.FriendshipBean;
import com.example.domain.UserBean;

public interface FriendshipRepository extends JpaRepository<FriendshipBean, Long>{
	
	@Query(value = "SELECT * FROM Friendship f WHERE f.recipient_id = :rec_id", nativeQuery = true)
    public Collection<FriendshipBean> findByRecipient_id(@Param("rec_id") Long recipient_id);

}
