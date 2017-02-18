package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.UserBean;

public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	@Query(value="SELECT *"
			+ "FROM user u"	
			+ "WHERE u.id NOT IN" 
			+ "(SELECT e.user_id" 
			+ "FROM employee e"
			+ "WHERE e.role != 'MANAGER')",nativeQuery=true)
	public Collection<UserBean> getNOTNOTMANAGERUsers();
	
}
