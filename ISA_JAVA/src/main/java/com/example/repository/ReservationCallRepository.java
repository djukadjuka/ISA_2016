package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.ReservationCallBean;

public interface ReservationCallRepository extends JpaRepository<ReservationCallBean, Long>  {
	
	@Query(value = "SELECT * FROM Reservation_call r WHERE r.originator_id = :orig_id and r.recipient_id = :orig_id", nativeQuery = true)
    public Collection<ReservationCallBean> findByOriginatorOriginator(@Param("orig_id") Long originator);

}
