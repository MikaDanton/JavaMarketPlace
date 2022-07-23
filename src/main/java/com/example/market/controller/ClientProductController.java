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

            ClientProduct clientProduct = new ClientProduct(client.get(), product.get());
            clientProductRepo.save(clientProduct);

            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
