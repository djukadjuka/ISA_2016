package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.RestaurantBean;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantBean, Long> {
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.type = :type", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByType(@Param("type") String type);
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%') and r.type = :type", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByNameAndType(@Param("name") String name, @Param("type") String type);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO manages_restaurants(manager_id,rest_id) values(:man_id,:rest_id)",nativeQuery=true)
	public void createManagerRelation(@Param("man_id") Long man_id, @Param("rest_id") Long rest_id);
	
	@Transactional
	@Modifying
	@Query(value="update restaurant r set r.name = :name where r.id = :id",nativeQuery=true)
	public void updateRestaurantName_FIX(@Param("name") String name, @Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value="update restaurant r set r.type = :type where r.id = :id",nativeQuery=true)
	public void updateRestaurantType_FIX(@Param("type") String type, @Param("id") Long id);
	
	@Query(value="select r.* from restaurant_serves_cuisines rsc, restaurant r, restaurant_food_type rft"
			+ " where where r.id = :rest_id and rft.id = :food_id and rft.id = rsc.type_id and rsc.rest_id = r.id",nativeQuery=true)
	public RestaurantBean getExistingFoodType(@Param("rest_id") Long rest_id, @Param("food_id") Long food_id);
	
	@Transactional
	@Modifying
	@Query(value="insert into restaurant_serves_cuisines(rest_id,type_id) values(:rest_id,:type_id)",nativeQuery=true)
	public void insert_new_food_type(@Param("rest_id") Long rest_id, @Param("type_id") Long type_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from restaurant_serves_cuisines where rest_id = :rest_id and type_id = :type_id",nativeQuery=true)
	public void delete_food_type(@Param("rest_id") Long rest_id, @Param("type_id") Long type_id);
	
	@Transactional
	@Modifying
	@Query(value = "insert into restaurant_drinks_menu(rest_id,drink_id) values(:rest_id,:drink_id)",nativeQuery=true)
	public void inset_drink_item(@Param("rest_id") Long rest_id, @Param("drink_id") Long drink_id);
	@Transactional
	@Modifying
	@Query(value = "insert into restaurant_food_menu(rest_id,food_id) values(:rest_id,:food_id)",nativeQuery=true)
	public void inset_food_item(@Param("rest_id") Long rest_id, @Param("food_id") Long food_id);
	@Transactional
	@Modifying
	@Query(value = "delete from restaurant_drinks_menu where rest_id = :rest_id and drink_id = :drink_id",nativeQuery=true)
	public void delete_drink_item(@Param("rest_id") Long rest_id, @Param("drink_id") Long drink_id);
	@Transactional
	@Modifying
	@Query(value = "delete from restaurant_food_menu where rest_id = :rest_id and food_id = :food_id",nativeQuery=true)
	public void delete_food_item(@Param("rest_id") Long rest_id, @Param("food_id") Long food_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from restaurant_zone where restaurant_id = :rest_id and id = :zone_id",nativeQuery=true)
	public void delete_zone_from_restaurant(@Param("rest_id") Long rest_id, @Param("zone_id") Long zone_id);
	@Transactional
	@Modifying
	@Query(value="delete from restaurant_table where restaurant_zone_id = :zone_id",nativeQuery=true)
	public void delete_tables_from_zone(@Param("zone_id") Long zone_id);
	
	@Transactional
	@Modifying
	@Query(value="update restaurant set lat = :lat, lng = :lng where id = :id",nativeQuery=true)
	public void update_restaurant_coords(@Param("lat") Double lat, @Param("lng") Double lng, @Param("id") Long id);
	
}
