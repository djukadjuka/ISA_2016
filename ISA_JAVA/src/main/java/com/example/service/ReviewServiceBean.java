package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ratings.ReviewBean;
import com.example.repository.ReviewRepository;

@Service
public class ReviewServiceBean implements ReviewService{

	@Autowired
	private ReviewRepository repository;
	
	@Override
	public ReviewBean findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<ReviewBean> findAll() {
		return this.repository.findAll();
	}

	@Override
	public ReviewBean update(ReviewBean rev) {
		return this.repository.save(rev);
	}

	@Override
	public ReviewBean create(ReviewBean rev) {
		return this.repository.save(rev);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Collection<ReviewBean> getAllRestaurantGrades(Long rest_id) {
		return this.repository.getAllRestaurantGrades(rest_id);
	}

	@Override
	public Collection<ReviewBean> getAllGradesForAProductInARestaurant(Long rest_id, Long product_id) {
		return this.repository.getAllGradesForAProductInARestaurant(rest_id, product_id);
	}

	@Override
	public Collection<ReviewBean> getAllGradesForAnEmployee(Long emp_id) {
		return this.repository.getAllGradesForAnEmployee(emp_id);
	}

}
