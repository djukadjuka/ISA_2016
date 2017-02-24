package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.BillBean;

@Repository
public interface BillRepository extends JpaRepository<BillBean, Long>{

	/**Get all bills for a restaurant*/
	@Query(value="select b.* from bill b"
			+ " where b.restaurant_id = :rest_id",nativeQuery=true)
	public Collection<BillBean> getAllBillsForARestaurant(@Param("rest_id") Long rest_id);
	
	/**Get all bills for an employee*/
	@Query(value="select b.* from bill b"
			+ " where b.employee_user_id = :emp_id",nativeQuery=true)
	public Collection<BillBean> getAllBillsForAnEmployee(@Param("emp_id") Long emp_id);
	
	/**get all bills for a restaurant in a time period*/
	@Query(value="select b.* from bill b"
			+ " where b.date_of_transaction <= :date_to"
			+ " and b.date_of_transaction >= :date_from"
			+ " and b.restaurant_id = :rest_id",nativeQuery=true)
	public Collection<BillBean> getAllBillsRestaurantTimePeriod(@Param("rest_id") Long rest_id,
																@Param("date_from") Long date_from,
																@Param("date_to") Long date_to);
	
}
