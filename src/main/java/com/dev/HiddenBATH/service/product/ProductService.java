package com.dev.HiddenBATH.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.HiddenBATH.repository.product.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
}
