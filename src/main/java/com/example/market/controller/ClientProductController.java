package com.example.market.controller;

import com.example.market.domain.Client;
import com.example.market.domain.ClientProduct;
import com.example.market.domain.Product;
import com.example.market.repos.ClientProductRepo;

import com.example.market.repos.ClientRepo;
import com.example.market.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientProduct")
public class ClientProductController {
    @Autowired
    private ClientProductRepo clientProductRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public Iterable<ClientProduct> list(){
        Iterable<ClientProduct> clientsProducts = clientProductRepo.findAll();

        return clientsProducts;
    }
    @GetMapping("/add")
    public String add(@RequestParam Integer idClient, @RequestParam Integer idProduct){
        try {
            Optional<Client> client =  clientRepo.findById(idClient);
            Optional<Product> product =  productRepo.findById(idProduct);
            if(client.isEmpty())
                throw new Exception("Client with id " + String.valueOf(idClient) + "does not exist!");
            if(product.isEmpty())
                throw new Exception("Product with id " + String.valueOf(idClient) + "does not exist!");
            if(client.get().getAmountOfMoney() < product.get().getPrice())
                throw new Exception("Insufficient funds!");
            client.get().removeMoney(product.get().getPrice());
            ClientProduct clientProduct = new ClientProduct(client.get(), product.get());
            clientProductRepo.save(clientProduct);
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/byClientId")
    public List<ClientProduct> byClientId(@RequestParam Integer id){
        List<ClientProduct> clientProduct = clientProductRepo.findByClientId(id);
        if(clientProduct.isEmpty())
            return null;
        return clientProduct;
    }
    @GetMapping("/byProductId")
    public List<ClientProduct> byProductId(@RequestParam Integer id){
        List<ClientProduct> clientProduct = clientProductRepo.findByProductId(id);
        if(clientProduct.isEmpty())
            return null;
        return clientProduct;
    }
}
