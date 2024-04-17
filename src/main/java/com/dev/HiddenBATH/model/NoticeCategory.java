package com.dev.HiddenBATH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="notice_category")
@Data
public class NoticeCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NOTICE_CATEGORY_ID")
	private Long id;
	
	@Column(name="NOTICE_CATEGORY_TEXT")
	private String text;
	
}
