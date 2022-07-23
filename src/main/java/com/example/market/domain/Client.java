package com.example.market.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Integer amountOfMoney;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientProduct> clientProductList = new ArrayList<>();

    public Client(){

    }
    public Client(String firstName, String lastName, Integer amountOfMoney){
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
    }

    public void addClientProduct(ClientProduct clientProduct){
        clientProductList.add(clientProduct);
        clientProduct.setClient(this);
    }
    public void removeClientProduct(ClientProduct clientProduct){
        clientProductList.remove(clientProduct);
        clientProduct.setClient(null);
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
