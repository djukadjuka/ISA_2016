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
	
	@Query(value = "SELECT * FROM Friendship f WHERE f.recipient_id = :rec_id and f.status = \'PENDING\'", nativeQuery = true)
    public Collection<FriendshipBean> findByRecipient_idAndStatusPending(@Param("rec_id") Long recipient_id);
	
	@Query(value = "SELECT * FROM Friendship f WHERE (f.recipient_id = :id or f.originator_id = :id) and f.status = \'ACCEPTED\'", nativeQuery = true)
    public Collection<FriendshipBean> 	findByRecipient_idOrOriginator_idAndStatusAccepted(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM Friendship f WHERE f.originator_id = :ori_id", nativeQuery = true)
    public Collection<FriendshipBean> findByOriginator_id(@Param("ori_id") Long originator_id);

}
