package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.OrderDetail;
import com.thisname.springsoftdealer.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImp implements IOrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
