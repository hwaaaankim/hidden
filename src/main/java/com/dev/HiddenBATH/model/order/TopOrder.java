package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tb_top_order")
public class TopOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TOP_ID")
	private Long topId;
	
	@Column(name="TOP_MODEL")
	private String topModel;
	
	@Column(name="TOP_COLOR")
	private String topColor;
	
	@Column(name="TOP_WIDTH")
	private String topWidth;
	
	@Column(name="TOP_HEIGHT")
	private String topHeight;
	
	@Column(name="TOP_DEPTH")
	private String topDepth;
	
	@Column(name="TOP_DOOR_COUNT")
	private String topDoorPosition;
	
	@Column(name="TOP_LED")
	private String topLed;
	
	@Column(name="TOP_LED_COLOR")
	private String topLedColor;
	
	@Column(name="TOP_CONCENT")
	private String topConcent;
	
	@Column(name="TOP_CONCENT_POSITION")
	private String topConcentPosition;

	@Column(name="TOP_CLIENT_NAME")
	private String name;
	
	@Column(name="TOP_CLIENT_PHONE")
	private String phone;
	
	@Column(name="TOP_CLIENT_EMAIL")
	private String email;
	
	@Column(name="TOP_CLIENT_MESSAGE")
	private String message;
	
}
