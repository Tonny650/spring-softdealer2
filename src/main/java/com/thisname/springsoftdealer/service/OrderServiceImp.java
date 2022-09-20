package com.thisname.springsoftdealer.service;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> fiadAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }


    public String generateOrderNumber(){
        int nomber = 0;
        String concatenateNomber="";

        List<Order> orders = fiadAll();
        List<Integer> nombersList = new ArrayList<Integer>();

        orders.stream().forEach(o -> nombersList.add(Integer.parseInt(o.getName())));

        if (orders.isEmpty()){
            nomber=1;
        }else {
            nomber= nombersList.stream().max(Integer::compare).get();
            nomber++;
        }

        if (nomber<10){
            concatenateNomber = "000000000"+String.valueOf(nomber);
        }else if (nomber<100){
            concatenateNomber = "00000000"+String.valueOf(nomber);
        }else if (nomber<1000){
            concatenateNomber = "0000000"+String.valueOf(nomber);
        }/*else if (nomber<10000){
            concatenateNomber = "000000"+String.valueOf(nomber);
        }else if (nomber<100000){
            concatenateNomber = "00000"+String.valueOf(nomber);
        }else if (nomber<1000000){
            concatenateNomber = "0000"+String.valueOf(nomber);
        }else if (nomber<10000000){
            concatenateNomber = "000"+String.valueOf(nomber);
        }else if (nomber<100000000){
            concatenateNomber = "00"+String.valueOf(nomber);
        }else if (nomber<1000000000){
            concatenateNomber = "0"+String.valueOf(nomber);
        }*/

        return concatenateNomber;
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

}
