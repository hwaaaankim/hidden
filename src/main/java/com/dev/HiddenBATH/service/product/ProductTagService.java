package com.dev.HiddenBATH.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.HiddenBATH.repository.product.ProductTagRepository;

@Service
public class ProductTagService {

	@Autowired
	ProductTagRepository productTagRepository;
	
	public void deleteProductTag(Long[] id) {
		for(Long i : id) {
			productTagRepository.deleteById(i);
		}
	}
}
