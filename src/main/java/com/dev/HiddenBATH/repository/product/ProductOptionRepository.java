package com.dev.HiddenBATH.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>{

}
