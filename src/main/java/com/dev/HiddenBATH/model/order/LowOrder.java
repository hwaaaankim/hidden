package com.dev.HiddenBATH.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tb_low_order")
public class LowOrder{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LOW_ID")
	private Long lowId;
	
	@Column(name="LOW_MODEL")
	private String lowMdel;
	
	@Column(name="LOW_COLOR")
	private String lowColor;
	
	@Column(name="LOW_FORM")
	private String lowForm;
	
	@Column(name="LOW_WIDTH")
	private String lowWidth;
	
	@Column(name="LOW_HEIGHT")
	private String lowHeight;
	
	@Column(name="LOW_DEPTH")
	private String lowDepth; 
	
	@Column(name="LOW_MARBLE_COLOR")
	private String lowMarbleColor;
	
	@Column(name="LOW_WASHSTAND")
	private String lowWashstand;
	
	@Column(name="LOW_WASHSTAND_TOPBALL")
	private String lowWashstandTopball;
	
	@Column(name="LOW_WASHSTAND_DIRECTION")
	private String lowWashstandDirection;
	
	@Column(name="LOW_DOOR")
	private String lowDoor;
	
	@Column(name="LOW_DOOR_COUNT")
	private String lowDoorCount;
	
	@Column(name="LOW_HANDLE")
	private String lowHandle;
	
	@Column(name="LOW_HANDLE_COLOR")
	private String lowHandleColor;
	
	@Column(name="LOW_CLIENT_NAME")
	private String name;
	
	@Column(name="LOW_CLIENT_PHONE")
	private String phone;
	
	@Column(name="LOW_CLIENT_EMAIL")
	private String email;
	
	@Column(name="LOW_CLIENT_MESSAGE")
	private String message;
	
}
