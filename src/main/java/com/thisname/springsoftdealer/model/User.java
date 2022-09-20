package com.thisname.springsoftdealer.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String address;
    @Getter @Setter
    private String phoneNumber;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private List<Order> orders ;

    @OneToOne
    @Getter @Setter
    private Admin admin;

    public User() {
    }

    public User(Integer id, String name, String username, String email, String address, String phoneNumber, String type, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
