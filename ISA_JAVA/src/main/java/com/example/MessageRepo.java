package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<SimpleMessage, Long>{

	@Query(value="select * from message where id = :id",nativeQuery=true)
	public SimpleMessage getMessageWithSomeId(@Param("id") Long id);
}
