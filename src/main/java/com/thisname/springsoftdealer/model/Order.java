package com.thisname.springsoftdealer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Date creationDate;
    @Getter @Setter
    private Date receivedDate;
    @Getter @Setter
    private double total;


    @ManyToOne
    @Getter @Setter
    private User user;

    @OneToMany(mappedBy = "order")
    @Getter @Setter
    private List<OrderDetail> orderDetail;

    public Order() {
    }

    public Order(Integer id, String name, Date creationDate, Date receivedDate, double total) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.receivedDate = receivedDate;
        this.total = total;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", receivedDate=" + receivedDate +
                ", total=" + total +
                '}';
    }
}
