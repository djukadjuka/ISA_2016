package com.example.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.orderBeans.RestaurantOrderItem;


@Repository
public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Long> {

}
