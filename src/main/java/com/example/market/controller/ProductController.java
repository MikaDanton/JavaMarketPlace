package com.example.market.controller;

import com.example.market.domain.Product;
import com.example.market.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            Product product = new Product(name, price);
            productRepo.save(product);
            return "Success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
