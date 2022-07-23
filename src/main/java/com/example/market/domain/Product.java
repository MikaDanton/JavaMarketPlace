package com.example.market.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer price;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientProduct> clientProductList = new ArrayList<>();

    public Product(){

    }
    public Product(String name, Integer price){
        this.name = name;
        this.price = price;
    }

    public void addClientProduct(ClientProduct clientProduct){
        clientProductList.add(clientProduct);
        clientProduct.setProduct(this);
    }
    public void removeClientProduct(ClientProduct clientProduct){
        clientProductList.remove(clientProduct);
        clientProduct.setProduct(null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
