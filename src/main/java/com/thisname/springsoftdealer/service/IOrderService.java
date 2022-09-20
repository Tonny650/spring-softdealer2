package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IOrderService {

    List<Order> fiadAll();
    Order save (Order order);
    String generateOrderNumber();
    List<Order> findByUser (User user);
    Optional<Order> findById(Integer id);

}
