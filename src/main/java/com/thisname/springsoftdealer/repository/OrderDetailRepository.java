package com.thisname.springsoftdealer.repository;

import com.thisname.springsoftdealer.model.OrderDetail;
import com.thisname.springsoftdealer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {


}
