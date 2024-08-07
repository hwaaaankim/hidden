package com.dev.HiddenBATH.model.product;

import java.util.List;

import org.springframework.lang.Nullable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="tb_product")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	private Long id;
	
	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Column(name="PRODUCT_SIGN")
	private Boolean productSign;
	
	@Column(name="PRODUCT_ROTATION_NUMBER")
	private int productRotationNumber;
	
	@Column(name="PRODUCT_ROTATION_EXTENSION")
	private String productRotationExtension;
	
	@Column(name="PRODUCT_TITLE")
	private String title;
	
	@Column(name="PRODUCT_SUBJECT")
	private String subject;
	
	@Column(name="PRODUCT_HANDLE")
	private Boolean handle;
	
	@Column(name="PRODUCT_ORDER")
	private Boolean order;
	
	@Column(name="PRODUCT_UNIT")
	private String unit;
	
	@Column(name="PRODUCT_INDEX")
	private int productIndex;
	
	@Column(name="PRODUCT_REP_IMAGE_NAME")
	private String productRepImageName;
	
	@Column(name="PRODUCT_REP_IMAGE_EXTENSION")
	private String productRepImageExtension;
	
	@Column(name="PRODUCT_REP_IMAGE_ORIGINAL_NAME")
	private String productRepImageOriginalName;
	
	@Column(name="PRODUCT_REP_IMAGE_PAGH")
	private String productRepImagePath;
	
	@Column(name="PRODUCT_REP_IMAGE_ROAD")
	private String productRepImageRoad;
	
	@Transient
	private String randomImage;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Nullable
	@JoinTable(
			name="tb_product_and_color", 
			joinColumns = @JoinColumn(name="PC_PRODUCT_ID"),
			inverseJoinColumns = @JoinColumn(name="PC_COLOR_ID")
			)
	private List<ProductColor> productColors;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Nullable
	@JoinTable(
			name="tb_product_and_size", 
			joinColumns = @JoinColumn(name="PS_PRODUCT_ID"),
			inverseJoinColumns = @JoinColumn(name="PS_SIZE_ID")
			)
	private List<ProductSize> productSizes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Nullable
	@JoinTable(
			name="tb_product_and_tag", 
			joinColumns = @JoinColumn(name="PT_PRODUCT_ID"),
			inverseJoinColumns = @JoinColumn(name="PT_TAG_ID")
			)
	private List<ProductTag> productTags;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Nullable
	@JoinTable(
			name="tb_product_and_option", 
			joinColumns = @JoinColumn(name="PO_PRODUCT_ID"),
			inverseJoinColumns = @JoinColumn(name="PO_OPTION_ID")
			)
	private List<ProductOption> productOptions;
	
	@Transient
	private Long middleId;
	
	@Transient
	private Long bigId;
		
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "productId"
			)
	private List<ProductImage> images;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "productId"
			)
	private List<ProductFile> files;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name="PRODUCT_MIDDLE_REFER_ID", referencedColumnName="MIDDLE_SORT_ID"
			)
	private MiddleSort middleSort;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name="PRODUCT_BIG_REFER_ID", referencedColumnName="BIG_SORT_ID"
			)
	private BigSort bigSort;
	
}
























