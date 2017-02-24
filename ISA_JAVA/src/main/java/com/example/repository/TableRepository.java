package com.example.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.TableBean;
import com.example.domain.TableBean.TableStatus;


@Repository
public interface TableRepository extends JpaRepository<TableBean, Long> {
	
	@Query(value = "SELECT * FROM Restaurant_table t WHERE t.restaurant_zone_id = :zone_id", nativeQuery = true)
    public Collection<TableBean> findAllTablesByZoneId(@Param("zone_id") Long zone_id);
	
	@Modifying
	@Transactional
    @Query(value = "UPDATE Restaurant_table t SET t.status = :status WHERE t.table_id = :id", nativeQuery = true)
    int updateTableStatus(@Param("id") Long id, @Param("status") String status);

}
