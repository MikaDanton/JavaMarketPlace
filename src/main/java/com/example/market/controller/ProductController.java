package com.example.market.controller;

import com.example.market.domain.Client;
import com.example.market.domain.Product;
import com.example.market.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @GetMapping
    public Iterable<Product> list(){
        Iterable<Product> products = productRepo.findAll();

        return products;
    }
    @GetMapping("/add")
    public String add(@RequestParam String name, @RequestParam Integer price){
        try {
            if(price < 0)
                throw new Exception("Price can not be negative!");
            Product product = new Product(name, price);
            productRepo.save(product);
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id){
        try {
            Optional<Product> product = productRepo.findById(id);
            if(product.isEmpty())
                throw new Exception("Product not found!");
            productRepo.delete(product.get());
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
