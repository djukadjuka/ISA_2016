package com.example.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.RestaurantRegistry;

@Repository
public interface RestaurantRegistryRepository extends JpaRepository<RestaurantRegistry, Long>{
	
	/**
	 * Svi restorani koje je odredjeni menadzer pokusao da registruje
	 * a nije video status registracije
	 * */
	@Query(value = ""
		+ "select * from restaurant_registry reg where"
		+ " reg.seen = 0 and reg.registering_by = :manager_id", nativeQuery = true)
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
	@Transactional
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
	@Transactional
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
	@Transactional
	@Modifying
	@Query(value=""
			+ "UPDATE restaurant_Registry "
			+ "SET status='DECLINED' "
			+ "WHERE id = :rest_id", nativeQuery = true)
	public int updateRestaurantStatus_DECLINED(@Param("rest_id") Long rest_id);
	
	/**
	 * Pravi novu vezu
	 * */
	@Transactional
	@Modifying
	@Query(value=""
			+ "INSERT INTO registering_restaurants (rest_id, manager_id)"
			+  "VALUES(:rest_id,:mgr_id)",nativeQuery = true)
	public void addNewRelationsLOL(@Param("rest_id") Long rest_id, @Param("mgr_id") Long mgr_id);
	
}
