package com.thisname.springsoftdealer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String image;
    @Getter @Setter
    private double price;
    @Getter @Setter
    private double priceSupplier;
    @Getter @Setter
    private int lot;
    @Getter @Setter
    private String iva;

    @ManyToOne
    @Getter @Setter
    private User user;


    @ManyToOne
    @Getter @Setter
    private Supplier supplier;

    public Product() {
    }

    public Product(Integer id, String name, String description, String image, double price, double priceSupplier, int lot, String iva, User user, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.priceSupplier = priceSupplier;
        this.lot = lot;
        this.iva = iva;
        this.user = user;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", priceSupplier=" + priceSupplier +
                ", lot=" + lot +
                ", iva='" + iva + '\'' +
                '}';
    }
}
