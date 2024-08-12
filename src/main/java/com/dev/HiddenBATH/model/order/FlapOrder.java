package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tb_flap_order")
public class FlapOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FLAP_ID")
	private Long flapId;
	
	@Column(name="FLAP_MODEL")
	private String flapModel;
	
	@Column(name="FLAP_COLOR")
	private String flapColor;
	
	@Column(name="FLAP_SIZE_WIDTH")
	private String flapSize;
	
	@Column(name="FLAP_WIDTH")
	private String flapWidth;
	
	@Column(name="FLAP_HEIGHT")
	private String flapHeight;
	
	@Column(name="FLAP_DEPTH")
	private String flapDdepth;

	@Column(name="DOOR_DIRECTION")
	private String flapDoorDirection;
	
	@Column(name="FLAP_LED")
	private String flapLed;
	
	@Column(name="FLAP_LED_COLOR")
	private String flapLedColor;
	
	@Column(name="FLAP_CONCENT")
	private String flapConcent;
	
	@Column(name="FLAP_CONCENT_POSITION")
	private String flapConcentPosition;
	
	@Column(name="FLAP_CLIENT_NAME")
	private String name;
	
	@Column(name="FLAP_CLIENT_PHONE")
	private String phone;
	
	@Column(name="FLAP_CLIENT_EMAIL")
	private String email;
	
	@Column(name="FLAP_CLIENT_MESSAGE")
	private String message;
	
}
