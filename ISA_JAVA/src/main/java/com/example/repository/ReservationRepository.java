package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.ReservationBean;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationBean, Long> {
	
	//vraca sve one rezervacije koje SE POKLAPAJU sa proslednjenom (za taj sto), dakle vraca bilo sta ako je rezervacija neispravna, odnosno ako se poklapa sa nekom predhodnom
	@Query(value = "SELECT * FROM Reservation r WHERE r.table_id = :table_id and ((r.start_date < :startDate and r.end_date > :startDate) or (r.start_date < :endDate and r.end_date > :endDate))", nativeQuery = true)
    public Collection<ReservationBean> findReservationsMatch(@Param("startDate") Long startDate, @Param("endDate") Long endDate, @Param("table_id") Long table_id);
	
	@Query(value = "SELECT * FROM Reservation r WHERE r.table_id = :table_id", nativeQuery = true)
    public Collection<ReservationBean> findReservationsByTableId(@Param("table_id") Long table_id);
	
	@Query(value = "insert into Reservation (id, start_date, end_date, table_id) values (:res_id, :startDate, :endDate, :table_id)", nativeQuery = true)
    public Collection<ReservationBean> updateReservation(@Param("res_id") Long reservation_id, @Param("startDate") Long startDate, @Param("endDate") Long endDate, @Param("table_id") Long table_id);
}
