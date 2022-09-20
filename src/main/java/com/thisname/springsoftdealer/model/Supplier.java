package com.thisname.springsoftdealer.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String address;
    @OneToMany(mappedBy = "supplier")
    @Getter @Setter
    private List<Product> products;



    public Supplier() {
    }

    public Supplier(Integer id, String name, String email, String address, List<Product> products) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
