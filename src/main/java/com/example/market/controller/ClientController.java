package com.example.market.controller;

import com.example.market.domain.Client;
import com.example.market.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public String add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer amountOfMoney){
        try {
            if(amountOfMoney < 0)
                throw new Exception("Amount of Money can not be negative!");
            Client client = new Client(firstName, lastName, amountOfMoney);
            clientRepo.save(client);
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/addMoney")
    public String addMoney(@RequestParam Integer id, @RequestParam Integer money){
        try {
            Optional<Client> client = clientRepo.findById(id);
            client.get().addMoney(money);
            clientRepo.save(client.get());
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id){
        try {
            Optional<Client> client = clientRepo.findById(id);
            if(client.isEmpty())
                throw new Exception("Client not found!");
            clientRepo.delete(client.get());
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
