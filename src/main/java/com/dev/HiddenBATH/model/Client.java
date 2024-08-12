package com.dev.HiddenBATH.model;

import java.util.Date;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_client")
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CLIENT_ID")
	private Long id;
	
	@Column(name="CLIENT_NAME")
	private String name;
	
	@Column(name="CLIENT_EMAIL")
	private String email;
	
	@Column(name="CLIENT_PHONE")
	private String phone;
	
	@Column(name="CLIENT_DATE")
	private Date joindate;
	
	@Column(name="CLIENT_CHECK_DATE")
	private Date checkDate;
	
	@Column(name="CLIENT_TOPIC")
	@Nullable
	private String topic;
	
	@Column(name="CLIENT_CONTACT")
	private Boolean contact;
	
	@Column(name="CLIENT_COMMENT")
	private String comment;
	
}





















