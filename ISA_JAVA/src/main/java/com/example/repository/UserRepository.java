package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.UserBean;

public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	/**
	 * ############################################################
		#Select all users that are either managers or regular users
		#but filter and do not show users that are active managers of currently
		#selected restaurant
	 * */
	@Query(value=""
			+ "SELECT * FROM user u WHERE u.id NOT IN (SELECT e.user_id "
			+ " FROM employee e "
			+ " WHERE e.role != 'MANAGER') "
			+ "AND u.id NOT IN( "
			+ " SELECT u.id from user u "
			+ " where u.id IN (	 "
			+ " SELECT u.id from user u, restaurant r,manages_restaurants mr "
			+ " WHERE u.id = mr.manager_id "
			+ " AND r.id = mr.rest_id "
			+ " AND r.id = :rest_id "
			+ " )"
			+ ")AND u.id NOT IN ("
			+ " select d.user_id from deliverer d"
			+ " where d.request_status = 'ACCEPTED'"
			+ ")",nativeQuery=true)
	public Collection<UserBean> getUsersNotManagingOrNotManagersForRestaurant(@Param("rest_id") Long rest_id);

	/**
	 * #######################################
		#select all managers for a restaurant
		#without the currently logged user
	 * */
	@Query(value=""
			+ "SELECT * from user u WHERE u.id IN ( "
			+ "SELECT u.id from user u, restaurant r,manages_restaurants mr "
			+ "WHERE u.id = mr.manager_id "
			+ "AND r.id = mr.rest_id "
			+ "AND u.id != :mgr_id "
			+ "AND r.id = :rest_id)",nativeQuery=true)
	public Collection<UserBean> getManagersForRestaurantNoCurrentManager(@Param("rest_id") Long rest_id, @Param("mgr_id") Long mgr_id);

	/**
	 * get users that work for a restaurant that are not managers
	 * */
	@Query(value = "SELECT * FROM user u WHERE u.id IN ("
			+" select e.user_id FROM employee e"
			+" WHERE e.works_in_restaurant = :rest_id)",nativeQuery=true)
	public Collection<UserBean> getUsersThatWorkForARestaurant(@Param("rest_id") Long rest_id);
	
	/**
	 * get all users that do not work for a restaurant and are not managers
	 * */
	@Query(value = "SELECT * from user u WHERE"
					+" u.id NOT IN (SELECT e.user_id from employee e WHERE"
					+" e.role = 'MANAGER') AND"
					+" u.id NOT IN (SELECT e.user_id from employee e WHERE e.works_in_restaurant IS NOT NULL)"
					+ " and u.id not in("
					+ " select deliverer.user_id from deliverer where deliverer.request_status = 'ACCEPTED')",nativeQuery=true)
	public Collection<UserBean> getusersThatDoNotWorkForARestaurant();
	
	@Query(value = "SELECT * FROM user u WHERE u.id IN (select deliverer.user_id from deliverer where deliverer.request_status = 'PENDING')",nativeQuery=true)
	public Collection<UserBean> getPossibleDeliverers();
	
	/**Check if user is tied to any restaurant*/
	@Query(value = "select u.* from user u, manages_restaurants mr where u.id = :manager_id and u.id = mr.manager_id",nativeQuery=true)
	public Collection<UserBean> getTiesToRestaurantByThisManager(@Param("manager_id") Long manager_id);
	
	/**Remove manager from managing this restaurant!*/
	@Transactional
	@Modifying
	@Query(value="delete from manages_restaurants where manager_id = :manager_id and rest_id = :rest_id",nativeQuery=true)
	public void destroyManagerRestaurantTies(@Param("manager_id") Long manager_id, @Param("rest_id") Long rest_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from employee where user_id = :user_id",nativeQuery=true)
	public void fireManagerAllTogether(@Param("user_id") Long user_id);
	
	//**************** auth0 Queries
	
	@Query(value="select * from user u where u.auth_code = :auth_code",nativeQuery=true)
	public UserBean findUserByAuthCodeYo(@Param("auth_code") String auth_code);
}
