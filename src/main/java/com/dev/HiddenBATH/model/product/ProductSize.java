package com.dev.HiddenBATH.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_product_size")
@Data
public class ProductSize {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_SIZE_ID")
	private Long id;
	
	@Column(name="PRODUCT_SIZE_TEXT")
	private String productSizeText;
}
