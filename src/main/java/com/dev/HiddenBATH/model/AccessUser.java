package com.dev.HiddenBATH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TB_MEMBER")
public class AccessUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "MEMBER_USERNAME")
	private String username;
	
	@Column(name = "MEMBER_PASSWORD")
	private String password;
	
	@Column(name = "MEMBER_ROLE")
	private String role;
	
}
