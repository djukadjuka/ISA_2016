package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.UserBean;

public interface UserRepository extends JpaRepository<UserBean, Long>{

}
