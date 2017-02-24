package com.example.service;

import java.util.Collection;

import com.example.domain.ratings.ReviewBean;

public interface ReviewService {

	ReviewBean findOne(Long id);
	
	Collection<ReviewBean> findAll();
	
	ReviewBean update(ReviewBean rev);
	
	ReviewBean create(ReviewBean rev);
	
	void delete(Long id);
	
	//custom
	/**get all restaurant grades*/
	public Collection<ReviewBean> getAllRestaurantGrades(Long rest_id);
	
	/**get all product grades from a specific restaurant*/
	public Collection<ReviewBean> getAllGradesForAProductInARestaurant(Long rest_id, Long product_id);

	
	/**get employee grades*/
	public Collection<ReviewBean> getAllGradesForAnEmployee(Long emp_id);
}
