package com.dev.HiddenBATH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long>{

}
