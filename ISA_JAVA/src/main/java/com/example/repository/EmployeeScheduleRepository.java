package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeScheduleBean;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeScheduleBean,Long>{

	@Query(value="SELECT * FROM employee_schedule e WHERE e.for_employee = :employee_id",nativeQuery=true)
	public Collection<EmployeeScheduleBean> getScheddzzzForEmployee(@Param("employee_id") Long employee_id);
}
