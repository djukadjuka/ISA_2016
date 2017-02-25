package com.example.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.ProductBean;
import com.example.domain.ReservationCallBean;

public interface ReservationCallRepository extends JpaRepository<ReservationCallBean, Long>  {
	
	@Query(value = "SELECT * FROM Reservation_call r WHERE r.originator_id = :orig_id and r.recipient_id = :orig_id", nativeQuery = true)
    public Collection<ReservationCallBean> findByOriginatorOriginator(@Param("orig_id") Long originator);
	
	@Query(value = "SELECT * FROM Reservation_call r WHERE r.recipient_id = :rec_id and r.status = \'ACCEPTED\'", nativeQuery = true)
    public Collection<ReservationCallBean> findByRecipient(@Param("rec_id") Long recipient);
	
	@Query(value = "SELECT * FROM Reservation_call r WHERE r.keygen = :keygen", nativeQuery = true)
    public ReservationCallBean findByKeygenAndId(@Param("keygen") Long keygen);
	
	@Modifying
	@Transactional
	@Query(value = 
	  		"UPDATE Reservation_call " 
			+ "SET status = :status "
			+ "WHERE id = :call_id", nativeQuery = true)
    public int updateStatus(@Param("status") String status, @Param("call_id") Long call_id);
	
	@Modifying
	@Transactional
	@Query(value = 
	  		"UPDATE Reservation_call " 
			+ "SET food = :food, drink = :drink, make_order_fast = :makeOrderFast"
			+ " WHERE id = :id", nativeQuery = true)
	public int updateFoodAndDrink(@Param("id") Long id, @Param("food") ProductBean food, @Param("drink") ProductBean drink, @Param("makeOrderFast") int makeOrderFast);
	
	@Modifying
	@Transactional
	@Query(value = 
	  		"UPDATE Reservation_call " 
			+ "SET food = null, drink = null, make_order_fast = 0"
			+ " WHERE id = :id", nativeQuery = true)
	public int cancelFoodAndDrink(@Param("id") Long id);
}
