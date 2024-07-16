package com.dev.HiddenBATH.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
