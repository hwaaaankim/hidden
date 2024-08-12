package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tb_slide_order")
public class SlideOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SLIDE_ID")
	private Long slideId;
	
	@Column(name="SLIDE_MODEL")
	private String slideModel;
	
	@Column(name="SLIDE_COLOR")
	private String slideColor;
	
	@Column(name="SLIDE_WIDTH")
	private String slideWidth;
	
	@Column(name="SLIDE_HEIGHT")
	private String slideHeight;
	
	@Column(name="SLIDE_DEPTH")
	private String slideDepth;
	
	@Column(name="SLIDE_LED")
	private String slideLed;
	
	@Column(name="SLIDE_LED_COLOR")
	private String slideLedColor;
	
	@Column(name="SLIDE_CONCENT")
	private String slideConcent;
	
	@Column(name="SLIDE_CONCENT_POSITION")
	private String slideConcentPosition;
	
	@Column(name="SLIDE_CLIENT_NAME")
	private String name;
	
	@Column(name="SLIDE_CLIENT_PHONE")
	private String phone;
	
	@Column(name="SLIDE_CLIENT_EMAIL")
	private String email;
	
	@Column(name="SLIDE_CLIENT_MESSAGE")
	private String message;
	
}
