package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tb_mirror_order")
public class MirrorOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MIRROR_ID")
	private Long mirrorId;
	
	@Column(name="MIRROR_MODEL")
	private String mirrorModel;
	
	@Column(name="MIRROR_FRAME")
	private String mirrorFrame;
	
	@Column(name="MIRROR_FRAME_STYLE")
	private String mirrorFrameStyle;
	
	@Column(name="MIRROR_FRAME_COLOR")
	private String mirrorFrameColor;
	
	@Column(name="MIRROR_WIDTH")
	private String mirrorWidth;
	
	@Column(name="MIRROR_HEIGHT")
	private String mirrorHeight;

	@Column(name="MIRROR_LED")
	private String mirrorLed;
	
	@Column(name="MIRROR_LED_METHOD")
	private String mirrorLedMethod;
	
	@Column(name="MIRROR_LED_FORM")
	private String mirrorLedForm;
	
	@Column(name="MIRROR_CLIENT_NAME")
	private String name;
	
	@Column(name="MIRROR_CLIENT_PHONE")
	private String phone;
	
	@Column(name="MIRROR_CLIENT_EMAIL")
	private String email;
	
	@Column(name="MIRROR_CLIENT_MESSAGE")
	private String message;
	
}
