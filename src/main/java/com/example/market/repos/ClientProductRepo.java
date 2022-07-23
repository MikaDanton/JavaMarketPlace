package com.example.market.repos;

import com.example.market.domain.ClientProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientProductRepo extends CrudRepository<ClientProduct, Integer> {
    @Query(value = "select * from client_product as cp\n" +
            "where cp.client_id = ?1", nativeQuery = true)
    List<ClientProduct> findByClientId(Integer id);
    @Query(value = "select * from client_product as cp\n" +
            "where cp.product_id = ?1", nativeQuery = true)
    List<ClientProduct> findByProductId(Integer id);
}
