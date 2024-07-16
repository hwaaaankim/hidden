package com.dev.HiddenBATH.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	private String productName;
	private String productCode;
	private String productTitle;
	private MultipartFile productImage;
	private List<MultipartFile> slideImages;
	private List<MultipartFile> files;
	private List<Long> sizes;
	private List<Long> colors;
	private List<Long> options;
	private Long bigSort;
	private Long middleSort;
	private List<Long> tags;
	private String subject;
}
