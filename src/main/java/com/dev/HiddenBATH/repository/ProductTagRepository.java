package com.dev.HiddenBATH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.ProductTag;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long>{

}
