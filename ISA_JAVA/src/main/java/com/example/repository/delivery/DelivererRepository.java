package com.example.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.deliveryBeans.DelivererBean;

@Repository
public interface DelivererRepository extends JpaRepository<DelivererBean, Long>{

}
