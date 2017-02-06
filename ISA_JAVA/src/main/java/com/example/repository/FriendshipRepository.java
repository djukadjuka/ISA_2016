package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.FriendshipBean;

public interface FriendshipRepository extends JpaRepository<FriendshipBean, Long>{

}
