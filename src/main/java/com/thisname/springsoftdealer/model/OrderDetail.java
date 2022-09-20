package com.thisname.springsoftdealer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private double lot;
    @Getter @Setter
    private  double price;
    @Getter @Setter
    private double total;

    @ManyToOne
    @Getter @Setter
    private Order order;

    @ManyToOne
    @Getter @Setter
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, String name, double lot, double price, double total) {
        this.id = id;
        this.name = name;
        this.lot = lot;
        this.price = price;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lot=" + lot +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
