package com.example.market.repos;

import com.example.market.domain.ClientProduct;
import org.springframework.data.repository.CrudRepository;

public interface ClientProductRepo extends CrudRepository<ClientProduct, Integer> {
}
