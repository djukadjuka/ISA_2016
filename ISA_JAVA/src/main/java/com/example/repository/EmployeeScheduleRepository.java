package com.example.repository;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeScheduleBean;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeScheduleBean,Long>{

	@Query(value="SELECT * FROM employee_schedule e WHERE e.for_employee = :employee_id",nativeQuery=true)
	public Collection<EmployeeScheduleBean> getScheddzzzForEmployee(@Param("employee_id") Long employee_id);
	
	@Query(value="SELECT * FROM employee_schedule es WHERE es.for_employee IN ( SELECT e.user_id FROM employee e WHERE e.role = 'WAITER') and es.date_date = :date",nativeQuery=true)
	public Collection<EmployeeScheduleBean> getSchedduleForEmployee(@Param("date") Long date);
	
	@Query(value="SELECT * FROM employee_schedule es WHERE es.for_employee IN ( SELECT e.user_id FROM employee e WHERE e.role = 'COOK') and es.date_date = :date",nativeQuery=true)
	public Collection<EmployeeScheduleBean> getSchedduleForCookEmployee(@Param("date") Long date);
	
	@Query(value="SELECT * FROM employee_schedule es WHERE es.for_employee IN ( SELECT e.user_id FROM employee e WHERE e.role = 'BARTENDER') and es.date_date = :date",nativeQuery=true)
	public Collection<EmployeeScheduleBean> getSchedduleForBarmanEmployee(@Param("date") Long date);
}

