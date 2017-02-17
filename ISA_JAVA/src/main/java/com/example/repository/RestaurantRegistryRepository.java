package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeBean;
import com.example.domain.RestaurantRegistry;

@Repository
public interface RestaurantRegistryRepository extends JpaRepository<RestaurantRegistry, Long>{
	
	/**
	 * Svi restorani koje je odredjeni menadzer pokusao da registruje
	 * a nije video status registracije
	 * */
	@Query(value = ""
		+ "SELECT regs.id,regs.restaurant_name,regs.seen,regs.status,regs.type,regs.deleted "
		+ "FROM restaurant_registry regs, registering_restaurants rr "
		+ "WHERE rr.rest_id = regs.id "
		+ "AND rr.manager_id = :manager_id "
		+ "AND regs.seen = 0", nativeQuery = true)
	public Collection<RestaurantRegistry> getRegistersByManagerId(@Param("manager_id")Long manager_id);
	
	/**
	 * Za admina
	 * Svi restorani za koje nije odluceno da li su
	 * prihvaceni ili ne
	 * */
	@Query(value =""
		+ "SELECT * from restaurant_registry "
		+ "WHERE status = 'PENDING'", nativeQuery = true)
	public Collection<RestaurantRegistry> getNotDecidedRestaurants();
	
	/**
	 * UPDATE
	 * Menadzer je video da je njegov restoran odbijen/prihvacen
	 * */
	@Modifying
	@Query(value=""
			+ "UPDATE restaurant_registry "
			+ "SET seen = 1 "
			+ "WHERE id = :rest_id", nativeQuery = true)
	public int updateRestaurantSeen(@Param("rest_id") Long rest_id);
	
	
	/**
	 * ADMIN UPDATE
	 * Prihvatio registraciju restorana
	 * */
	@Modifying
	@Query(value=""
			+ "UPDATE restaurant_Registry "
			+ "SET status='ACCEPTED' "
			+ "WHERE id = :rest_id", nativeQuery = true)
	public int updateRestaurantStatus_ACCEPTED(@Param("rest_id") Long rest_id);
	
	/**
	 * ADMIN UPDATE
	 * ODBIO registraciju restorana
	 * */
	@Modifying
	@Query(value=""
			+ "UPDATE restaurant_Registry "
			+ "SET status='DECLINED' "
			+ "WHERE id = :rest_id", nativeQuery = true)
	public int updateRestaurantStatus_DECLINED(@Param("rest_id") Long rest_id);
	
	
}
