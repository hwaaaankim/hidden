package com.dev.HiddenBATH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_gallery")
@Data
public class Gallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="GALLERY_ID")
	private Long id;
	
	@Column(name="GALLERY_PATH")
	private String galleryPath;
	
	@Column(name="GALLERY_NAME")
	private String galleryName;
	
	@Column(name="GALLERY_ROAD")
	private String galleryRoad;
	
	@Column(name="GALLERY_THUMB_PATH")
	private String galleryThumbPath;
	
	@Column(name="GALLERY_THUMB_NAME")
	private String galleryThumbName;
	
	@Column(name="GALLERY_THUMB_ROAD")
	private String galleryThumbRoad;
	
}
