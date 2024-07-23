package com.dev.HiddenBATH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_construction")
@Data
public class Construction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONSTRUCTION_ID")
	private Long id;
	
	@Column(name="CONSTRUCTION_PATH")
	private String constructionPath;
	
	@Column(name="CONSTRUCTION_NAME")
	private String constructionName;
	
	@Column(name="CONSTRUCTION_ROAD")
	private String constructionRoad;

	@Column(name="CONSTRUCTION_THUMB_PATH")
	private String constructionThumbPath;
	
	@Column(name="CONSTRUCTION_THUMB_NAME")
	private String constructionThumbName;
	
	@Column(name="CONSTRUCTION_THUMB_ROAD")
	private String constructionThumbRoad;
}
