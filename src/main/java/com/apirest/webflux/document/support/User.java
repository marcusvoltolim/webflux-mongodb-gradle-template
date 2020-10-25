package com.apirest.webflux.document.support;

import com.apirest.webflux.enums.support.UserType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User implements EntityPersistable {

	@Id
	private String id;

	private String name;

	private String password;

	private UserType userType;

}