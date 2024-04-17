package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="mirror_order")
public class MirrorOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MIRROR_ID")
	private Long id;
	
	@Column(name="MIRROR_MODEL")
	private String model;
	
	@Column(name="MIRROR_FRAME")
	private String frame;
	
	@Column(name="MIRROR_FRAME_STYLE")
	private String frameStyle;
	
	@Column(name="MIRROR_FRAME_COLOR")
	private String frameColoe;
	
	@Column(name="MIRROR_WIDTH")
	private String width;
	
	@Column(name="MIRROR_HEIGHT")
	private String height;
	
	@Column(name="MIRROR_SIZE_MESSAGE")
	private String sizeMessage;
	
	@Column(name="MIRROR_LED")
	private String led;
	
	@Column(name="MIRROR_LED_METHOD")
	private String ledMethod;
	
	@Column(name="MIRROR_LED_FORM")
	private String ledForm;
	
	@Column(name="MIRROR_SENSOR")
	private String sensor;
	
	@Column(name="MIRROR_SENSOR_FORM")
	private String sensorForm;
	
	@Column(name="MIRROR_CLIENT_NAME")
	private String name;
	
	@Column(name="MIRROR_CLIENT_PHONE")
	private String phone;
	
	@Column(name="MIRROR_CLIENT_EMAIL")
	private String email;
	
	@Column(name="MIRROR_CLIENT_MESSAGE")
	private String message;
	
}
