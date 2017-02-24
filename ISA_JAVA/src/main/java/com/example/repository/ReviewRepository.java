package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.ratings.ReviewBean;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewBean, Long>{

	/**get all restaurant grades*/
	@Query(value="select rev.* from review rev"
			+ " where rev.for_restaurant = :rest_id"
			+ " and rev.for_product is null",nativeQuery=true)
	public Collection<ReviewBean> getAllRestaurantGrades(@Param("rest_id") Long rest_id);
	
	/**get all product grades from a specific restaurant*/
	@Query(value="select rev.* from review rev"
			+ " where rev.for_product = :product_id"
			+ " and rev.for_restaurant = :rest_id",nativeQuery=true)
	public Collection<ReviewBean> getAllGradesForAProductInARestaurant(@Param("rest_id") Long rest_id, @Param("product_id") Long product_id);

	
	/**get employee grades*/
	@Query(value="select rev.* from review rev"
			+ " where rev.for_employee = :emp_id",nativeQuery=true)
	public Collection<ReviewBean> getAllGradesForAnEmployee(@Param("emp_id") Long emp_id);
	
}
