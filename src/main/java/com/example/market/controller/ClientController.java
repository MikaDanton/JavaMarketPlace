package com.example.market.controller;

import com.example.market.domain.Client;
import com.example.market.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientRepo clientRepo;
    @GetMapping
    public Iterable<Client> list(){
        Iterable<Client> clients = clientRepo.findAll();

        return clients;
    }
    @GetMapping("/add")
    public String add(@RequestParam String firstname, @RequestParam String lastname, @RequestParam Integer amountofmoney){
        try {
            Client client = new Client(firstname, lastname, amountofmoney);
            clientRepo.save(client);
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
