package com.example.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.deliveryBeans.DelivererBean;

@Repository
public interface DelivererRepository extends JpaRepository<DelivererBean, Long>{

	@Transactional
	@Modifying
	@Query(value="update deliverer set request_status='ACCEPTED', set first_login = null  where user_id = :user_id",nativeQuery=true)
	public void deliverer_accepted(@Param("user_id") Long user_id);
	
	@Transactional
	@Modifying
	@Query(value="insert into deliverer(request_status,user_id) values ('PENDING',:user_id)",nativeQuery=true)
	public void user_wants_to_be_deliverer(@Param("user_id") Long user_id);
	
	@Transactional
	@Modifying
	@Query(value = "update deliverer set first_login = 1 where user_id = :user_id",nativeQuery=true)
	public void deliverer_changed_password(@Param("user_id") Long user_id);
}
