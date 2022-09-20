package com.thisname.springsoftdealer.repository;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUser (User user);
}
